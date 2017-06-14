# SeleniumStandalone
Bare bones Selenium test in an IntelliJ Maven project

This IntelliJ Maven project uses the JUnit and Selenium dependencies to run a simple search engine test against Google in a Chrome browser.

This IntelliJ Maven project was created using the archetype maven-archetype-quickstart

Once the project was created, the only files I edited were:
  pom.xml - to add the JUnit and Selenium <dependencies>.
  AppTest.java - to create the Google search engine test.

In order to run the test from the command line on a Mac computer:
1. Verify Chrome is installed.
2. Verify Maven is installed.
3. Clone this repository.
4. In the repository's root folder, type the command: 
    mvn clean install

