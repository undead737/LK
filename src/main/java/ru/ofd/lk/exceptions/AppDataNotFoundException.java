package ru.ofd.lk.exceptions;

public class AppDataNotFoundException extends Exception{
    public AppDataNotFoundException() {
        super("Not found");
    }

    public AppDataNotFoundException(String message) {
        super(message + " not found!");
    }
}
