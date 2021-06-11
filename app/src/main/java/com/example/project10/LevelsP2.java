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
    private Game game=new Game();
    private Song[] songs=new Song[game.getCurrent()];
    SharedPreferences sp;
    SharedPreferences score;
    private MenuItem scoreview;
    private Button BacKTopage1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_p2);
        songs=game.getsongs(2);
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
        BacKTopage1=findViewById(R.id.backTopP1);
        BacKTopage1.setOnClickListener(this);

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
        Intent intent;
        if(view==BacKTopage1){
            intent=new Intent(this,Levels.class);
            startActivity(intent);
            finish();
        }
        else {
            int place=0;
            if(view==btn[0]){
                place=0;
            }
            else if (view==btn[1]){
                place=1;
            }
            else if(view==btn[2])
                place=2;
            else if(view==btn[3])
                place=3;
            else if (view==btn[4])
                place=4;
            else if (view==btn[5]){
                place=5;
            }
            else if(view==btn[6])
                place=6;
            else if(view==btn[7])
                place=7;
            else if (view==btn[8])
                place=8;

            intent= new Intent(this,Level.class);
            intent.putExtra("SongName",songs[place].getSongname());
            intent.putExtra("ArtistName",songs[place].getArtistname());
            intent.putExtra("NumLevel",songs[place].getNumlevel());
            intent.putExtra("Mediaplayer1", songs[place].getIdsong1());
            intent.putExtra("Mediaplayer2", songs[place].getIdsong2());
            intent.putExtra("Mediaplayer3", songs[place].getIdsong3());
            startActivity(intent);
            finish();
        }
        }


    }
