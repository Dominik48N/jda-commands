package com.github.dominik48n.jdacommands;

import com.google.common.base.Preconditions;
import net.dv8tion.jda.api.entities.Role;

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

        // Define arguments
        this.name = name;
        this.prefix = prefix;
        this.description = description;
        this.aliases = aliases;
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
}
