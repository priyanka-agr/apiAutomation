package com.tajawal.common.database;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import java.util.Arrays;

import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCursor;

import java.util.ArrayList;
import java.util.List;


public class MongoDbConfig {

    public static MongoClient mongoClient;
    public static MongoCredential credential;

    /**
     * @param userName
     * @param dbName
     * @param password
     * @param host
     * @param port
     * @return
     */

    public static MongoClient connectMongoDB(String userName, String password, String dbName, String host, int port) {
        credential = MongoCredential.createCredential(userName, dbName, password.toCharArray());

        mongoClient = new MongoClient(new ServerAddress(host), Arrays.asList(credential));

        return mongoClient;
    }


    public static void closeDBConnection(MongoClient mongoClient) {
        mongoClient.close();
        System.out.println("DB Connection closed");
    }


    public static String findDocumentWithEqualQuery(MongoClient mongoClient, String dbName, String collectionName, String queryName, String queryValue) {
        String data = null;
        MongoDatabase database = mongoClient.getDatabase(dbName);

        MongoCollection<Document> collection = database.getCollection(collectionName);

        System.out.println("Querying the DB:" + dbName + ". Collection is:" + collectionName);

        MongoCursor<Document> cursor = collection.find(eq(queryName, queryValue)).iterator();

        try {
            if (!cursor.hasNext()) {
                Assert.fail("Data Not Found for Field: " + queryName + ". Value: " + queryValue);
            } else {
                data = cursor.next().toJson();
            }
        } finally {
            cursor.close();
        }
        return data;
    }

}
