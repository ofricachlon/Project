package com.example.project10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Playbtn;
    private Button settingsbtn;
    private SharedPreferences sp;
    private SharedPreferences score;
    private MenuItem scoreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Playbtn = (Button) findViewById(R.id.playbtn);
        settingsbtn = (Button) findViewById(R.id.settingbtn);
        settingsbtn.setOnClickListener(this);
        Playbtn.setOnClickListener(this);
        sp=getSharedPreferences("com.project.project1.0",MODE_PRIVATE);
        score=getSharedPreferences("score",0);

    }

    @Override
    public void onClick(View view) {
        if (settingsbtn == view) {
            Intent intent = new Intent(this, settingsActivity.class);
            startActivity(intent);
            finish();
        }
        if (Playbtn == view) {
            Intent intent = new Intent(this, Levels.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (sp.getBoolean("firstrun", true)) {

            new AlertDialog.Builder(this).setMessage("welcome to Music Quiz. Receive a gift of joining 500 points").setCancelable(false).setPositiveButton("Thank You!", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences.Editor scoreedit = score.edit();
                    scoreedit.putInt("score",  500);
                    scoreedit.commit();
                }
            }).show();
        }
            sp.edit().putBoolean("firstrun", false).commit();
        }
    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                }).setNegativeButton("No", null).show();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        scoreview=menu.findItem(R.id.points);
        String s="points: "+score.getInt("score",0);
        scoreview.setTitle(s);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        int id=item.getItemId();
        if(id==R.id.help){
            Intent intent=new Intent(this,settingsActivity.class);
            startActivity(intent);
            finish();
        }
        if(id==R.id.points){
            item.setTitle("points: "+score.getInt("score",0));
        }
        return true;
    }
}