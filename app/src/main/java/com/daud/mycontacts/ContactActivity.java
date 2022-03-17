package com.daud.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    private TextView NameC,NumberC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        NameC=findViewById(R.id.NameC);
        NumberC=findViewById(R.id.NumberC);
        Intent intent=getIntent();
        String Name=intent.getStringExtra("RvName");
        String Number=intent.getStringExtra("RvNumber");
        NameC.setText(Name);
        NumberC.setText(Number);
        NumberC.setOnClickListener(view -> {
            Intent callintent = new Intent(Intent.ACTION_DIAL);
            callintent.setData(Uri.parse("tel:"+Number));
            startActivity(callintent);
        });
    }
}