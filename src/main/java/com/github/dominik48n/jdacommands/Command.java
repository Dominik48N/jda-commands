package com.github.dominik48n.jdacommands;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.unions.GuildChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.GuildMessageChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public abstract class Command {

    /**
     * This is the command name.
     */
    private final String name;

    /**
     * Which prefix should be in front of the command?
     * Please make sure that if you use <code>/</code> as a prefix, the command is suggested from Discord.
     */
    private final String prefix;

    /**
     * If you use the <code>/</code> prefix, you can use this to provide a description that will be displayed below the command.
     */
    private final String description;

    /**
     * Use these command aliases.
     */
    private final String[] aliases;

    /**
     * These roles have access to this command. If this array is empty, anyone can access it.
     */
    private final Role[] access;

    public Command(final String name, final String prefix) {
        this(name, prefix, (String) null);
    }

    public Command(final String name, final String prefix, final String description) {
        this(name, prefix, description, new String[]{});
    }

    public Command(final String name, final String prefix, final Role... access) {
        this(name, prefix, null, access);
    }

    public Command(final String name, final String prefix, final String description, final String... aliases) {
        this(name, prefix, description, aliases, new Role[]{});
    }

    public Command(final String name, final String prefix, final String description, final Role... access) {
        this(name, prefix, description, new String[]{}, access);
    }

    public Command(final String name, final String prefix, final String description, final String[] aliases, final Role[] access) {
        // Check null arguments
        Preconditions.checkNotNull(name, "The name of a command cannot be null.");
        Preconditions.checkNotNull(prefix, "The prefix of a command cannot be null.");
        Preconditions.checkNotNull(aliases, "The aliases of a command cannot be null.");
        Preconditions.checkNotNull(access, "The access roles of a command cannot be null.");

        // Define arguments
        this.name = name;
        this.prefix = prefix;
        this.description = description != null ? description : "";
        this.aliases = Arrays.stream(aliases).map(String::toLowerCase).toArray(String[]::new);
        this.access = access;
    }

    public String getName() {
        return this.name;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public Role[] getAccess() {
        return this.access;
    }

    /**
     * This abstract method is executed when the command is entered.
     *
     * @param guild        the {@link Guild} the command was run on.
     * @param member       the command executor as {@link Member}
     * @param channel      the {@link MessageChannelUnion}
     * @param guildChannel the {@link GuildChannelUnion}
     * @param arguments    These are the arguments that were additionally specified.
     */
    public abstract void execute(
        final Guild guild,
        final Member member,
        final MessageChannelUnion channel,
        final GuildMessageChannelUnion guildChannel,
        final String[] arguments
    );
}
