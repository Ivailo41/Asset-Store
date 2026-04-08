package com.assetstore.client.command;

import com.assetstore.client.command.exception.CommandNotFoundException;
import com.assetstore.client.command.exception.EmptyInputException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryCommandRegistry implements CommandRegistry {

    private final Map<String, Command> commands;

    public InMemoryCommandRegistry() {
        commands = new HashMap<>();
    }

    @Override
    public Command get(String name) throws CommandNotFoundException {
        return Optional.ofNullable(commands.get(name)).orElseThrow(() -> new CommandNotFoundException(name));
    }

    @Override
    public void register(String commandName, Command command) throws EmptyInputException {
        if(commandName.isBlank()) {
            throw new EmptyInputException("Command name cannot be blank");
        }
        commands.putIfAbsent(commandName, command);
    }
}
