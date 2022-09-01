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

        for (final String alias : command.getAliases()) {
            this.commands.put(alias, command);
        }

        if (command.getPrefix().startsWith("/")) {
            this.jda.upsertCommand(name, command.getDescription()).queue();

            for (final String alias : command.getAliases()) {
                this.jda.upsertCommand(alias, command.getDescription()).queue();
            }
        }
    }

    /**
     * Unregister a command
     *
     * @param name the name of the command
     */
    public void unregisterCommand(String name) {
        name = name.toLowerCase();

        final Command command = this.commands.remove(name);

        if (command == null) return; // Command is not exists.

        for (final String alias : command.getAliases()) {
            this.commands.remove(alias);
        }
    }

    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(this.commands);
    }
}
