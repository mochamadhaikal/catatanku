package com.example.catatanku;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.catatanku.db.DbConfig;

public class DetailActivity extends AppCompatActivity {

    DbConfig config;
    SQLiteDatabase db;
    Cursor cursor;

    String id;
    TextView txtJudul, txtDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        config = new DbConfig(this);

        id = getIntent().getExtras().getString("id");

        txtJudul = findViewById(R.id.txt_judul);
        txtDeskripsi = findViewById(R.id.txt_deskripsi);

        showData();

    }

    private void showData() {

        db = config.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_tugas WHERE id='" + id + "'", null);
        cursor.moveToFirst();
        txtJudul.setText(cursor.getString(1));
        txtDeskripsi.setText(cursor.getString(2));

    }
}