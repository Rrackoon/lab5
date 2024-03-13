package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.utils.IOProvider;

public class ClearCommand extends Command {
    public ClearCommand(IOProvider provider, CollectionManager collection) {
        super("clear", "очистить коллекцию", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        collection.clear();
        provider.getPrinter().print("Collection cleared successfully.\n");
    }
}
