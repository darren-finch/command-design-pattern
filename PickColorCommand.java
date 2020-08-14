package com.all.sandboxjava.designpatterns.command;

import com.all.sandboxjava.designpatterns.command.Canvas.Point;

public class PickColorCommand extends Command {
    private String[] arguments;

    @Override
    void execute() throws IllegalArgumentException {
        try {
            //Decipher arguments
            Canvas.pickColor(getPickColorLocationFromArgs());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal arguments passed to " + getCommandName() + " command!");
        }
    }

    private Point getPickColorLocationFromArgs() {
        return new Point(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]));
    }

    @Override
    void setArgs(String args) {
        this.arguments = args.replaceAll(" ", "").split(Constants.COMMAND_ARGUMENT_DELIMETER);
    }

    @Override
    String getCommandName() {
        return "Pick Color";
    }

    @Override
    public void undo() {
        System.out.println("Undoing " + getCommandName());
    }
}