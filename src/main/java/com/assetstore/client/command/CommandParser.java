package com.assetstore.client.command;

import java.util.List;

public interface CommandParser {

    public List<String> parseCommand(String input);
}
