package org.example.utils;

import com.google.gson.*;
import org.example.interfaces.Printer;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;

import java.util.Scanner;

public class IOProvider {
    Printer printer;
    //для вывода сообщений пользователю
    Scanner scanner;

    public IOProvider(Scanner scanner, Printer printer) {
        this.scanner = scanner;
        this.printer = printer;
    }

    public Printer getPrinter() {
        return printer;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void closeScanner() {scanner.close();}
}
