package com.all.sandboxjava.designpatterns.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This is a fictitious app meant to show off the command design pattern.
 * I've created 3 commands that do something with the fictional canvas, "paint", "erase", and "pickcolor".
 * I've also created one more command "a" that lists all available commands.
 * If you add your own command, print its syntax in the "a" execute() method.
 *
 * Steps to add your own command:
 * 1. Create a new class <YourCommandName>Command that extends the Command abstract class.
 * 2. Override desired functionality (mainly in execute()). You don't have to receive args or care about the canvas.
 * 3. Add your command in the loadAllCommands() function.
 * */
public class ImageEditorApp {

    private static CanvasCommandInvoker commandInvoker;

    //NOTE: If you create any new commands, you have to add them here.
    private static void loadAllCommands() {
        List<Command> allCommands = new ArrayList<>();
        allCommands.add(new PaintCommand());
        allCommands.add(new EraseCommand());
        allCommands.add(new PickColorCommand());
        allCommands.add(new ShowAvailableCommandsCommand());
        commandInvoker = new CanvasCommandInvoker(allCommands);
    }

    public static void main(String[] args) {
        loadAllCommands();

        Scanner inputScanner = new Scanner(System.in);

        printMenu();

        String input = getInput(inputScanner);

        while (!input.equals("q")) {
            try {
                if (input.isEmpty())
                    throw new IllegalArgumentException("Empty input, please enter a command.");

                if (input.equals("undo")) {
                    commandInvoker.undoLastCommand();
                } else {
                    String command = getCommand(input);
                    String commandArgs = getCommandArgs(input);

                    commandInvoker.executeCommand(command, commandArgs);
                }
            } catch (Exception e) {
                CustomLogger.warn("ImageEditorApp", e.getMessage());
                printMenu();
            }
            input = getInput(inputScanner);
        }
    }

    private static void printMenu() {
        System.out.println("Please enter a command in the format of <command name> [argument1, argument2, argument3, ...]");
        System.out.println("Type \"a\" to see available commands.");
        System.out.println("Type \"undo\" to undo the last command.");
        System.out.println("Type \"q\" to quit.");
    }

    private static String getInput(Scanner scanner) {
        return scanner.nextLine().toLowerCase();
    }

    private static String getCommandArgs(String input) throws IllegalArgumentException {
        int bracket1Index = input.indexOf("[");
        int bracket2Index = input.indexOf("]");

        if (bracket1Index < 0 || bracket2Index < 0) {
            //The user wants to pass args to this command because he supplied a space, but he didn't pass any args.
            //Throw an exception.
            if (input.contains(" ")) {
                throw new IllegalArgumentException("Arguments were not supplied with [] syntax.");
            }
            //The user doesn't want to pass args to this command.
            return "";
        } else {
            return input.substring(bracket1Index + 1, bracket2Index);
        }
    }

    private static String getCommand(String input) {
        int commandEndIndex = input.contains(" ") ? input.indexOf(" ") : input.length();

        return input.substring(0, commandEndIndex);
    }
}