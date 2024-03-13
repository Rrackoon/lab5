package org.example.command;
import org.example.models.*;
import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.utils.IOProvider;

public class CountLesAdminNameCommand extends Command {
    public CountLesAdminNameCommand(IOProvider provider, CollectionManager collection) {
        super("count_less_than_group_admin {name}",
                "вывести количество элементов, значение поля groupAdmin которых меньше заданного",
                provider, collection);
    }

    @Override
    public void execute(String[] args) throws InvalidArgsException {
        validateArgs(args, 1);
        String name = args[0];//получение имени
        String line = "-".repeat(60);//создание строки на 60 симв
        provider.getPrinter().print(line);//для разделительной линии
        Long count=0L;
        for (StudyGroup studyGroup : collection.getCollection()) {
            if (name.compareTo(studyGroup.getGroupAdmin().getName()) >0 ){
                count++;
            }

        }
        line=" count: "+count;
        provider.getPrinter().print(line);
    }
}
