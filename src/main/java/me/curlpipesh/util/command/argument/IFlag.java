package me.curlpipesh.util.command.argument;

/**
 * @author audrey
 * @since 1/24/16.
 */
@SuppressWarnings({"unchecked", "unused"})
public interface IFlag {
    String getName();

    String getShortName();

    /**
     * Whether or not this flag takes an argument with spaces in it. In the
     * event that it does, the 'spaced' argument has to be enclosed in quotes
     * (<tt>"</tt> characters).
     *
     * @return Whether or not this flag takes an argument with spaces in it
     */
    boolean takesSpacedArg();
}
