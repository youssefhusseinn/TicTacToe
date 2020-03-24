package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView1;
    private TextView textView2;
    private EditText editText1;
    private EditText editText2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        button = findViewById(R.id.button);
        button.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("p1name",editText1.getText().toString());
        intent.putExtra("p2name",editText2.getText().toString());
        startActivity(intent);
    }
}
