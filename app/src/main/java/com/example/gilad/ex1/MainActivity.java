package com.example.gilad.ex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    static final String MESSAGES = "messages";

    ArrayList<CharSequence> content;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.listView);

        if (savedInstanceState == null) {
            content = new ArrayList<>();
        }
        else {
            content = savedInstanceState.getCharSequenceArrayList(MESSAGES);
        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                content);

        listView.setAdapter(adapter);

        final EditText editText = (EditText) findViewById(R.id.editText);
        Button send = (Button) findViewById(R.id.button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = editText.getText().toString();

                if (!text.equals("")) {
                    content.add(text);
                    adapter.notifyDataSetChanged();
                }

                editText.getText().clear();
            }

        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequenceArrayList(MESSAGES, content);
    }
}
