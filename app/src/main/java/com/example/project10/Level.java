package com.example.project10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Level extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp;
    SharedPreferences score;
    private int sumpoints;
    private int times;
    private String SongName;
    private Button back;
    private int NumLevel;
    private MediaPlayer Player;
    private EditText answer;
    private Button submit;
    private Button play;
    private boolean complete=false;
    private TextView textlevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        times=0;
        setContentView(R.layout.activity_level);
        sp=getSharedPreferences("level",0);//כאן עצרתי https://appschool.co.il/assets/moxifile/books/android/android5.pdf עמ' 5
        score=getSharedPreferences("score",0);
        sumpoints=score.getInt("score",0);
        back=(Button)findViewById(R.id.backToLevels);
        play=(Button)findViewById(R.id.playmusic);
        textlevel=findViewById(R.id.textlevel);
        answer=findViewById(R.id.answer);
        submit=findViewById(R.id.submit);
        Intent intent=getIntent();
        Bundle bundle = getIntent().getExtras();
        int sound = bundle.getInt("Mediaplayer");
        Player = MediaPlayer.create(this, sound);
        NumLevel=intent.getExtras().getInt("NumLevel");
        play.setOnClickListener(this);
        submit.setOnClickListener(this);
        SongName=intent.getStringExtra("SongName");
        textlevel.setText("Level: "+NumLevel);
    }

    @Override
    public void onClick(View view) {
        if(view==play){
            if(!Player.isPlaying()){
                Player.start();
            }
        }
     if(submit==view){
            if(SongName.equals(answer.getText().toString().toLowerCase())){
                Toast toast = Toast.makeText(this, "Good Job!", Toast.LENGTH_LONG);
                toast.show();
                Intent intent= new Intent(this,Levels.class);
                if(sp.getBoolean("win"+NumLevel,false)==false){//אם הוא לא עבר עדיין את השלב
                    if(times==0){
                        SharedPreferences.Editor scoreedit=score.edit();
                        scoreedit.putInt("score",sumpoints+1000);
                        scoreedit.commit();
                    }
                    else if (times<=2){
                        SharedPreferences.Editor scoreedit=score.edit();
                        scoreedit.putInt("score",sumpoints+700);
                        scoreedit.commit();
                    }
                    else if(times<=4){
                        SharedPreferences.Editor scoreedit=score.edit();
                        scoreedit.putInt("score",sumpoints+500);
                        scoreedit.commit();
                    }
                    else if (times<=7){
                        SharedPreferences.Editor scoreedit=score.edit();
                        scoreedit.putInt("score",sumpoints+300);
                        scoreedit.commit();
                    }
                    else{
                        SharedPreferences.Editor scoreedit=score.edit();
                        scoreedit.putInt("score",sumpoints+100);
                        scoreedit.commit();
                    }

                }
                complete=true;
                SharedPreferences.Editor editor=sp.edit();
                editor.putBoolean("win"+NumLevel,complete);
                editor.commit();
                intent.putExtra("leveldone",complete);
                intent.putExtra("numleveldone",NumLevel);
                intent.putExtra("chance",times);
                startActivity(intent);
                finish();
            }
            else{
                Toast toast = Toast.makeText(this, "Sorry thats wrong", Toast.LENGTH_LONG);
                toast.show();
                times++;
            }
       }
     if(back==view){
         Intent intent=new Intent(this,Levels.class);
         startActivity(intent);
         finish();
     }
    }
}