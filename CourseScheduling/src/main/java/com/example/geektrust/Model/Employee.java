package com.example.geektrust.Model;

public class Employee {
    String emailId;
    String name;

    public Employee( String emailId )
    {
        this.emailId = emailId;
        this.name = this.emailId.substring(0, this.emailId.indexOf('@'));
    }

    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
