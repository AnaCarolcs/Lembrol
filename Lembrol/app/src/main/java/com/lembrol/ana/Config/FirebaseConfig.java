package com.lembrol.ana.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FirebaseConfig {

    private static DatabaseReference firebaseReference;
    private static FirebaseAuth authentication;

    public static DatabaseReference getFirebase(){

        if(firebaseReference == null){
            firebaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return firebaseReference;
    }

    public static FirebaseAuth getAuthenticationFirebase(){

        if(authentication == null){
            authentication = FirebaseAuth.getInstance();
        }
        return authentication;
    }
}
