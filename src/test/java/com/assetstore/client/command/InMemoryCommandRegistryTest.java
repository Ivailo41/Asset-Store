package com.assetstore.client.command;

import com.assetstore.client.command.exception.CommandNotFoundException;
import com.assetstore.client.command.exception.EmptyInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InMemoryCommandRegistryTest {

    CommandRegistry registry = new InMemoryCommandRegistry();

    @Test
    void testRegisterCommand() {
        Command command = new GetAssetsCommand();
        registry.register("get-asset", command);
        assertEquals(command, registry.get("get-asset"));
    }

    @Test
    void testRegisterEmptyCommand() {
        Command command = new GetAssetsCommand();
        assertThrows(EmptyInputException.class, () -> registry.register("", command));
    }

    @Test
    void testGetInvalidCommand() {
        assertThrows(CommandNotFoundException.class, () -> registry.get("non-existent"));
    }
}
