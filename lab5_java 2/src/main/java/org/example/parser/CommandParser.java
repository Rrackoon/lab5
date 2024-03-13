package org.example.parser;

import org.example.exception.*;
import org.example.utils.IOProvider;


import org.example.managers.CommandManager;
import org.example.utils.IOProvider;
import org.example.interfaces.Printer;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandParser extends DefaultTypeParser {
    private final CommandManager commandManager;
    private final IOProvider provider;
    private final static int MAX_REC_DEPTH = 5;
    private final int recDepth;

    public CommandParser(CommandManager commandManager, IOProvider provider, int recDepth) {
        super(provider.getScanner(), provider.getPrinter());
        this.commandManager = commandManager;
        this.provider = provider;
        this.recDepth = recDepth;
    }
    //выполнение обработки команд
    public void run() {
        Scanner scanner = provider.getScanner();
        Printer printer = provider.getPrinter();
        // чтобы рекурсия не превышала макс значение
        if (recDepth > MAX_REC_DEPTH) {
            throw new RecursionException();
        }
    //обработка кроманд пользователя
        while (true) {
            try {
                printer.printf("Enter command:\n");
                String line = scanner.nextLine();
                String[] splitLine = line.strip().split("\\s+");//разделение на массив строк
                String commandName = splitLine[0].toLowerCase();//приведение к нижнему регистру
                String[] args = Arrays.copyOfRange(splitLine, 1, splitLine.length);//получение аргументов команд
                if (!commandManager.execute(commandName, args)) {
                    printer.print("Invalid command");
                }
            } catch (InterruptCommandException e) {
                printer.print("\nExited\n");
            } catch (InvalidArgsException e) {
                printer.print("Invalid arguments. Use command \"help\" to find correct ones.");
            } catch (NoSuchElementException e) {
                printer.print("EOF");
                break;
            } catch (RecursionException e) {
                printer.print("Recursion depth exceeded!");
                break;
            } catch (ExitException e) {
                provider.closeScanner();
                provider.getPrinter().print("Program has finished. Good luck!");
                break;
            } catch (Exception e) {
                printer.print("Error occurred: " + e);
            }
        }
    }
}


