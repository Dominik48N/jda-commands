package com.github.dominik48n.jdacommands.listener;

import com.github.dominik48n.jdacommands.Command;
import com.github.dominik48n.jdacommands.JDACommands;
import java.util.Arrays;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.ArrayUtils;

public class SlashCommandInteractionListener extends ListenerAdapter {

    public void handle(final SlashCommandInteractionEvent event) {
        final Command command = JDACommands.getInstance().getCommandRegistry().getCommands().get(event.getName().toLowerCase());

        if (command == null || !command.getPrefix().startsWith("/"))
            // The command is not a slash command!
            return;

        // Check if member has access
        final Member member = event.getMember();
        if (member == null) return; // The member cannot be null
        if (command.getAccess().length != 0
            && member.getRoles().stream().filter(role -> Arrays.stream(command.getAccess()).anyMatch(role1 -> role1 == role)).count() <= 0L
        )
            // Member has no access of to the command
            return;

        // Execute the command
        command.execute(
            event.getGuild(),
            member,
            event.getChannel(),
            event.getGuildChannel(),
            ArrayUtils.addAll(
                event.getSubcommandGroup() != null ? event.getSubcommandGroup().split(" ") : new String[] {},
                event.getSubcommandName() != null ? event.getSubcommandName().split(" ") : new String[] {}
            )
        );
    }
}
