package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db.collection("Album")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(MainActivity.this, document.get("ID").toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this, document.get("Image").toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this, document.get("Name").toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this, document.get("Singer").toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.w("====>", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}