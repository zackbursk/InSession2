package org.example;

// used MongoDB - Version 3.4.3 - PortNumber localhost:27017 - link: https://docs.mongodb.com/manual/installation/

public class CustomerDatabase {
    private CustomerDatabaseService CustomerDatabaseService;

    public void setCustomerService (CustomerDatabaseService CustomerDatabaseService){
        this.CustomerDatabaseService = CustomerDatabaseService;
    }

    public void startConnection() {
        CustomerDatabaseService.startConnection();
    }

    public void addCustomer(int id, String name, int amount) {
        CustomerDatabaseService.addCustomer(id,name,amount);
    }

    public void addOrder(int customerId, int amount){
        CustomerDatabaseService.addOrder(customerId,amount);
    }

    public void RetrieveCustomers(){
        CustomerDatabaseService.RetrieveCustomers();
    }
}
