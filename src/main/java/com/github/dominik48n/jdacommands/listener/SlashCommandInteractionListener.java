/*
 * Copyright 2022 Dominik48N
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.dominik48n.jdacommands.listener;

import com.github.dominik48n.jdacommands.Command;
import com.github.dominik48n.jdacommands.JDACommands;
import java.util.Arrays;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.ArrayUtils;

public class SlashCommandInteractionListener extends ListenerAdapter {

    public void onSlashCommandInteraction(final SlashCommandInteractionEvent event) {
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
