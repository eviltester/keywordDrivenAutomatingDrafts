## Derisk and Experiment with the Tech

Rather than build a 'framework' immediately.

We want to derisk the installation and check that the libraries we want to use are understood and good enough for our needs.

In this case:

- WebDriver Manager to control the WebDriver config and setup
- JUnit 5 as our basic 'internal' test execution and assertion framework
- WebDriver as the browser execution engine

We may want to add logging frameworks later as there will be a need for reporting and logging during the execution of scripts.

The `@Test` method derisks the basic set of commands we want to implement as keywords initially.

This also helps us get used to the underlying tech and make sure we understand it, and if it doesn't meet our needs then we can swap in and out tech in an experimental, adhoc area, rather than in the framework.

If we do identify risk then the keyword driven framework should be build in such a way that we can swap tech in and out... but we won't do that initially, we will build on this tech.