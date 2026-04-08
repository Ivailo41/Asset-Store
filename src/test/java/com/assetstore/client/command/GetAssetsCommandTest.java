package com.assetstore.client.command;

import com.assetstore.client.command.exception.BadArgumentsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetAssetsCommandTest {

    static CommandRegistry registry = new InMemoryCommandRegistry();
    static CommandParser parser = new CommandParserImpl();

    static Command getAssetsCommand = new getAssetsCommand();

    @BeforeAll
    static void beforeAll() throws Exception {
        registry.register("get-assets", getAssetsCommand);
    }

    @Test
    void testGetAssetsCommandResponse() {
        String commandString = "get-assets /test/asset";
        List<String> result = parser.parseCommand(commandString);
        assertTrue(registry.get(result.getFirst()).execute(result.subList(1, result.size())));
    }

    @Test
    void testGetAssetsCommandThrowsBadArgsException() {
        String commandString = "get-assets /test/asset bad arguments";
        List<String> result = parser.parseCommand(commandString);
        assertThrows(BadArgumentsException.class, () -> registry.get(result.getFirst()).execute(result.subList(1, result.size())));
    }
}
