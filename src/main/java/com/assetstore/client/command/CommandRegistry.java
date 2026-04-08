package com.assetstore.client.command;

import com.assetstore.client.command.exception.CommandNotFoundException;

public interface CommandRegistry {

    public Command get(String name) throws CommandNotFoundException;
    public void register(String commandName, Command command);
}
