package com.example.catatanku.db;

import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class DbConfig extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_catatan.db";
    private static final int DB_VERSION = 1;

    public DbConfig(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE tb_tugas ( id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(255) NOT NULL, description TEXT NOT NULL )";
        db.execSQL(queryCreateTable);

        String querySampleData = "INSERT INTO tb_tugas (title, description) VALUES ('UTS', 'Membuat program sederhana'), ('Tugas', 'Prakik SQLite'), ('Kuis', 'Teori SQL')";
        db.execSQL(querySampleData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

