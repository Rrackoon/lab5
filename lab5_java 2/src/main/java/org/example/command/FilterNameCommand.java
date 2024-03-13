package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.models.StudyGroup;

import org.example.utils.IOProvider;

import java.util.List;
import java.util.stream.Collectors;

public class FilterNameCommand extends Command {
    public FilterNameCommand(IOProvider provider, CollectionManager collection) {
        super("filter_contains_name {name}",
                "вывести элементы, значение поля name которых cодержит заданную подстроку",
                provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);
        String name = args[0];
        List<StudyGroup> studyGroups = CollectionManager.getCollection()
                .stream()//преобразование в поток эл-в
                .filter(flat -> flat.getName().toLowerCase().contains(name.toLowerCase()))//фильтр к каждому эл-ту
                .collect(Collectors.toList());
        provider.getPrinter().print("Collection filtered by name:");
        String line = "-".repeat(60);
        provider.getPrinter().print(line);
        for (StudyGroup studyGroup : studyGroups) {
            provider.getPrinter().print(studyGroup.toString());
            provider.getPrinter().print(line);
        }
    }
}
