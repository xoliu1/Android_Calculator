package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewItem extends AppCompatActivity {
    String[] datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Intent intent = getIntent();
        datas = intent.getStringArrayExtra("formulas");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListViewItem.this, android.R.layout.simple_list_item_1, datas);
        ListView listView1 = (ListView) findViewById(R.id.list_view1);
        listView1.setAdapter(adapter);
    }
}