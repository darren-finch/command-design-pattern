package com.all.sandboxjava.designpatterns.command;

import java.util.EmptyStackException;
import java.util.Stack;

public class CommandHistory {
    private final Stack<Command> history;

    public CommandHistory() {
        history = new Stack<>();
    }

    public void addCommandToHistory(Command command) {
        history.push(command);
    }

    public void undoLastCommand() {
        try {
            Command mostRecentCommand = history.pop();
            mostRecentCommand.undo();
        }
        catch (EmptyStackException e) {
            CustomLogger.info("CommandHistory", "Trying to undo command from empty command history.");
        }
    }
}
