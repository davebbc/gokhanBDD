# Gokhan BDD 

## Get the code

Git:

    git clone git@github.com:atesg01/gokhanBDD.git

## Use Maven

Open a command window and run:

    cd maven
    mvn clean install

This runs Cucumber features using Cucumber's JUnit Platform Engine. The `Suite`
annotation on the `RunCucumberTest` class tells JUnit to kick off Cucumber.

## Run test without maven
Run the runner file (RunCucumberTest) located in /src/test/java/io/cucumber/runners/RunCucumberTest.java
