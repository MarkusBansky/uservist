import React from 'react';
import clsx from 'clsx';
import Layout from '@theme/Layout';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import useBaseUrl from '@docusaurus/useBaseUrl';
import styles from './styles.module.css';

const features = [
  {
    title: 'Build yourself',
    // imageUrl: 'img/buildFromScratch.png',
    description: (
      <>
        Uservist has been designed to be easily built by the end-user and deployed to a docker container.
        You can build the project simply by cloning it and running the universal build script <code>build.go</code>.
      </>
    ),
  },
  {
    title: 'REST principles',
    // imageUrl: 'img/undraw_docusaurus_tree.svg',
    description: (
      <>
        Uservist is build upon <b>REST</b> principles and is available through the simple wll-documented API.
      </>
    ),
  },
  {
    title: 'Powered by Java + React',
    // imageUrl: 'img/undraw_docusaurus_react.svg',
    description: (
      <>
        The back-end services are developed using <b>Java</b> & <b>Spring Boot</b>, on the other hand the front-end is
        developed using <b>React</b> and <b>Typescript</b>. Each contains easily customizable components as well as the
        database migrations.
      </>
    ),
  },
];

function Feature({imageUrl, title, description}) {
  const imgUrl = useBaseUrl(imageUrl);
  return (
    <div className={clsx('col col--4', styles.feature)}>
      {imgUrl && (
        <div className="text--center">
          <img className={styles.featureImage} src={imgUrl} alt={title} />
        </div>
      )}
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  );
}

function Home() {
  const context = useDocusaurusContext();
  const {siteConfig = {}} = context;
  return (
    <Layout
      title={`Hello from ${siteConfig.title}`}
      description="Description will go into a meta tag in <head />">
      <header className={clsx('hero hero--primary', styles.heroBanner)}>
        <div className="container">
          <h1 className="hero__title">{siteConfig.title}</h1>
          <p className="hero__subtitle">{siteConfig.tagline}</p>
          <div className={styles.buttons}>
            <Link
              className={clsx(
                'button button--outline button--secondary button--lg',
                styles.getStarted,
              )}
              to={useBaseUrl('docs/')}>
              Get Started
            </Link>
          </div>
        </div>
      </header>
      <main>
        {features && features.length > 0 && (
          <section className={styles.features}>
            <div className="container">
              <div className="row">
                {features.map((props, idx) => (
                  <Feature key={idx} {...props} />
                ))}
              </div>
            </div>
          </section>
        )}
      </main>
    </Layout>
  );
}

export default Home;
