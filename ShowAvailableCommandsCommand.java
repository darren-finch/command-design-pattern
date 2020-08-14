package com.all.sandboxjava.designpatterns.command;

public class ShowAvailableCommandsCommand extends Command {
    @Override
    void execute() {
        //If you add a new command, put its syntax here.
        System.out.println("Available commands:");
        System.out.println("paint [<x>, <y>, <radius>]");
        System.out.println("erase [<x>, <y>, <radius>]");
        System.out.println("pickcolor [<x>, <y>]");
    }

    @Override
    void setArgs(String args) {

    }

    @Override
    String getCommandName() {
        return "a";
    }

    @Override
    public void undo() {

    }
}
