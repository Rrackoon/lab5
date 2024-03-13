package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.utils.IOProvider;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand(IOProvider provider, CollectionManager collection) {
        super("remove_by_id {id}", "удалить элемент из коллекции по его id", provider, collection);
    }

    @Override
    public void validateArgs(String[] args, int length) throws InvalidArgsException {
        try {
            super.validateArgs(args, length);
            long id = Long.parseLong(args[0]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgsException();
        }
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);

        long stId = Long.parseLong(args[0]);
        if (collection.get(stId) == null) {
            provider.getPrinter().print("StudyGroup with specified ID doesn't exist.");
            return;
        }
        collection.removeById(stId);
        provider.getPrinter().printf("StudyGroup's (ID %s) removed successfully.\n", stId);
    }
}
