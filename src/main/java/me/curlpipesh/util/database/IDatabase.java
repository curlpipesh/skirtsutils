package me.curlpipesh.util.database;

import me.curlpipesh.util.plugin.SkirtsPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author audrey
 * @since 8/28/15.
 */
public interface IDatabase {
    boolean connect();

    boolean disconnect();

    boolean initialize();

    List<Runnable> getInitializationTasks();

    boolean addInitTask(Runnable task);

    boolean doesDriverExist();

    Connection getConnection();

    String getDatabaseName();

    void setDatabaseName(String name);

    File getDatabaseFile();

    SkirtsPlugin getPlugin();

    boolean execute(PreparedStatement s);
}
