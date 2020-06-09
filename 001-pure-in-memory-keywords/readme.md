# Simple In Memory Parser

This example shows a simple in memory example i.e. the script is 'coded' rather than being read from a file.

This allows us to create the basic set of classes we need to support.

- KeywordCommand - a command with a set of arguments
- KeywordScript - a list of commands
- KeywordScriptExecutor - a class that can execute the script

We have a simple parser in the `parse` method on KeywordScript which takes a list of tab delimited strings and 