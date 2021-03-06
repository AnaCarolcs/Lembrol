package com.lembrol.ana.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.lembrol.ana.Config.FirebaseConfig;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;

    public User(){

    }

    public void save(){
        DatabaseReference databaseReference = FirebaseConfig.getFirebase();
        databaseReference.child("user").child(getId()).setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
