package com.example.project10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
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
    private boolean[] usetheclue;
    private int sumpoints;
    private int times;
    private String SongName;
    private Button back;
    private int NumLevel;
    private MediaPlayer[] Player;
    private EditText answer;
    private Button submit;
    private Button play;
    private boolean complete=false;
    private TextView textlevel;
    private Button clues;
    private TextView clueshow;
    private Button Sharebtn;
    private Button playP2;
    private Button playP3;
    private MenuItem scoreview;
    private String artistname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        times=0;
        setContentView(R.layout.activity_level);
        sp=getSharedPreferences("level",0);//כאן עצרתי https://appschool.co.il/assets/moxifile/books/android/android5.pdf עמ' 5
        score=getSharedPreferences("score",0);
        sumpoints=score.getInt("score",0);
        usetheclue=new boolean[2];
        usetheclue[0]=false;
        usetheclue[1]=false;
        back=(Button)findViewById(R.id.backToLevels);
        play=(Button)findViewById(R.id.playmusic);
        textlevel=findViewById(R.id.textlevel);
        answer=findViewById(R.id.answer);
        submit=findViewById(R.id.submit);
        clues=findViewById(R.id.clues_popup);
        clueshow=findViewById(R.id.clues_textview);
        Sharebtn=findViewById(R.id.shereBtn);
        Player=new MediaPlayer[3];
        playP2=findViewById(R.id.Playpart2);
        playP3=findViewById(R.id.Playpart3);

        Intent intent=getIntent();
        Bundle bundle = getIntent().getExtras();
        int sound1 = bundle.getInt("Mediaplayer1");
        int sound2=bundle.getInt("Mediaplayer2");
        int sound3=bundle.getInt("Mediaplayer3");


        Player[0] = MediaPlayer.create(this, sound1);
        Player[1] = MediaPlayer.create(this, sound2);
        Player[2] = MediaPlayer.create(this, sound3);
        NumLevel=intent.getExtras().getInt("NumLevel");
        play.setOnClickListener(this);
        submit.setOnClickListener(this);
        back.setOnClickListener(this);
        SongName=intent.getStringExtra("SongName");
        artistname=intent.getStringExtra("ArtistName");
        clues.setOnClickListener(this);
        Sharebtn.setOnClickListener(this);
        playP2.setOnClickListener(this);
        playP3.setOnClickListener(this);

        textlevel.setText("Song: "+NumLevel);
    }

    @Override
    public void onClick(View view) {
        if(view==play){
            if(!Player[0].isPlaying()){
                Player[0].start();
            }
        }
        if(back==view){
            if(NumLevel<=9){
                Intent intent=new Intent(this,Levels.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent=new Intent(this,LevelsP2.class);
                startActivity(intent);
                finish();
            }

        }
        if(Sharebtn==view){
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String ShereBody="Hey, im stuck in song "+NumLevel +" in the game MusicQuiz. can you help me?";
            String Sheresub="MusicQuiz help";
            intent.putExtra(Intent.EXTRA_SUBJECT,Sheresub);
            intent.putExtra(Intent.EXTRA_TEXT,ShereBody);
            startActivity(Intent.createChooser(intent,"Share using"));
        }
        if (playP2==view){
            if(score.getInt("score",0)>=150){
                if(!Player[0].isPlaying()||!Player[1].isPlaying()||!Player[2].isPlaying())
                {
                    if(usetheclue[0]){
                        Player[1].start();
                    }
                    else {
                        new AlertDialog.Builder(this).setMessage("the cost for this clue is 150").setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                sumpoints=score.getInt("score",0);
                                SharedPreferences.Editor scoreedit=score.edit();
                                scoreedit.putInt("score",sumpoints-150);
                                scoreedit.commit();
                                Player[1].start();
                                usetheclue[0]=true;
                                findViewById(R.id.Playpart3).setVisibility(View.VISIBLE);
                            }
                        }).setNegativeButton("No, i dont need that", null).show();
                    }
                }
            }
        }
        if(playP3==view){
            if(score.getInt("score",0)>=50){
                if(!Player[0].isPlaying()||!Player[1].isPlaying()||!Player[2].isPlaying())
                {
                    if(usetheclue[1]){
                        Player[2].start();
                    }
                    else {
                        new AlertDialog.Builder(this).setMessage("The cost for this clue is 50").setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                sumpoints=score.getInt("score",0);
                                SharedPreferences.Editor scoreedit=score.edit();
                                scoreedit.putInt("score",sumpoints-50);
                                scoreedit.commit();
                                Player[2].start();
                                usetheclue[1]=true;
                            }
                        }).setNegativeButton("No, i dont need that", null).show();
                    }
                }
            }
        }

     if(submit==view){
            if(SongName.equals(answer.getText().toString().toLowerCase())){
                Toast toast = Toast.makeText(this, "Good Job!", Toast.LENGTH_LONG);
                toast.show();
                Intent intent;
                if(NumLevel>9){
                     intent= new Intent(this,LevelsP2.class);
                }
                else {
                    intent = new Intent(this, Levels.class);
                }

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
                intent.putExtra("numleveldone",NumLevel);///
                intent.putExtra("chance",times);
                startActivity(intent);
                finish();
            }
            else{
                Toast toast = Toast.makeText(this, "Sorry thats wrong", Toast.LENGTH_LONG);
                toast.show();
                answer.setText(null);
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
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Level.this.finish();
                    }
                }).setNegativeButton("No", null).show();
    }

    @Override
    public boolean onMenuItemClick(final MenuItem menuItem) {
        super.onOptionsItemSelected(menuItem);


        int id=menuItem.getItemId();
        if(id==R.id.firstchar){
            if(score.getInt("score",0)>=100){
                new AlertDialog.Builder(this).setMessage("The cost for this clue is 100").setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        sumpoints=score.getInt("score",0);
                        clueshow.setText("First character is: '" +SongName.charAt(0)+"'");
                        SharedPreferences.Editor scoreedit=score.edit();
                        scoreedit.putInt("score",sumpoints-100);
                        scoreedit.commit();

                    }
                }).setNegativeButton("No, i dont need that", null).show();
            }
            else{
                Toast toast=Toast.makeText(this,"You do not have enough points to do this",Toast.LENGTH_LONG);
                toast.show();
            }
        }
        if(id==R.id.lengthofsong){
            if(score.getInt("score",0)>=50){
                new AlertDialog.Builder(this).setMessage("The cost for this clue is 50").setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) {
                        int count = 0;
                        int countspace=0;
                        String s = "(";
                        if (score.getInt("score", 0) >= 50) {
                            for (int i = 0; i < SongName.length(); i++) {
                                if (SongName.charAt(i) == ' ') {
                                    s += count + ",";
                                    count = 0;
                                }
                                count++;
                            }
                            if (count > 0) {
                                s += count - 1;
                            }
                            for(int i=0; i<s.length();i++){
                                if(s.charAt(i)==','){
                                    countspace++;
                                }
                            }
                            if (countspace==0){
                                s="("+SongName.length();
                            }
                            s += ")";
                            clueshow.setText(s);
                            sumpoints=score.getInt("score",0);
                            SharedPreferences.Editor scoreedit = score.edit();
                            scoreedit.putInt("score", sumpoints - 50);
                            scoreedit.commit();

                        }
                    }
                }).setNegativeButton("No, i dont need that", null).show();
            }
            else{
                Toast toast=Toast.makeText(this,"You do not have enough points to do this",Toast.LENGTH_LONG);
                toast.show();
              }

            }
        if (id==R.id.artistname){
            if(score.getInt("score",0)>=150){
                new AlertDialog.Builder(this).setMessage("The cost for this clue is 150").setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) {
                        {
                            clueshow.setText(artistname);
                            sumpoints=score.getInt("score",0);
                            SharedPreferences.Editor scoreedit = score.edit();
                            scoreedit.putInt("score", sumpoints - 150);
                            scoreedit.commit();
                        }
                    }
                }).setNegativeButton("No, i dont need that", null).show();
            }
            else{
                Toast toast=Toast.makeText(this,"You do not have enough points to do this",Toast.LENGTH_LONG);
                toast.show();
            }

        }





        if(id==R.id.Continuethesong)
        {
            findViewById(R.id.Playpart2).setVisibility(View.VISIBLE);
        }
        return true;
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