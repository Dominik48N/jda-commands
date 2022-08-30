package com.github.dominik48n.jdacommands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.dv8tion.jda.api.JDA;

public class CommandRegistry {

    private final Map<String, Command> commands = new HashMap<>();

    private final JDA jda;

    protected CommandRegistry(final JDA jda) {
        this.jda = jda;
    }

    /**
     * Register a new command
     *
     * @param command the {@link Command}
     */
    public void registerCommand(final Command command) {
        final String name = command.getName().toLowerCase();

        this.commands.put(name, command);
        if (command.getPrefix().startsWith("/")) this.jda.upsertCommand(name, command.getDescription()).queue();
    }

    /**
     * Unregister a command
     *
     * @param name the name of the command
     */
    public void unregisterCommand(String name) {
        name = name.toLowerCase();

        final Command command = this.commands.remove(name);
        if (command != null && command.getPrefix().startsWith("/")) this.jda.deleteCommandById(name).queue();
    }

    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(this.commands);
    }
}
