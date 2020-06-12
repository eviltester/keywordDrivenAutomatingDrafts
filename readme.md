Keyword driven testing example Hobby Project

Keyword Driven Testing Explained:

- it's not testing, it's automating
- explain keyword driven testing with examples
- explain basic parsing: interpreter, text adventures
- keyword driven pros, cons, gotchas
- framework vs library explained
- modelling, abstracting, domains and DSLs explained
- desired tool support - code completion, why excel doesn't help, etc.

Basic Home Grown Keyword Driven Framework:

- explain tech we are building on initially - code [x] 000
- write a keyword driven testing example in code with no external files - code [x] 001
- read the keywords from a file - tab delimited - code [x] 002
- read the keywords from an excel file using the excel library Apache POI - code [x] 003
- refactor code to use command pattern - code [x] 004
- add more 'commands' - click [x], alert handling [x] 005
- adding assertions - can see text [x] 006
- evaluation of the extras commands and assertions [x] 007
    - e.g. should it be alert \t cancel i.e. action as first param
    - should it be check \t can see text
    - should it be check \t [any command] - left as todo in text, thought experiment
    - pros: smaller command set, easy to expand in code
- state clean up so no hanging browsers, single exception throwing, and stop on failed step etc. [x] 008   
- logging [x] 009
    - logging happens as we run
- reporting
    - different formats
    - happens after we run
- data driven testing vs keyword driven
- add data driven capabilities
- refactor further to make easier to use library interface
- wrap as a 'tool' i.e. main method

Existing Keyword Driven Frameworks:

- existing keyword driven frameworks overview
- Keywords are a basic DSL, Cucumber is not Keyword Driven
- demo of robot framework
- demo of katalon studio
- demo of karate framework
- advanced keyword driven concepts implemented in commercial and open source tooling
- features you should look for (refer back to desired tool support earlier)

Advanced Features:

- assertions on any command - check \t true \t [any command] - need to pass in the keyword map, could use constructor to do this when adding to map
- see what features in existing frameworks that we might want to approximate in 'advanced' to demystify
- run all scripts from all sheets?
- run all tab files in a 'suite' file or directory
- named parameters for commands, default params
- syntax checking
- macro commands built on other commands
- abstract tech - use JSoup, as well as WebDriver (showing differences)
- using it as a library of commands to automate beyond the keywords
- executor customisation e.g. pause between steps
- 'it' i.e. find Element, add to 'it' in state, then allow check 'checkname' it
- variables from attributes or text 

Aims:

- keep sections short
- keep examples simple
- use basic classes and control structures to make it easier for people with other language experiences to understand
- demystify