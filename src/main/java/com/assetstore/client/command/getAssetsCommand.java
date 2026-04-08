package com.assetstore.client.command;

import com.assetstore.client.command.exception.BadArgumentsException;

import java.util.List;

public class getAssetsCommand implements Command {

    @Override
    public boolean execute(List<String> args) throws BadArgumentsException {

        if(args.size() != 1) {
            throw new BadArgumentsException("Invalid number of arguments");
        }
        System.out.println("getAssetsCommand");
        return true;
    }
}
