package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomerDatabaseService {
    public static MongoClient client;
    public static MongoDatabase db;

    public static void startConnection(){
        client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        db = client.getDatabase("customer");
    }

    public static void addCustomer(int id, String name, int amount){
        Document doc = new Document("id", id)
                .append("Name", name)
                .append("Total Amount of Goods Purchased", amount);
        db.getCollection("Customer Info").insertOne(doc);
    }

    public static void addOrder(int customerId, int amount){
        Document doc = new Document("id", customerId)
                .append("Total Amount of Goods Purchased", amount);
        db.getCollection("Order Info").insertOne(doc);
    }

    public static StringBuilder RetrieveCustomers(){
        MongoCollection<Document> collection = db.getCollection("Customer Info");
        //Retrieving the documents
        FindIterable<Document> iterDoc = collection.find();
        StringBuilder store = new StringBuilder();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            store.append(it.next());
        }
        System.out.println(store);
        return store;
    }
}
