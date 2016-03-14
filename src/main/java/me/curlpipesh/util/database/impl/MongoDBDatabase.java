package me.curlpipesh.util.database.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.Setter;
import me.curlpipesh.util.plugin.SkirtsPlugin;
import org.bson.Document;

/**
 * We don't extend {@link me.curlpipesh.util.database.Database} here because
 * that class expects a SQL-type database, not a "NoSQL" database like MongoDB.
 * Instead, we make the basic non-SQL-based 'wrapper' around the normal Mongo
 * methods here, and expose as much as we can to allow for easier external use.
 * <p />
 * Using this class means that you need to have a section in your plugin's
 * <tt>config.yml</tt> that looks like the following: <br />
 * <code>
 * mongo:
 *   host: mongo.example.com
 *   port: 42069
 *   dbname: plugindb
 * </code>
 *
 * @author audrey
 * @since 3/14/16.
 */
@SuppressWarnings("unused")
public class MongoDBDatabase {
    @Getter
    @Setter
    @SuppressWarnings("FieldMayBeFinal")
    private String databaseName;

    @Getter
    private MongoClient mongoClient;
    @Getter
    private MongoDatabase mongoDatabase;

    @Getter
    private final SkirtsPlugin plugin;

    public MongoDBDatabase(final SkirtsPlugin plugin) {
        this.plugin = plugin;
        databaseName = plugin.getConfig().getString("mongo.dbname");
    }

    public boolean connect() {
        mongoClient = new MongoClient(plugin.getConfig().getString("mongo.host"),
                plugin.getConfig().getInt("mongo.port"));
        mongoDatabase = mongoClient.getDatabase(databaseName);
        return true;
    }

    public void addDocument(final String collection, final Document document) {
        mongoDatabase.getCollection(collection).insertOne(document);
    }

    public MongoCollection<Document> getCollection(final String collection) {
        return mongoDatabase.getCollection(collection);
    }

    public FindIterable<Document> getAll(final String collection) {
        return mongoDatabase.getCollection(collection).find();
    }

    @SuppressWarnings("Duplicates")
    public boolean doesDriverExist() {
        try {
            Class.forName("com.mongodb.MongoClient");
            return true;
        } catch(final ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
