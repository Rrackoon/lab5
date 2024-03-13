package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.models.StudyGroup;
import org.example.parser.SGParser;
import org.example.utils.IOProvider;

public class AddIfMinCommand extends Command {
    public AddIfMinCommand(IOProvider provider, CollectionManager collection) {
        super("add_if_max",
                "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции",
                provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        SGParser parser = new SGParser(provider.getScanner(), provider.getPrinter());
        StudyGroup studyGroup = parser.parseStudyGroup();
        //обработка конца файла
        StudyGroup minStudyGroup = collection.min();
        if (minStudyGroup.getStudentsCount() <= studyGroup.getStudentsCount()) {
            provider.getPrinter().printf("StudyGroup (value: %s) not added (%s).\n",
                    studyGroup.getStudentsCount(), minStudyGroup.getStudentsCount());
            return;
        }
        collection.push(studyGroup);
        provider.getPrinter().printf("StudyGroup (ID %s) added successfully.\n", studyGroup.getID());
    }
}
