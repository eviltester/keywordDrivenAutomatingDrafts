# Simple In Memory Parser

This example shows a simple in memory example i.e. the script is 'coded' rather than being read from a file.

This allows us to create the basic set of classes we need to support.

- KeywordCommand - a command with a set of arguments
- KeywordScript - a list of commands
- KeywordScriptExecutor - a class that can execute the script

We have a simple parser in the `parse` method on KeywordScript which takes a list of tab delimited strings and parses them into KeywordCommand(s)

The KeywordCommand parses a tab delimited string to populate itself with the command and arguments.

At the moment the execution is a switch statement with all code inline, this is good enough as a simple example but would be unmaintainable long term.

Initially this might be refactored into a set of methods, but would eventually move to a set of CommandExecutor classes built on a Command pattern.

The unit tests for the classes are not complete, but show that it is possible to TDD a 'test framework' and where the limits of the TDD fall i.e. we would not TDD the execution because it uses WebDriver - we might mock it out, but that seems a bit painful, but... who knows where we might take this part.