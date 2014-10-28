package com.github.twitterswingsample.view.migration.console;

public interface ConsoleInterface {

    /**
     * Inserts a message into the textarea
     * 
     * @param message text to be displayed in the console. every field of the array gets a new line.
     */
    public abstract void printMessage(String[] message);

}