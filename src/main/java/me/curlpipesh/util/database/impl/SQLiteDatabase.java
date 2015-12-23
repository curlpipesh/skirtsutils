package me.curlpipesh.util.database.impl;

import lombok.Getter;
import lombok.NonNull;
import me.curlpipesh.util.database.Database;
import me.curlpipesh.util.plugin.SkirtsPlugin;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author audrey
 * @since 8/23/15.
 */
@SuppressWarnings("Duplicates")
public class SQLiteDatabase extends Database {
    @Getter
    private final String initializationStatement;

    public SQLiteDatabase(@NonNull SkirtsPlugin plugin, @NonNull String dbName, @NonNull String initializationStatement) {
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
                setConnection(DriverManager.getConnection("jdbc:sqlite:" + getDatabaseFile().getPath()));
                getConnection().setAutoCommit(true);
                setConnected(true);
                return true;
            } catch(SQLException e) {
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
        } catch(SQLException e) {
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
            Statement create = getConnection().createStatement();
            create.execute(initializationStatement);
            create.close();
            created = true;
        } catch(SQLException e) {
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
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
