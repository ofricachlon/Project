package com.example.project10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

public class Levels extends AppCompatActivity implements View.OnClickListener {
    Button[] btn = new Button[9];
    SharedPreferences sp;
    SharedPreferences score;
    private MenuItem scoreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        sp=getSharedPreferences("level",0);
        score=getSharedPreferences("score",0);
        btn[0] = findViewById(R.id.btn1);
        btn[1] = findViewById(R.id.btn2);
        btn[2] = findViewById(R.id.btn3);
        btn[3] = findViewById(R.id.btn4);
        btn[4] = findViewById(R.id.btn5);
        btn[5] = findViewById(R.id.btn6);
        btn[6] = findViewById(R.id.btn7);
        btn[7] = findViewById(R.id.btn8);
        btn[8] = findViewById(R.id.btn9);
        for (int i = 0; i < btn.length; i++) {
            btn[i].setOnClickListener(this);

        }


        Intent intent=getIntent();

        /*if(intent.getBooleanExtra("leveldone",false)){
            btn[intent.getExtras().getInt("numleveldone")-1].setText("Done!");
        }*/
        int x;
        for (int i=0;i<btn.length;i++){
            x=i+1;
            if(sp.getBoolean("win"+x,false)){
                btn[i].setText("DONE!");
            }
        }
    }


    @Override
    public void onClick(View view) {
        if(view==btn[0]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","פרפר");
            intent.putExtra("NumLevel",1);
            intent.putExtra("Mediaplayer1", R.raw.song1p1);
            intent.putExtra("Mediaplayer2", R.raw.song1p2);
            intent.putExtra("Mediaplayer3", R.raw.song1p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[1]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","בין הבודדים");
            intent.putExtra("NumLevel",2);
            intent.putExtra("Mediaplayer1", R.raw.song2p1);
            intent.putExtra("Mediaplayer2", R.raw.song2p2);
            intent.putExtra("Mediaplayer3", R.raw.song2p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[2]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","עולם משוגע");
            intent.putExtra("NumLevel",3);
            intent.putExtra("Mediaplayer1", R.raw.song3p1);
            intent.putExtra("Mediaplayer2", R.raw.song3p2);
            intent.putExtra("Mediaplayer3", R.raw.song3p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[3]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","רוק 30");
            intent.putExtra("NumLevel",4);
            intent.putExtra("Mediaplayer1", R.raw.song4p1);
            intent.putExtra("Mediaplayer2", R.raw.song4p2);
            intent.putExtra("Mediaplayer3", R.raw.song4p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[4]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","מקס");
            intent.putExtra("NumLevel",5);
            intent.putExtra("Mediaplayer1", R.raw.song5p1);
            intent.putExtra("Mediaplayer2", R.raw.song5p2);
            intent.putExtra("Mediaplayer3", R.raw.song5p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[5]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","חתולים");
            intent.putExtra("NumLevel",6);
            intent.putExtra("Mediaplayer1", R.raw.song6p1);
            intent.putExtra("Mediaplayer2", R.raw.song6p2);
            intent.putExtra("Mediaplayer3", R.raw.song6p3);
            startActivity(intent);
            finish();
        }

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
//        if(id==R.id.points){
//          item.setTitle("points: "+score.getInt("score",0));
//        }
        return true;
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Levels.this.finish();
                    }
                }).setNegativeButton("No", null).show();
    }
}