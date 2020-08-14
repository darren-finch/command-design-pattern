package com.all.sandboxjava.designpatterns.command;

import java.util.List;

public class CanvasCommandInvoker {

    private final List<Command> registeredCommands;
    private final CommandHistory commandHistory;

    public CanvasCommandInvoker(List<Command> allCommands) {
        this.registeredCommands = allCommands;
        this.commandHistory = new CommandHistory();
    }


    /* If this function seems complex, here's why.
    * I wanted a way for the user to call a command by simply passing a command name and the command arguments as Strings.
    * The naive way of doing this would be a huge switch statement that turns command names into the corresponding command objects.
    * And since each command needs to be unique, we can either use reflection to create a new command object,
    * or we can store every type of command in a list, figure out the desired command, clone that command, and execute that command.
    * Reflection would be too complex for this article (which is how this should be implemented), so I went the second route.
    * This does mean we cannot send arguments over to the command in the command constructor, but the flexibility this solution offers is worth it.
    * */
    public void executeCommand(String commandName, String args) {
        for(Command command : registeredCommands) {
            if(command.getCommandName().toLowerCase().replaceAll(" ", "").equals(commandName.toLowerCase().replaceAll(" ", ""))) {
                try {
                    Command newCommand = (Command) command.clone();
                    newCommand.setArgs(args);
                    newCommand.execute();

                    commandHistory.addCommandToHistory(newCommand);
                    return;
                } catch (Exception e) {
                    CustomLogger.warn("Invoker", "Couldn't execute command for some reason. Check logs for more details.");
                    e.printStackTrace();
                    return;
                }
            }
        }
        CustomLogger.warn("CanvasCommandInvoker", "\"" + commandName + "\"" + " command not found.");
    }

    public void undoLastCommand() {
        commandHistory.undoLastCommand();
    }
}
