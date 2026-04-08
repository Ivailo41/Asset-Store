package com.assetstore.client.command;

import com.assetstore.client.command.exception.BadArgumentsException;

import java.util.List;

public interface Command {

    boolean execute(List<String> args) throws BadArgumentsException;
}
