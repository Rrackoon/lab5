package org.example.managers;
import org.example.utils.IOProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;
import org.example.exception.InvalidArgsException;
import org.example.managers.CollectionManager;
import org.example.models.StudyGroup;
import org.example.models.Person;
import org.example.utils.IOProvider;
import org.example.command.*;
public class CommandManager {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandManager(CollectionManager collection, IOProvider provider, int recDepth) {
        register("info", new InfoCommand(provider, collection));
        register("show", new ShowCommand(provider, collection));
        register("add", new AddCommand(provider, collection));
        register("update", new UpdateCommand(provider, collection));
        register("remove_by_id", new RemoveByIdCommand(provider, collection));
        register("clear", new ClearCommand(provider, collection));
        register("save", new SaveCommand(provider, collection));
        register("exit", new ExitCommand(provider, collection));
        register("remove_first", new RemoveFirstCommand(provider, collection));
        register("add_if_min", new AddIfMinCommand(provider, collection));
        register("print_ascending", new PrintAscendingCommand(provider, collection));
        register("filter_contains_name", new FilterNameCommand(provider, collection));
        register("count_less_than_group_admin", new CountLesAdminNameCommand(provider, collection));
        register("execute_script", new ExecuteScriptCommand(provider, collection, recDepth));
        register("help", new HelpCommand(provider, collection, commands));
    }


    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public boolean execute(String commandName, String[] args) throws InvalidArgsException {
        if (commands.containsKey(commandName)) {
            commands.get(commandName).execute(args);
            return true;
        }
        return false;
    }
}
