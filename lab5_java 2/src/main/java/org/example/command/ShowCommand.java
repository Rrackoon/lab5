package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.models.StudyGroup;
import org.example.utils.IOProvider;

public class ShowCommand extends Command {
    public ShowCommand(IOProvider provider, CollectionManager collection) {
        super("show", "вывести все элементы коллекции в строковом представлении", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        String line = "-".repeat(60);
        provider.getPrinter().print(line);
        for (StudyGroup studyGroup : collection.getCollection()) {
            provider.getPrinter().print(studyGroup.toString());
            provider.getPrinter().print(line);
        }
    }
}
