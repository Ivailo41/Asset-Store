package com.assetstore.client.command;

import com.assetstore.client.command.exception.EmptyInputException;

import java.util.List;

public interface CommandParser {

    public List<String> parseCommand(String input) throws EmptyInputException;
}
