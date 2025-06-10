package com.example.catatanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.catatanku.adapter.DataAdapter;
import com.example.catatanku.db.DbConfig;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DbConfig config;
    SQLiteDatabase db;
    Cursor cursor;

    RecyclerView rcvData;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layout;

    ArrayList<String> idList, titleList;

    ImageView linkAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = new DbConfig(this);

        linkAdd = findViewById(R.id.link_add);

        linkAdd.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
        });

        rcvData = findViewById(R.id.rcv_data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }
    public void showData() {
        idList = new ArrayList<>();
        titleList = new ArrayList<>();

        layout = new LinearLayoutManager(this);
        adapter = new DataAdapter(this, idList, titleList);

        rcvData.setLayoutManager(layout);
        rcvData.setHasFixedSize(true);
        rcvData.setAdapter(adapter);

        db = config.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_tugas", null);

        if (cursor.moveToFirst()) {
            do {
                idList.add(cursor.getString(0));
                titleList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        adapter.notifyDataSetChanged();
        cursor.close();
    }
}
