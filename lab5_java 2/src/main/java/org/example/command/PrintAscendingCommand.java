package org.example.command;
import org.example.interfaces.Printer;
import org.example.models.*;
import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.utils.IOProvider;

import java.util.List;

public class PrintAscendingCommand extends Command {
    public PrintAscendingCommand(IOProvider provider, CollectionManager collection) {
        super("print_ascending", "вывести все элементы коллекции по возрастанию", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);

        List<StudyGroup> studyGroups = collection.getCollection(); // Получаем список групп из вашей коллекции

        for (StudyGroup group : studyGroups) {
            provider.getPrinter().print(group.toString()); // Выводим каждую группу
        }
    }
}
