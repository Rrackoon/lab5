package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.models.StudyGroup;
import org.example.parser.SGParser;
import org.example.utils.IOProvider;

public class AddCommand extends Command {
    public AddCommand(IOProvider provider, CollectionManager collection) {
        super("add", "добавить новый элемент в коллекцию", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        SGParser parser = new SGParser(provider.getScanner(), provider.getPrinter());
        StudyGroup studyGroup = parser.parseStudyGroup();
        //обработка конца файла
        collection.push(studyGroup);
        provider.getPrinter().printf("StudyGroup (ID %s) added successfully.\n", studyGroup.getId());

    }
}
