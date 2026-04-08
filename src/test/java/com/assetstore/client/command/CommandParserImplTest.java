package com.assetstore.client.command;

import com.assetstore.client.command.exception.EmptyInputException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserImplTest {

    CommandParser commandParser = new CommandParserImpl();

    @Test
    void testSimpleCommandTokenization() {
        String command = "This is a command";
        List<String> result = commandParser.parseCommand(command);
        assertEquals(4, result.size());
    }

    @Test
    void testCommandWithQuotesTokenization() {
        String command = "send-message -quoted \"Hello World\"";
        List<String> result = commandParser.parseCommand(command);
        assertEquals(3, result.size());
    }

    @Test
    void testCommandWithEmptyString() {
        String command = "";
        assertThrows(EmptyInputException.class, () -> commandParser.parseCommand(command));
    }

    @Test
    void testCommandWithBlankString() {
        String command = "   ";
        assertThrows(EmptyInputException.class, () -> commandParser.parseCommand(command));
    }
}
