package com.example.project10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class Level extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
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
    private Button clues;
    private TextView clueshow;
    private Button Sherebtn;


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
        clues=findViewById(R.id.clues_popup);
        clueshow=findViewById(R.id.clues_textview);
        Sherebtn=findViewById(R.id.shereBtn);
        Intent intent=getIntent();
        Bundle bundle = getIntent().getExtras();
        int sound = bundle.getInt("Mediaplayer");
        Player = MediaPlayer.create(this, sound);
        NumLevel=intent.getExtras().getInt("NumLevel");
        play.setOnClickListener(this);
        submit.setOnClickListener(this);
        back.setOnClickListener(this);
        SongName=intent.getStringExtra("SongName");
        clues.setOnClickListener(this);
        Sherebtn.setOnClickListener(this);

        textlevel.setText("Song: "+NumLevel);
    }

    @Override
    public void onClick(View view) {
        if(view==play){
            if(!Player.isPlaying()){
                Player.start();
            }
        }
        if(back==view){
            Intent intent=new Intent(this,Levels.class);
            startActivity(intent);
            finish();
        }
        if(Sherebtn==view){
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String ShereBody="Hey, im stuck in song "+NumLevel +" in the game MusicQuiz. can you help me?";
            String Sheresub="MusicQuiz help";
            intent.putExtra(Intent.EXTRA_SUBJECT,Sheresub);
            intent.putExtra(Intent.EXTRA_TEXT,ShereBody);
            startActivity(Intent.createChooser(intent,"Shere using"));
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
     if (clues==view){
         PopupMenu popup = new PopupMenu(this, view);
         popup.setOnMenuItemClickListener(this);
         popup.inflate(R.menu.popup_menu);
         popup.show();
     }
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage("Are you sure you want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) { Level.this.finish(); }
                }).setNegativeButton("No", null).show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        super.onOptionsItemSelected(menuItem);


        int id=menuItem.getItemId();
        if(id==R.id.firstchar){
            if(score.getInt("score",0)>=100){
                clueshow.setText("First character is: '" +SongName.charAt(0)+"'");
                SharedPreferences.Editor scoreedit=score.edit();
                scoreedit.putInt("score",sumpoints-100);
                scoreedit.commit();
            }
            else{
                Toast toast=Toast.makeText(this,"You do not have enough points to do this",Toast.LENGTH_LONG);
                toast.show();
            }
        }
        if(id==R.id.lengthofsong){
            int count=0;
            String s="(";
            if (score.getInt("score",0)>=50){
                for (int i=0;i<SongName.length();i++){
                    if (SongName.charAt(i)==' '){
                        s+=count +",";
                        count=0;
                    }
                    count++;
                }
                if(count>0){
                    s+=count;
                }
                s+=")";
                clueshow.setText(s);
            }
            else{
                Toast toast=Toast.makeText(this,"You do not have enough points to do this",Toast.LENGTH_LONG);
                toast.show();
            }
        }
        return true;
    }
}