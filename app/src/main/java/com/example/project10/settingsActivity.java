package com.example.project10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    Button Share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        howtoplay=(Button)findViewById(R.id.howtoplaybtn);
        horaot=(TextView)findViewById(R.id.horaot);
        horaot.setVisibility(View.GONE);
        backbtn=(Button)findViewById(R.id.backBtn);
        Share=findViewById(R.id.Sheresettings);
        Share.setOnClickListener(this);
        backbtn.setOnClickListener(this);
        howtoplay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(howtoplay==view){
            if(horaot.getVisibility()==View.GONE)
            {
                (findViewById(R.id.horaot)).setVisibility(View.VISIBLE);
            }
            else
            {
                horaot.setVisibility(view.GONE);
            }
        }
        if(view==backbtn){
            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if (Share==view){
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String ShereBody="Hi! Look at a new game I found. His name is MusicQuiz I recommend you! ";
            String Sheresub="MusicQuiz";
            intent.putExtra(Intent.EXTRA_SUBJECT,Sheresub);
            intent.putExtra(Intent.EXTRA_TEXT,ShereBody);
            startActivity(Intent.createChooser(intent,"Shere"));
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage("Are you sure you want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        settingsActivity.this.finish();
                    }
                }).setNegativeButton("No", null).show();
    }
}