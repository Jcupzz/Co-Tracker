package com.jcupzz.mycoviddiary;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Uploadtodatabase {
    String pass, email, uid;
    private FirebaseFirestore db;

    Uploadtodatabase(String pass, String email, String uid) {

        this.email = email;
        this.pass = pass;
        this.uid = uid;


        Map<String, Object> userdata = new HashMap<>();
        userdata.put("pass", pass);
        userdata.put("email", email);
        userdata.put("uid", uid);


        db = FirebaseFirestore.getInstance();
        db.collection(email).document("userdetails").collection("authdetails").document().set(
                userdata
        );


    }

}
