package by.motolyha.mangaproject.controller.imagecontroller;

import by.motolyha.mangaproject.controller.imagecontroller.impl.ImageCommand;
import by.motolyha.mangaproject.controller.imagecontroller.impl.NotFoundCommand;

import java.util.EnumMap;

import static by.motolyha.mangaproject.controller.imagecontroller.CommandImageType.*;

public class CommandImageProvider {

    private static CommandImageProvider instance;
    private final EnumMap<CommandImageType, Command> commands = new EnumMap(CommandImageType.class);

    public static CommandImageProvider getInstance() {
        if (instance == null) {
            instance = new CommandImageProvider();
        }
        return instance;
    }

    public CommandImageProvider() {
        commands.put(IMAGE, new ImageCommand());

        commands.put(NOT_FOUND, new NotFoundCommand());
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(NOT_FOUND);
        }
        CommandImageType commandType;
        try {
            commandType = CommandImageType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = NOT_FOUND;
        }
        return commands.get(commandType);
    }
}
