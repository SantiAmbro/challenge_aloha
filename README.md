#Aloha 4

#### RPT - Command Processor
This exercise simulates a command line shell. It does **NOT** actually create any objects on the physical disk. Instead, it maintains objects and state within the context of the running application.

While this application could be written procedurally, using OOP techniques (when appropiate), is preferred to seperate functionality into more logical and maintanable pieces.

####Input
The main mechanism of the application is the acceptance of text on the command line. Commands should be read and processed until de [quit] command is found.

If the entered command is not recognized, the Unrecognized Command error message should be returned. Or if the command has invalid parameters, or any parameter is missing, the Invalid Command error message
should be returned.

####Commands
####Quit
Command name: quit

Command terminates de main application loop.

####Current Directory
Command name: pwd
Command prints the full path of the current directory

List Contents
Command name: ls

Switches( ** extra ** )
[-r] recursive: If provided, prints everything in the current directory and all subdirectories

This command lists the contents (directories & files) of the current directory. It writes a single item per line. If there are no items, print nothing. If printing recursively, before printing contents, print the full path of the current directory

####Make Directory
Command name: mkdir

Parameters:
[dirName] name of the directory to make (char limit 100)

Creates a directory entry in the cache. If the Directory already exists (by name), a message like "Directory already exists" should be printed.

####Change Directory
Command name: cd

Parameters:
[dirName] name of the sub-directory to change current path to

Command changes the current path to a sub-directory (by name). If the name does not exists "Directory not found" message should be displayed.

####Create File
Command name: touch

Parameters:
[fileName] name of the directory to make (char limit 100)

Creates a file on current Directory

####EXTRAS
* An optional parameter can be added to the command to accept a multi-faceted path (i.e. subdir1/subdir1-1/subdir3)
    * cd (follow path recursively. of any of the parts do not exists, command should failed with "Invalid Path")
    * ls (the list should be applied of the final node of the supplied path)

* The previous state of the application could be loaded from a file on disk. The contents of this file would be a serialized representation of the application context