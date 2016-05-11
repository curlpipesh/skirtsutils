package lgbt.audrey.util.database;


import lgbt.audrey.util.plugin.SkirtsPlugin;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author audrey
 * @since 8/23/15.
 */
public abstract class Database implements IDatabase {
    @Getter
    @Setter
    @SuppressWarnings("FieldMayBeFinal")
    private String databaseName;

    @Getter
    @Setter
    @SuppressWarnings("FieldMayBeFinal")
    private File databaseFile;

    @Getter
    @Setter
    @SuppressWarnings("FieldMayBeFinal")
    private Connection connection;

    @Getter
    private final SkirtsPlugin plugin;

    @Getter
    @Setter
    @SuppressWarnings("FieldMayBeFinal")
    private boolean connected;

    @Getter
    private final List<Runnable> initializationTasks;

    public Database(@NonNull final SkirtsPlugin plugin, @NonNull final String databaseName) {
        this.databaseName = databaseName;
        this.plugin = plugin;
        databaseFile = new File(plugin.getDataFolder() + File.separator + databaseName + ".db");
        initializationTasks = new CopyOnWriteArrayList<>();
    }

    public boolean addInitTask(@NonNull final Runnable task) {
        return initializationTasks.add(task);
    }

    public final boolean execute(@NonNull final PreparedStatement s) {
        try {
            final boolean state = s.execute();
            s.close();
            return state;
        } catch(final SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
