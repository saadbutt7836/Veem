package com.wgorganizaton.veem.Utility;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRef {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, UserRef;

    public static FirebaseUser GetUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public static String GetUserId() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static DatabaseReference GetDatabase() {
        return FirebaseDatabase.getInstance().getReference();
    }



}
