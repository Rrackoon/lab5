package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.parser.CommandParser;
import org.example.utils.IOProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExecuteScriptCommand extends Command {

    private final int recDepth;
    public ExecuteScriptCommand(IOProvider provider, CollectionManager collection, int recDepth) {
        super("execute_script {file_name}", "считать и исполнить скрипт из указанного файла", provider, collection);
        this.recDepth = recDepth;
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);
        String fileName = args[0];
        try (FileReader fileReader = new FileReader(fileName)) {//открывается файл + создается экземпляр
            var provider = new IOProvider(new Scanner(fileReader), this.provider.getPrinter());
            var commandManager = new CommandManager(collection, provider, recDepth + 1);
            var commandParser = new CommandParser(commandManager, provider, recDepth + 1);
            commandParser.run();
        } catch (FileNotFoundException e) {
            provider.getPrinter().print("File not found or access denied (read).");
        } catch (IOException e) {
            provider.getPrinter().print("Something went wrong while reading.");
        }
    }
}