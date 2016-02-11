package me.curlpipesh.util.command.argument;

import lombok.Getter;

/**
 * @author audrey
 * @since 1/24/16.
 */
@SuppressWarnings("unused")
public class Flag implements IFlag {
    @Getter
    private final String name;

    @Getter
    private final String shortName;

    private final boolean takesSpacedArg;

    public Flag(final String name, final boolean takesSpacedArg) {
        this(name, null, takesSpacedArg);
    }

    public Flag(final String name, final String shortName, final boolean takesSpacedArg) {
        this.name = name;
        this.shortName = shortName;
        this.takesSpacedArg = takesSpacedArg;
    }

    @Override
    public boolean takesSpacedArg() {
        return takesSpacedArg;
    }
}
