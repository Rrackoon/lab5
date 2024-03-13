package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.models.StudyGroup;
import org.example.utils.IOProvider;

public class RemoveFirstCommand extends Command {
    public RemoveFirstCommand(IOProvider provider, CollectionManager collection) {
        super("remove_first", "удалить первый элемент из коллекции", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        collection.removeById(0);
        provider.getPrinter().print("First collection element removed successfully.");
    }
}
