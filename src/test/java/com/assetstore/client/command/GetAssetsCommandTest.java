package com.assetstore.client.command;

import com.assetstore.client.command.exception.BadArgumentsException;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetAssetsCommandTest {

    static Command getAssetsCommand = new GetAssetsCommand();

    @Test
    void testGetAssetsCommandResponse() {
        List<String> commandList = List.of("get-assets", "/test/asset");
        assertTrue(getAssetsCommand.execute(commandList.subList(1, commandList.size())));
    }

    @Test
    void testGetAssetsCommandThrowsBadArgsException() {
        List<String> commandList = List.of("get-assets", "/test/asset bad", "arguments");
        assertThrows(BadArgumentsException.class, () -> getAssetsCommand.execute(commandList.subList(1, commandList.size())));
    }
}
