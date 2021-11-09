package com.example.musicapp.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.musicapp.Services.CallBack.SongCallBack;
import com.example.musicapp.model.Song;
import com.example.musicapp.model.SongIDList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

public class SongsDAO {
    Context context;

    public SongsDAO(Context context) {
        this.context = context;
    }

    Song song = new Song();
    ArrayList<Song> suggestList = new ArrayList<>(); //đề xuất danh sách
    ArrayList<Song> ranktList = new ArrayList<>();//Danh sách xếp hạng
    ArrayList<Song> songIdList = new ArrayList<>();//danh sách bài hát
    ArrayList<Song> songNewList = new ArrayList<>();// Gửi danh sách mới

    public void getSuggest(final SongCallBack songCallBack) {
        // Truy cập phiên bản Cloud Firestore từ Hoạt động của bạn
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Songs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
//                                Log.d("suggesst", snapshot.getId() + " => " + snapshot.getData());
                        song = snapshot.toObject(Song.class);
                        suggestList.add(song);
                    } // handle random
                    int songListSize = suggestList.size();
                    ArrayList<Song> randomsongList = new ArrayList<>();
                    for (int i = 0; i < songListSize; i++) {
                        Song randomsong = suggestList.get(new Random().nextInt(songListSize));
                        if (!randomsongList.contains(randomsong)) {
                            randomsongList.add(randomsong);
                            if (randomsongList.size() == 5) {
                                break;
                            }
                        }
                    }
                    songCallBack.getCallBack(randomsongList);

                } else {
                    Log.w("AAA", "Error getting documents.", task.getException());
                    ToastAnnotation("Có Lỗi Xảy Ra");
                }
            }
        });
    }

    public void getRankSongs(final SongCallBack songCallBack) {
        // Truy cập phiên bản Cloud Firestore từ Hoạt động của bạn
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Song").orderBy("Like", Query.Direction.DESCENDING).limit(5).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                Log.d("AAA", snapshot.getId() + " => " + snapshot.getData());
                                song = snapshot.toObject(Song.class);
                                ranktList.add(song);
                                songCallBack.getCallBack(ranktList);
                            }
                        } else {
                            Log.w("AAA", "Error getting documents.", task.getException());
                            ToastAnnotation("Có Lỗi Xảy Ra");
                        }
                    }
                });
    }
    private void getSongsFromList (SongIDList songIDList, final SongCallBack songCallBack ){
        Log.d("songIDList",songIDList.getData().toString());
        for (int i=0;i<songIDList.getData().size();i++){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Songs").whereEqualTo("ID",songIDList.getData().get(i)).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot snapshot : task.getResult()){
                                    Log.d("songIDList",snapshot.getId()+"=>"+snapshot.getData());
                                    song = snapshot.toObject(Song.class);
                                    songIdList.add(song);
                                    songCallBack.getCallBack(songIdList);
                                }
                            } else {
                                Log.w("songIDList", "Error getting documents.", task.getException());
                                ToastAnnotation("Có Lỗi Xảy Ra");
                            }
                        }
                    });
        }

    }

    private void ToastAnnotation(String mesage) {
        Toast.makeText(context, mesage, Toast.LENGTH_SHORT).show();
    }
}
