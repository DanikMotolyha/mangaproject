package by.motolyha.mangaproject.controller.command;

import by.motolyha.mangaproject.controller.command.impl.LogOutCommand;
import by.motolyha.mangaproject.controller.command.impl.SignUpCommand;
import by.motolyha.mangaproject.controller.command.impl.SingInCommand;
import by.motolyha.mangaproject.controller.command.impl.go.*;

import java.util.EnumMap;

import static by.motolyha.mangaproject.controller.command.CommandType.*;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public CommandProvider() {
        commands.put(GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(GO_TO_SIGN_IN_PAGE, new GoToSignInPageCommand());
        commands.put(GO_TO_SIGN_UP_PAGE, new GoToSignUpPageCommand());
        commands.put(GO_TO_PROFILE_PAGE, new GoToProfilePageCommand());
        commands.put(GO_TO_PAGE_NOT_FOUND, new GoToPageNotFoundCommand());
        commands.put(GO_TO_SIGN_UP_SUCCESS_PAGE, new GoToSignUpSuccessPageCommand());

        commands.put(SIGN_IN, new SingInCommand());
        commands.put(SIGN_UP, new SignUpCommand());
        commands.put(LOG_OUT, new LogOutCommand());
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(GO_TO_PAGE_NOT_FOUND);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = GO_TO_PAGE_NOT_FOUND;
        }
        return commands.get(commandType);
    }
}
