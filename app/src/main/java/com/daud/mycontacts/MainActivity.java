package com.daud.mycontacts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private List<String>Name;
    private List<String> Number;
    private RecyclerView RecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        getContacts();
        MyAdapter Adapter=new MyAdapter(this,Name,Number);
        RecyclerView.setAdapter(Adapter);
        /*RecyclerView.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,ContactActivity.class);
            intent.putExtra("Data",""+Adapter);
        });*/
    }

    private void initialization() {
        Name=new ArrayList<>();
        Number=new ArrayList<>();
        RecyclerView=findViewById(R.id.RecyclerView);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED);
        }
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},0);
        ContentResolver contentResolver=getContentResolver();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor= contentResolver.query(uri,null,null,null,null);
        cursor.moveToFirst();
            while (cursor.moveToNext()){
                @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Name.add(name);
                Number.add(number);
            }
    }
}