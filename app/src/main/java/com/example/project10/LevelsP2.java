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

public class LevelsP2 extends AppCompatActivity implements View.OnClickListener {
    private Button[] btn = new Button[9];
    SharedPreferences sp;
    SharedPreferences score;
    private MenuItem scoreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_p2);
        sp=getSharedPreferences("level",0);
        score=getSharedPreferences("score",0);
        btn[0] = findViewById(R.id.btn10);
        btn[1] = findViewById(R.id.btn11);
        btn[2] = findViewById(R.id.btn12);
        btn[3] = findViewById(R.id.btn13);
        btn[4] = findViewById(R.id.btn14);
        btn[5] = findViewById(R.id.btn15);
        btn[6] = findViewById(R.id.btn16);
        btn[7] = findViewById(R.id.btn17);
        btn[8] = findViewById(R.id.btn18);

        for (int i = 0; i < btn.length; i++) {
            btn[i].setOnClickListener(this);
        }


        Intent intent=getIntent();

        int x;
        for (int i=0;i<btn.length;i++){
            x=9+i+1;
            if(sp.getBoolean("win"+x,false)){
                btn[i].setText("DONE!");
            }
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
                        LevelsP2.this.finish();
                    }
                }).setNegativeButton("No", null).show();
    }

    @Override
    public void onClick(View view) {
        if(view==btn[0]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","מכה אפורה");
            intent.putExtra("NumLevel",10);
            intent.putExtra("Mediaplayer1", R.raw.p2song1p1);
            intent.putExtra("Mediaplayer2", R.raw.p2song1p2);
            intent.putExtra("Mediaplayer3", R.raw.p2song1p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[1]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","אני שוב מתאהב");
            intent.putExtra("NumLevel",11);
            intent.putExtra("Mediaplayer1", R.raw.p2song2p1);
            intent.putExtra("Mediaplayer2", R.raw.p2song2p2);
            intent.putExtra("Mediaplayer3", R.raw.p2song2p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[2]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","מי אוהב אותך יותר ממני");
            intent.putExtra("NumLevel",12);
            intent.putExtra("Mediaplayer1", R.raw.p2song3p1);
            intent.putExtra("Mediaplayer2", R.raw.p2song3p2);
            intent.putExtra("Mediaplayer3", R.raw.p2song3p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[3]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","ימים של שקט");
            intent.putExtra("NumLevel",13);
            intent.putExtra("Mediaplayer1", R.raw.p2song4p1);
            intent.putExtra("Mediaplayer2", R.raw.p2song4p2);
            intent.putExtra("Mediaplayer3", R.raw.p2song4p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[4]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","פסק זמן");
            intent.putExtra("NumLevel",14);
            intent.putExtra("Mediaplayer1", R.raw.p2song5p1);
            intent.putExtra("Mediaplayer2", R.raw.p2song5p2);
            intent.putExtra("Mediaplayer3", R.raw.p2song5p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[5]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","חתולים");
            intent.putExtra("NumLevel",15);
            intent.putExtra("Mediaplayer1", R.raw.song6p1);
            intent.putExtra("Mediaplayer2", R.raw.song6p2);
            intent.putExtra("Mediaplayer3", R.raw.song6p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[6]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","איידישע ראסטה מאן");
            intent.putExtra("NumLevel",16);
            intent.putExtra("Mediaplayer1", R.raw.song7p1);
            intent.putExtra("Mediaplayer2", R.raw.song7p2);
            intent.putExtra("Mediaplayer3", R.raw.song7p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[7]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","גלישה בסתר");
            intent.putExtra("NumLevel",17);
            intent.putExtra("Mediaplayer1", R.raw.song8p1);
            intent.putExtra("Mediaplayer2", R.raw.song8p2);
            intent.putExtra("Mediaplayer3", R.raw.song8p3);
            startActivity(intent);
            finish();
        }
        if(view==btn[8]){
            Intent intent= new Intent(this,Level.class);
            intent.putExtra("SongName","להתעורר");
            intent.putExtra("NumLevel",18);
            intent.putExtra("Mediaplayer1", R.raw.song9p1);
            intent.putExtra("Mediaplayer2", R.raw.song9p2);
            intent.putExtra("Mediaplayer3", R.raw.song9p3);
            startActivity(intent);
            finish();
        }
    }
}