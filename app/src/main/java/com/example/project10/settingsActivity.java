package com.example.project10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class settingsActivity extends AppCompatActivity implements View.OnClickListener {
    Button backbtn;
    Button howtoplay;
    TextView horaot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        howtoplay=(Button)findViewById(R.id.howtoplaybtn);
        horaot=(TextView)findViewById(R.id.horaot);
        horaot.setVisibility(View.GONE);
        backbtn=(Button)findViewById(R.id.backBtn);
        backbtn.setOnClickListener(this);
        howtoplay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(horaot.getVisibility()==View.GONE)
        {
            (findViewById(R.id.horaot)).setVisibility(View.VISIBLE);
        }
        else
            {
            horaot.setVisibility(view.GONE);
        }
        if(view==backbtn){
            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}