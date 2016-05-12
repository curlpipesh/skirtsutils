package lgbt.audrey.util.database.impl;

import lombok.Getter;
import lombok.NonNull;
import lgbt.audrey.util.database.Database;
import lgbt.audrey.util.plugin.AudreyPlugin;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * SQLite database implementation
 *
 * @author audrey
 * @since 8/23/15.
 */
@SuppressWarnings({"Duplicates", "unused"})
public class SQLiteDatabase extends Database {
    @Getter
    private final String initializationStatement;

    public SQLiteDatabase(@NonNull final AudreyPlugin plugin, @NonNull final String dbName,
                          @NonNull final String initializationStatement) {
        super(plugin, dbName);
        this.initializationStatement = initializationStatement;
    }

    @Override
    public boolean connect() {
        if(!getDatabaseFile().exists()) {
            getPlugin().getLogger().warning("SQLite DB \"" + getDatabaseName() + "\" doesn't exist, creating...");
        }
        if(doesDriverExist()) {
            try {
                final Properties properties = new Properties();
                properties.setProperty("allowMultiQueries", "true");
                setConnection(DriverManager.getConnection("jdbc:sqlite:" + getDatabaseFile().getPath() + "", properties));
                getConnection().setAutoCommit(true);
                setConnected(true);
                return true;
            } catch(final SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            getPlugin().getLogger().warning("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            getPlugin().getLogger().warning("SQLite DB driver doesn't exist! Do NOT expect any sort of functionality!!");
            getPlugin().getLogger().warning("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        boolean state;
        try {
            getConnection().close();
            setConnected(false);
            state = true;
        } catch(final SQLException e) {
            e.printStackTrace();
            state = false;
        }
        return state;
    }

    @Override
    @SuppressWarnings("SqlNoDataSourceInspection")
    public boolean initialize() {
        boolean created = false;
        try {
            final Statement create = getConnection().createStatement();
            create.execute(initializationStatement);
            create.close();
            created = true;
        } catch(final SQLException e) {
            e.printStackTrace();
        }

        getInitializationTasks().forEach(Runnable::run);

        return created;
    }

    @Override
    public boolean doesDriverExist() {
        try {
            Class.forName("org.sqlite.JDBC");
            return true;
        } catch(final ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
