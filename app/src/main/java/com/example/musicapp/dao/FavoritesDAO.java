package com.example.musicapp.dao;

import static android.os.Build.ID;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.musicapp.Services.CallBack.SucessCallBack;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class FavoritesDAO {
    Context context;

    public FavoritesDAO(Context context) {
        this.context = context;
    }

    String TAG = "test";

    //thêm mục yêu thích
    public void addItemFavorites(String UserID, String songID, final SucessCallBack sucessCallBack) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("Users").document(UserID);
        final DocumentReference documentReference_song = db.collection("SongID").document(songID);

        documentReference
                .update("favorites", FieldValue.arrayUnion(songID)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.w(TAG, "DocumentSnapshot successfully updated!");
                // tang them 1like
                documentReference_song
                        .update("Like", FieldValue.increment(1))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                                ToastAnnotation("Bài hát đã thêm vào mục Yêu Thích của bạn");
                                sucessCallBack.getCallBack(true);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                                sucessCallBack.getCallBack(false);
                            }
                        });
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        sucessCallBack.getCallBack(false);

                    }
                });
    }

    // remove Item
    public void removeItemFavorites(String UserID, String songID, final SucessCallBack sucessCallBack) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("Users").document(UserID);
        final DocumentReference documentReference_song = db.collection("songID").document(songID);

        documentReference.update("favorites", FieldValue.arrayRemove(songID)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                // tang them 1like
                documentReference_song.update("Like", FieldValue.increment(-1))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "documentSnapshot successfully updated!");
                                ToastAnnotation("Bài hát đã được xóa khỏi mục yêu thích");
                                sucessCallBack.getCallBack(true);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        sucessCallBack.getCallBack(false);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error updating document", e);
                sucessCallBack.getCallBack(false);
            }
        });
    }

    private void ToastAnnotation(String mesage) {
        Toast.makeText(context, mesage, Toast.LENGTH_SHORT).show();
    }
}
