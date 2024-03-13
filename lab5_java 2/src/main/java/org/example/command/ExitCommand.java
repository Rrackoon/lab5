package org.example.command;

import org.example.exception.ExitException;
import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.utils.IOProvider;

public class ExitCommand extends Command {
    public ExitCommand(IOProvider provider, CollectionManager collection) {
        super("exit", "завершить программу (без сохранения в файл)", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        throw new ExitException();
    }
}
