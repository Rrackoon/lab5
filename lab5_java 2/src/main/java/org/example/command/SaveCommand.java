package org.example.command;

import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.utils.IOProvider;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command {
    public SaveCommand(IOProvider provider, CollectionManager collection) {
        super("save", "сохранить коллекцию в файл", provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 0);
        try (FileWriter fileWriter = new FileWriter(collection.getFileName())) {//открывает файл для записи
            fileWriter.write(collection.dump());//запись содержимого коллекции преобразованного в строку
            fileWriter.flush();//сброс буфера, чтобы убедиться что все записано в файл
            provider.getPrinter().printf("Collection dumped to file (%s) successfully.\n", System.getenv("FILENAME"));
        } catch (FileNotFoundException e) {
            provider.getPrinter().print("File not found or access denied (write)");
        } catch (IOException e) {
            provider.getPrinter().print("Something went wrong while writing.");
        }
    }
}
