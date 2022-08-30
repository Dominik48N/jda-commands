package com.github.dominik48n.jdacommands;

import com.github.dominik48n.jdacommands.listener.MessageReceivedListener;
import com.github.dominik48n.jdacommands.listener.SlashCommandInteractionListener;
import net.dv8tion.jda.api.JDA;

public class JDACommands {

    private static JDACommands INSTANCE;

    private final CommandRegistry commandRegistry;
    private final JDA jda;

    private JDACommands(final JDA jda) {
        // Initialize variables
        this.commandRegistry = new CommandRegistry(jda);
        this.jda = jda;

        // Register listeners
        this.jda.addEventListener(new SlashCommandInteractionListener());
        this.jda.addEventListener(new MessageReceivedListener());

        // Define class instance
        INSTANCE = this;
    }

    public CommandRegistry getCommandRegistry() {
        return this.commandRegistry;
    }

    public JDA getJDA() {
        return this.jda;
    }

    /**
     * Initialize the jda commands.
     * Please Note: If the JDA Commands have already been initialized, this will not work a second time.
     *
     * @param jda the bot {@link JDA}
     * @return the initialized {@link JDACommands} class.
     */
    public static JDACommands init(final JDA jda) {
        if (INSTANCE != null) throw new IllegalStateException("JDACommands is already initialized.");
        return new JDACommands(jda);
    }

    public static JDACommands getInstance() {
        if (INSTANCE == null) throw new IllegalStateException("JDACommands is not initialized.");
        return INSTANCE;
    }
}
