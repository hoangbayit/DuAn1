package com.example.musicapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Album implements Parcelable {
    private String Image,Name,Singer,ID;
    private ArrayList<String> Song = null;

    public Album() {
    }

    public Album(String image, String name, String singer, String ID, ArrayList<String> song) {
        Image = image;
        Name = name;
        Singer = singer;
        this.ID = ID;
        Song = song;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<String> getSong() {
        return Song;
    }

    public void setSong(ArrayList<String> song) {
        Song = song;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
