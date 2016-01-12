package me.curlpipesh.util.database;

import me.curlpipesh.util.plugin.SkirtsPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Interface used for interacting with a database.
 *
 * @author audrey
 * @since 8/28/15.
 */
@SuppressWarnings("unused")
public interface IDatabase {
    /**
     * Connect to the database
     *
     * @return <tt>true</tt> if the connection worked, <tt>false</tt>
     * otherwise.
     */
    boolean connect();

    /**
     * Disconnect from the database
     *
     * @return <tt>true</tt> if the disconnection worked, <tt>false</tt>
     * otherwise.
     */
    boolean disconnect();

    /**
     * Initialize the database. Used for creating the tables and running all
     * initialization tasks.
     *
     * @return Whether or not the database initialized successfully.
     */
    boolean initialize();

    /**
     * The tasks that the class should run when {@link #initialize()} is
     * called.
     *
     * @return The list of initialization tasks.
     */
    List<Runnable> getInitializationTasks();

    /**
     * Adds an initialization task to this database
     *
     * @param task The task to add
     *
     * @return <tt>true</tt> if the task was added, <tt>false</tt> otherwise.
     */
    boolean addInitTask(Runnable task);

    /**
     * Returns whether or not the database driver for this database exists.
     *
     * @return <code>true</code> if the driver exists, <code>false</code>
     * otherwise.
     */
    boolean doesDriverExist();

    /**
     * Returns the connection to the database
     *
     * @return The connection to the database
     */
    Connection getConnection();

    /**
     * The name of the actual database. Also used for DB file names in
     * {@link me.curlpipesh.util.database.impl.SQLiteDatabase}.
     *
     * @return The database name
     */
    String getDatabaseName();

    /**
     * Sets the database name
     *
     * @param name The new name to use
     */
    void setDatabaseName(String name);

    /**
     * Returns the file being used for the database. Only for
     * {@link me.curlpipesh.util.database.impl.SQLiteDatabase}.
     *
     * TODO: This is created for {@link me.curlpipesh.util.database.impl.MySQLDatabase} anyway
     *
     * @return The file being used for the database.
     */
    File getDatabaseFile();

    /**
     * The plugin that is using this database
     *
     * @return The plugin using this database
     */
    SkirtsPlugin getPlugin();

    /**
     * Executes the given statement
     *
     * @param s The statement to execute
     * @return <tt>true</tt> if the query worked, <tt>false</tt> otherwise.
     */
    boolean execute(PreparedStatement s);
}
