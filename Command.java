package com.all.sandboxjava.designpatterns.command;

public abstract class Command implements Cloneable {
    abstract void execute();

    abstract void setArgs(String args);
    abstract String getCommandName();
    public abstract void undo();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
