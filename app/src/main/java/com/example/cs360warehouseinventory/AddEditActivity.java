package com.example.cs360warehouseinventory;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditActivity extends AppCompatActivity {

    private DatabaseActivity db;
    private String mode = "add";
    private long editId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        db = new DatabaseActivity(this);

        mode = getIntent().getStringExtra("mode");
        if (mode == null) mode = "add";
        editId = getIntent().getLongExtra("id", -1);

        EditText etName = findViewById(R.id.etName);
        EditText etQty = findViewById(R.id.etQty);
        EditText etDesc = findViewById(R.id.etDesc);

        if ("edit".equals(mode) && editId != -1) {
            Cursor c = db.getItemById(editId);
            if (c != null && c.moveToFirst()) {
                etName.setText(c.getString(c.getColumnIndexOrThrow(DatabaseActivity.C_NAME)));
                etQty.setText(String.valueOf(c.getInt(c.getColumnIndexOrThrow(DatabaseActivity.C_QTY))));
                String d = c.getString(c.getColumnIndexOrThrow(DatabaseActivity.C_DESC));
                etDesc.setText(d != null ? d : "");
            }
            if (c != null) {
                c.close();
            }
        }

        findViewById(R.id.btnSubmit).setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            int qty = 0;
            try {
                qty = Integer.parseInt(etQty.getText().toString().trim());
            } catch (Exception ignored) {}

            String desc = etDesc.getText().toString().trim();
            if (desc.isEmpty()) desc = null;

            if (name.isEmpty()) {
                etName.setError("Required");
                return;
            }

            if ("edit".equals(mode) && editId != -1) {
                db.updateItem(editId, name, qty, desc, null, null);
            } else {
                db.insertItem(name, qty, desc, null, null);
            }

            finish();
        });

        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());
    }
}