package com.example.project10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button  Playbtn;
    private Button settingsbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Playbtn=(Button)findViewById(R.id.playbtn);
        settingsbtn=(Button)findViewById(R.id.settingbtn);
        settingsbtn.setOnClickListener(this);
        Playbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (settingsbtn==view){
            Intent intent=new Intent(this,settingsActivity.class);
            startActivity(intent);
        }
        if(Playbtn==view){
            Intent intent=new Intent(this,Levels.class);
            startActivity(intent);
        }
    }
    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}