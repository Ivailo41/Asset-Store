package com.assetstore.client.command;

import com.assetstore.client.command.exception.CommandNotFoundException;
import com.assetstore.client.command.exception.EmptyInputException;

public interface CommandRegistry {

    public Command get(String name) throws CommandNotFoundException;
    public void register(String commandName, Command command) throws EmptyInputException;
}
