package com.github.dominik48n.jdacommands;

import net.dv8tion.jda.api.JDABuilder;

public class JDACommands {

    private static JDACommands INSTANCE;

    private final CommandRegistry commandRegistry;
    private final JDABuilder jdaBuilder;

    private JDACommands(final JDABuilder jdaBuilder) {
        this.commandRegistry = new CommandRegistry();
        this.jdaBuilder = jdaBuilder;

        INSTANCE = this;
    }

    public CommandRegistry getCommandRegistry() {
        return this.commandRegistry;
    }

    public static JDACommands init(final JDABuilder jdaBuilder) {
        if (INSTANCE != null) throw new IllegalStateException("JDACommands is already initialized.");
        return new JDACommands(jdaBuilder);
    }

    public static JDACommands getInstance() {
        if (INSTANCE == null) throw new IllegalStateException("JDACommands is not initialized.");
        return INSTANCE;
    }
}
