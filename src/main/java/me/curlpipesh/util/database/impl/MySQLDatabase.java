package me.curlpipesh.util.database.impl;

import lombok.Getter;
import lombok.NonNull;
import me.curlpipesh.util.database.Database;
import me.curlpipesh.util.plugin.SkirtsPlugin;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author TehNeon
 * @since 12/22/15.
 */
@SuppressWarnings({"Duplicates", "unused"})
public class MySQLDatabase extends Database {
    @Getter
    private final String initializationStatement;

    @Getter
    private final String host;
    @Getter
    private final String username;
    @Getter
    private final String password;
    @Getter
    private final int port;

    public MySQLDatabase(@NonNull final SkirtsPlugin plugin, @NonNull final String host,
                         @NonNull final String databaseName, @NonNull final int port, @NonNull final String username,
                         @NonNull final String password, @NonNull final String initializationStatement) {
        super(plugin, databaseName);
        this.initializationStatement = initializationStatement;
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    @Override
    public boolean connect() {
        if (doesDriverExist()) {
            try {
                setConnection(DriverManager.getConnection("jdbc:mysql://" + getHost() + ':' + getPort() + '/' + getDatabaseName(), getUsername(), getPassword()));
                setConnected(true);
                return true;
            } catch (final SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            getPlugin().getLogger().warning("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            getPlugin().getLogger().warning("MySQL DB driver doesn't exist! Do NOT expect any sort of functionality!!");
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
        } catch (final SQLException e) {
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
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        getInitializationTasks().forEach(Runnable::run);

        return created;
    }

    @Override
    public boolean doesDriverExist() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}