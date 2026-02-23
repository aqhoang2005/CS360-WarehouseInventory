package com.example.cs360warehouseinventory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseActivity db;
    private InventoryAdapter adapter;
    private String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.main_activity);

        currentUsername = getIntent().getStringExtra("username");
        db = new DatabaseActivity(this);

        RecyclerView rv = findViewById(R.id.inventoryList);
        if (rv == null) throw new RuntimeException("inventoryList not found in main_activity.xml");
        rv.setLayoutManager(new GridLayoutManager(this, 1));

        adapter = new InventoryAdapter(
                item -> {
                    Intent i = new Intent(MainActivity.this, AddEditActivity.class);
                    i.putExtra("mode", "edit");
                    i.putExtra("id", item.id);
                    startActivity(i);
                },
                item -> {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Delete Item")
                            .setMessage("Delete '" + item.name + "'?")
                            .setPositiveButton("Delete", (d, w) -> {
                                db.deleteItem(item.id);
                                loadGrid();
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                }
        );

        rv.setAdapter(adapter);

        findViewById(R.id.addBtn).setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AddEditActivity.class);
            i.putExtra("mode", "add");
            startActivity(i);
        });

        findViewById(R.id.profileBtn).setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, UserActivity.class);
            i.putExtra("username", currentUsername);
            startActivity(i);
        });

        loadGrid();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadGrid();
    }

    private void loadGrid() {
        Cursor c = db.getAllItemsCursor();
        List<InventoryItem> list = new ArrayList<>();

        int idIdx = c.getColumnIndexOrThrow(DatabaseActivity.C_ITEM_ID);
        int nameIdx = c.getColumnIndexOrThrow(DatabaseActivity.C_NAME);
        int qtyIdx = c.getColumnIndexOrThrow(DatabaseActivity.C_QTY);
        int descIdx = c.getColumnIndexOrThrow(DatabaseActivity.C_DESC);
        int tagsIdx = c.getColumnIndexOrThrow(DatabaseActivity.C_TAGS);
        int imgIdx = c.getColumnIndexOrThrow(DatabaseActivity.C_IMAGE_URI);

        while (c.moveToNext()) {
            list.add(new InventoryItem(
                    c.getLong(idIdx),
                    c.getString(nameIdx),
                    c.getInt(qtyIdx),
                    c.getString(descIdx),
                    c.getString(tagsIdx),
                    c.getString(imgIdx)
            ));
        }
        c.close();

        adapter.submitList(list);
    }

}
