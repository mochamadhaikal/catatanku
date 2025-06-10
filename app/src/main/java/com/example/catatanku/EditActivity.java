package com.example.catatanku;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catatanku.db.DbConfig;

public class EditActivity extends AppCompatActivity {

    DbConfig config;
    SQLiteDatabase db;
    Cursor cursor;

    EditText edtTitle, edtDescription;
    Button btnSubmit;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        config = new DbConfig(this);

        id = getIntent().getStringExtra("id");

        edtTitle = findViewById(R.id.edt_title);
        edtDescription = findViewById(R.id.edt_description);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener( view -> editData() );

        showData();
    }

    private void showData() {

        db = config.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_tugas WHERE id='" + id + "'", null);
        cursor.moveToFirst();
        edtTitle.setText(cursor.getString(1));
        edtDescription.setText(cursor.getString(2));
    }

    private void editData()
    {
        String title = edtTitle.getText().toString();
        String description = edtDescription.getText().toString();

        if( title.isEmpty() || description.isEmpty() ){
            Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show();
        }else{
            db = config.getReadableDatabase();
            db.execSQL("UPDATE tb_tugas SET title = '" + title + "', description = '" + description + "' WHERE id = '" + id + "'");
            Toast.makeText(this, "Catatan berhasil diubah.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}