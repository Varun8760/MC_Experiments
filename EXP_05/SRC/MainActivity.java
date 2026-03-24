package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText itemName, itemPrice, itemNote;
    Button btnSave, btnShow;
    ListView listView;

    SQLiteDatabase db;

    ArrayList<String> inventory;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemName = findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.itemPrice);
        itemNote = findViewById(R.id.itemNote);

        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        listView = findViewById(R.id.listViewItems);

        inventory = new ArrayList<>();

        db = openOrCreateDatabase("InventoryDB", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS items(name TEXT, price TEXT, note TEXT)");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = itemName.getText().toString();
                String price = itemPrice.getText().toString();
                String note = itemNote.getText().toString();

                db.execSQL("INSERT INTO items VALUES('" + name + "','" + price + "','" + note + "')");

                Toast.makeText(MainActivity.this, "Item Saved", Toast.LENGTH_SHORT).show();

                itemName.setText("");
                itemPrice.setText("");
                itemNote.setText("");
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inventory.clear();

                Cursor cursor = db.rawQuery("SELECT * FROM items", null);

                if(cursor.moveToFirst()) {
                    do {

                        String item =
                                "Item: " + cursor.getString(0) +
                                        " | Price: " + cursor.getString(1) +
                                        " | Note: " + cursor.getString(2);

                        inventory.add(item);

                    } while(cursor.moveToNext());
                }

                adapter = new ArrayAdapter<>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        inventory
                );

                listView.setAdapter(adapter);
            }
        });
    }
}