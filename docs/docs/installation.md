---
id: installation
title: Installation
sidebar_label: Installation Guide
slug: /
---

Follow these steps to install Uservist service and additional software in order to get started.

## Requirements
In order to successfully run this application you need to 
have this software pre-installed on your machine.

### For Docker

- Docker `latest`
- PostgreSQL `12`

### For Java *(without docker)*

- Java `^14`
- Gradle `6.6.1`
- NodeJS `^14.11` *(optional if you want to use frontend not on docker)*

## Create Docker containers
The heart of **Uservist** are two containers: `uservist-service` and the `uservist-frontend`.
 
You can run a docker container for the first service by running the next command below:

```shell script
$ docker run -p 9090:9090 -d --name uservist-service uservist-service:0.0.1 
``` 

This service requires config variables to work with the local database.