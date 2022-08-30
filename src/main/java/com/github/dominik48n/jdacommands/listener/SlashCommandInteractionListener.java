package com.github.dominik48n.jdacommands.listener;

import com.github.dominik48n.jdacommands.Command;
import com.github.dominik48n.jdacommands.JDACommands;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.ArrayUtils;

public class SlashCommandInteractionListener extends ListenerAdapter {

    public void handle(final SlashCommandInteractionEvent event) {
        final Command command = JDACommands.getInstance().getCommandRegistry().getCommands().get(event.getName().toLowerCase());

        if (command == null || !command.getPrefix().startsWith("/"))
            // The command is not a slash command!
            return;

        // Execute the command
        command.execute(
            event.getGuild(),
            event.getMember(),
            event.getChannel(),
            event.getGuildChannel(),
            ArrayUtils.addAll(
                event.getSubcommandGroup() != null ? event.getSubcommandGroup().split(" ") : new String[] {},
                event.getSubcommandName() != null ? event.getSubcommandName().split(" ") : new String[] {}
            )
        );
    }
}
