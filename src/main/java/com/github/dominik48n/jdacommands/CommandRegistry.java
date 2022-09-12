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
