package com.example.musicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActi extends AppCompatActivity {
    EditText edtUserName,edtPass;
    Button btnLogin,btnForgorPass,btnDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_main);
        edtUserName = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgorPass = findViewById(R.id.btnForgotPass);
        btnDangKy = findViewById(R.id.btnDangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogDangKy();
            }
        });




    }
    public void openDialogDangKy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActi.this);
        LayoutInflater inflater =LoginActi.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dangky, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
    }
}