package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.utils.IOProvider;

public abstract class Command {
    String name;
    String description;
    IOProvider provider;
    CollectionManager collection;

    public Command(String name, String description, IOProvider provider, CollectionManager collection) {
        this.name = name;
        this.description = description;
        this.provider = provider;
        this.collection = collection;
    }

    public abstract void execute(String[] args) throws InvalidArgsException;

    public String getDescription() {
        return String.format("%s    |     %s", name, description);
    }

    public void validateArgs(String[] args, int length) throws InvalidArgsException {
        if (args.length != length) {
            throw new InvalidArgsException();
        }
    }
}
