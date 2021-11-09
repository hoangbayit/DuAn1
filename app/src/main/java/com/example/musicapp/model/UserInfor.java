package com.example.musicapp.model;

import java.util.ArrayList;

public class UserInfor {
    private String ID;
    private String Username;
    private ArrayList<String> Favorites = null;
    private String Password;
    private String Email;
    private Boolean LinkGmail;
    private Boolean LinkFaceBook;
    private ArrayList<String> userPlaylist;

    private String kindID;
    //biến xác định có phải là playplist hay không
    private Boolean isPlayList;
    //biến xác định có phải là favorites hay không
    private Boolean isFavorites;


    private String TempID;
    //Biến xác định Playlist hiện tại người dang dùng đang xem
    private String TemPlayListID;
    //Biến Xác Định album hiện tại người dang xem
    private ArrayList<String> CurentAlbum;

    private static UserInfor instance = new UserInfor();
    private UserInfor(){ }

    private static UserInfor getInstance(){
        return instance;
    }


    public UserInfor(String ID, String username, ArrayList<String> favorites, String password, String email, Boolean linkGmail, Boolean linkFaceBook, ArrayList<String> userPlaylist) {
        this.ID = ID;
        Username = username;
        Favorites = favorites;
        Password = password;
        Email = email;
        LinkGmail = linkGmail;
        LinkFaceBook = linkFaceBook;
        this.userPlaylist = userPlaylist;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public ArrayList<String> getFavorites() {
        return Favorites;
    }

    public void setFavorites(ArrayList<String> favorites) {
        Favorites = favorites;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Boolean getLinkGmail() {
        return LinkGmail;
    }

    public void setLinkGmail(Boolean linkGmail) {
        LinkGmail = linkGmail;
    }

    public Boolean getLinkFaceBook() {
        return LinkFaceBook;
    }

    public void setLinkFaceBook(Boolean linkFaceBook) {
        LinkFaceBook = linkFaceBook;
    }

    public ArrayList<String> getUserPlaylist() {
        return userPlaylist;
    }

    public void setUserPlaylist(ArrayList<String> userPlaylist) {
        this.userPlaylist = userPlaylist;
    }
}

