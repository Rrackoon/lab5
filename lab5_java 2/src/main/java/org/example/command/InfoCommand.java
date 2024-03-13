package org.example.command;


import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.utils.IOProvider;

public class InfoCommand extends Command {
    public InfoCommand(IOProvider provider, CollectionManager collection) {
        super("info", "вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)",
                provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        provider.getPrinter().print(collection.description());
    }


}
