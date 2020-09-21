package main

import (
    "bufio"
    "github.com/briandowns/spinner"
    "github.com/docopt/docopt-go"
    log "github.com/sirupsen/logrus"
    "github.com/tcnksm/go-input"
    "os"
    "os/exec"
    "strings"
    "time"
)

func main() {
    usage := `Command line tool to build Uservist Service docker image.

Usage:
  go buildDocker.go
  go buildDocker.go -f | --force
  go buildDocker.go -h | --help
  go buildDocker.go --version

Options:
  -h --help     Show this screen.
  --version     Show version.
  -f --force    Force build and scip all prompts to default.`
    arguments, _ := docopt.ParseDoc(usage)
    forcePrompts, _ := arguments.Bool("--force")

    log.SetOutput(os.Stdout)
    log.SetLevel(log.DebugLevel)
    log.SetFormatter(&log.TextFormatter{FullTimestamp: true})
    // set up spinner
    s := spinner.New(spinner.CharSets[11], 100*time.Millisecond)
    // set up ui elements
    ui := &input.UI{
        Writer: os.Stdout,
        Reader: os.Stdin,
    }
    // ask for input tag name
    dockerTag := "uservist-service"
    if !forcePrompts {
        dt, _ := ui.Ask("Enter your name for the docker image tag", &input.Options{
            Default: "uservist-service",
            Required: true,
            Loop:     true,
        })
        dockerTag = dt
    }
    // read gradle app version
    file, _ := os.Open("build.gradle")
    defer file.Close()
    scanner := bufio.NewScanner(file)
    dockerVersion := "0.0.1"
    for scanner.Scan() {
        line := scanner.Text()
        if strings.HasPrefix(line, "version") {
            dockerVersion = strings.Replace(strings.TrimSpace(line[7:]), "'", "", -1)
            break
        }
    }

    // other commands...
    gradleClean := exec.Command("gradle", "clean")
    gradleBuild := exec.Command("gradle", "build")
    dockerBuild := exec.Command("docker", "build", "--tag", dockerTag + ":" + dockerVersion, ".")

    // 1. Clean gradle project
    log.WithFields(log.Fields{"stage": "gradle"}).Info("Cleaning gradle project")
    s.Start()
    if err := gradleClean.Run(); err != nil {
        log.Fatal("Failed to clean the project", err)
        return
    }
    s.Stop()

    // 2. Build gradle project
    log.WithFields(log.Fields{"stage": "gradle"}).Info("Building gradle project")
    s.Start()
    if err := gradleBuild.Run(); err != nil {
        log.Fatal("Failed to build the project", err)
        return
    }
    s.Stop()

    // 3. Build docker project
    log.WithFields(log.Fields{"stage": "docker"}).Info("Building Docker image")
    s.Start()
    if err := dockerBuild.Run(); err != nil {
        log.Fatal("Failed to build docker image", err)
        return
    }
    s.Stop()

    log.Info("DONE \n")
    log.Info("Image with a name of: '" + dockerTag + ":" + dockerVersion + "' is ready to be used...")
    log.Info("You can use this image as: 'docker run --publish 9090:9090 --detach --name uservist-service " + dockerTag + ":" + dockerVersion + "'")
}