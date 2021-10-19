package org.example;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class MongoTests {
    private CustomerDatabase CustomerDatabase;
    private CustomerDatabaseService CustomerDatabaseService;

    @BeforeEach
    void setUp() {
        CustomerDatabase = new CustomerDatabase();
        CustomerDatabaseService = mock(CustomerDatabaseService.class);
        CustomerDatabase.setCustomerService(CustomerDatabaseService);
    }

    //#mock
    @Test
    public void verifyStartConnection() throws IOException {
        CustomerDatabase mocked = mock(CustomerDatabase.class);
        mocked.startConnection();
        Mockito.verify(mocked).startConnection();
    }

    //#mock
    @Test
    public void verifyAddCustomer() throws IOException {
        CustomerDatabase mocked = mock(CustomerDatabase.class);
        mocked.addCustomer(1,"test",1);;
        Mockito.verify(mocked).addCustomer(1,"test",1);;
    }

    //#mock
    @Test
    public void verifyAddOrder() throws IOException {
        CustomerDatabase mocked = mock(CustomerDatabase.class);
        mocked.addOrder(1,1);
        Mockito.verify(mocked).addOrder(1,1);;
    }

    //#mock
    @Test
    public void verifyRetrieveCustomer() throws IOException {
        CustomerDatabase mocked = mock(CustomerDatabase.class);
        mocked.RetrieveCustomers();
        Mockito.verify(mocked).RetrieveCustomers();
    }

    //#fake
    @Test
    public void fakeRetrieve() {
        CustomerDatabaseService spy = spy(new CustomerDatabaseService());
        spy.startConnection();
        spy.addCustomer(1, "test", 1);
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase db = client.getDatabase("customer");
        MongoCollection<Document> collection = db.getCollection("Customer Info");
        FindIterable<Document> iterDoc = collection.find();
        StringBuilder store = new StringBuilder();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            store.append(it.next());
        }
        assertEquals(spy.RetrieveCustomers().toString(),store.toString());
        //cleanup
        collection.deleteOne(Filters.eq("id", 1));
    }


    //#fake
    @Test
    public void fakeCustomer(){
        //mock the customer service
        CustomerDatabaseService mocked = mock(CustomerDatabaseService.class);
        //creates customer database
        mocked.startConnection();
        //creates collection and adds customer
        mocked.addCustomer(1,"test",1);
        //connects to db
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        //gets customer database
        MongoDatabase db = client.getDatabase("customer");
        //gets customer info collection
        DBCollection coll = client.getDB("customer").getCollection("Customer Info");

        //asserts added customer successfully
        assertEquals(1, coll.findOne().get("id"));
        assertEquals("test", coll.findOne().get("Name"));
        assertEquals(1, coll.findOne().get("Total Amount of Goods Purchased"));

        //remove test customer
        BasicDBObject query = new BasicDBObject();
        query.append("id", 1);
        query.append("Name", "test");
        query.append("Total Amount of Goods Purchased", 1);
        coll.remove(query);

        client.close();
    }

    //#fake
    @Test
    public void fakeOrder(){
        //mock the customer service
        CustomerDatabaseService mocked = mock(CustomerDatabaseService.class);
        //creates customer database
        mocked.startConnection();
        //creates collection and adds customer
        mocked.addOrder(1,1);
        //connects to db
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        //gets customer info collection
        DBCollection coll = client.getDB("customer").getCollection("Order Info");

        //asserts added customer successfully
        assertEquals(1, coll.findOne().get("id"));
        assertEquals(1, coll.findOne().get("Total Amount of Goods Purchased"));

        //remove test customer
        BasicDBObject query = new BasicDBObject();
        query.append("id", 1);
        query.append("Total Amount of Goods Purchased", 1);
        coll.remove(query);

        client.close();
    }

}
