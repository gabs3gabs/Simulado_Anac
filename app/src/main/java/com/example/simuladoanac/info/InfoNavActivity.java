package com.example.simuladoanac.info;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.GridView;

import com.example.simuladoanac.R;

public class InfoNavActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_nav);

        gridView = findViewById(R.id.gridView);

        GridAdapter adapter = new GridAdapter(getIntent().getIntExtra("setNumero", 1), getIntent().getStringExtra("titulo"));
        gridView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}