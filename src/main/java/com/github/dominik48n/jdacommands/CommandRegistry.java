package com.github.dominik48n.jdacommands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private final Map<String, Command> commands = new HashMap<>();

    public void registerCommand(final Command command) {
        this.commands.put(command.getName().toLowerCase(), command);
    }

    public void unregisterCommand(final String name) {
        this.commands.remove(name.toLowerCase());
    }

    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(this.commands);
    }
}
