package com.github.dominik48n.jdacommands.listener;

import com.github.dominik48n.jdacommands.Command;
import com.github.dominik48n.jdacommands.JDACommands;
import java.util.Arrays;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedListener extends ListenerAdapter {

    public void handle(final MessageReceivedEvent event) {
        final String message = event.getMessage().getContentStripped();
        final Command command = JDACommands.getInstance().getCommandRegistry().getCommands().get(message);

        if (command == null || command.getPrefix().startsWith("/"))
            // The command is not exists or a slash command!
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
        final String[] splitMessage = message.split(" ");
        command.execute(
            event.getGuild(),
            member,
            event.getChannel(),
            event.getGuildChannel(),
            Arrays.copyOfRange(splitMessage, 1, splitMessage.length)
        );
    }
}