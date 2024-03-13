package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.models.StudyGroup;
import org.example.parser.SGParser;
import org.example.utils.IOProvider;

public class UpdateCommand extends Command {
    public UpdateCommand(IOProvider provider, CollectionManager collection) {
        super("update {id} {element}", "обновить значение элемента коллекции, id которого равен заданному",
                provider, collection);
    }


    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);

        long studyGroupId = Long.parseLong(args[0]);
        StudyGroup studyGroup = collection.get(studyGroupId);
        if (studyGroup == null) {
            provider.getPrinter().print("StudyGroup with specified ID doesn't exist.");
            return;
        }
        String line = "-".repeat(60);
        provider.getPrinter().print("Chosen StudyGroup:");
        provider.getPrinter().printf("%s\n%s\n%s\n", line, studyGroup, line);
        SGParser argParser = new SGParser(provider.getScanner(), provider.getPrinter());
        StudyGroup newStudyGroup = argParser.parseStudyGroup();
        collection.update(studyGroupId, newStudyGroup);
        provider.getPrinter().printf("StudyGroup (ID %s) updated successfully.\n", studyGroupId);
    }
}
