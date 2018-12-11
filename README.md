Example of nice console output for Cucumber4-JVM and LOG4J2 for parallel test execution.

##### Motivation
Starting from Cucumber-JVM 4.0.0 it's possible now to run tests in parallel. However, console output is messed up, because `pretty` Cucumber plugin does not interact with any logging framework, therefore Cucumber and logging framework produce logs independently.

This project shows an example of how to make Cucumber to print nice logs for parallel tests.


##### Description
Project contains pure Cucumber-JVM as test framework and log4j2 + slf4j as logging framework. Project is built by Gradle. 2 gradle tasks were created to demonstrate console output with existing `pretty` plugin (`cucumberTestsUsual` task) and improved pretty plugin, built in conjunction with log4j2 appender (`cucumberTestsPrettyLogs` task).


##### Scope of refactoring

`main` module contains `PrettyFormatter.java` and dependent classes (they are not public, so I had to copy-paste them as well). 
* Class `PrettyFormatter.java` was refactored, most of functionality were extracted to parent class `AbstractPrettyFormatter.java`. In result, behaviour of `PrettyFormatter.java` was not changed.
* Class `CucumberLog4j2ParallelAppender.java` was added. It implements standard log4j2 `AbstractAppender`.
* Class `PrettyLogFormatter.java` was added. It reuses most of functionality from `PrettyFormatter.java` (extracted to `AbstractPrettyFormatter.java`). The both Cucumber event logs and logging framework logs are stored in map and released at once during `TestCaseFinished` event.
* `PrettyLogFormatter` and `CucumberLog4j2ParallelAppender` should be used together (instead of `PrettyFormatter` and console log appender respectively). See configuration examples in gradle tasks and `log4j2.xml` file.
* Solution can be easily extended for other logging frameworks.

##### Demo
Clone project and:
* Run `gradlew cucumberTestsUsual` to see how console logs are printed now for parallel test execution;
* Run `gradlew cucumberTestsPrettyLogs` to see how console logs are printed with improved log formatter
