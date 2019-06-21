package com.kamores.tiffin;

public class ServerRequest {
    private String operation;
    private String email;
    private User user;
    Suppliers suppliers;
    Items items;

    public void setItems(Items items) {
        this.items = items;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}