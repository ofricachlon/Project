package com.example.project10;
public class Game {
    private Song[] hiphopMagar;
    private Song[] hiphopsongs;
    private Song[] israeliSongs;

    private final int max=9;//גודל מאגר השירים של האפליקציה
    private final int current=9;//מספר השירים שניתן לשחק איתם

    public Game(){
        hiphopMagar=new Song[max];
        hiphopMagar[0]=new Song("פרפר","עטר מיינר",1, R.raw.song1p1, R.raw.song1p2, R.raw.song1p3);
        hiphopMagar[1]=new Song("בין הבודדים","איזי & ג'ורדי",2, R.raw.song2p1, R.raw.song2p2, R.raw.song2p3);
        hiphopMagar[2]=new Song("עולם משוגע","טונה & רביד פלוטניק",3, R.raw.song3p1, R.raw.song3p2, R.raw.song3p3);
        hiphopMagar[3]=new Song("רוק 30","טונה",4, R.raw.song4p1, R.raw.song4p2, R.raw.song4p3);
        hiphopMagar[4]=new Song("מקס","וייב איש",5, R.raw.song5p1, R.raw.song5p2, R.raw.song5p3);
        hiphopMagar[5]=new Song("חתולים","ג'ימבו ג'יי ולהקת הספא& רביד פלוטניק",6, R.raw.song6p1, R.raw.song6p2, R.raw.song6p3);
        hiphopMagar[6]=new Song("איידישע ראסטה מאן","רביד פלוטניק",7, R.raw.song7p1, R.raw.song7p2, R.raw.song7p3);
        hiphopMagar[7]=new Song("גלישה בסתר","כהן & סוויסה",8, R.raw.song8p1, R.raw.song8p2, R.raw.song8p3);
        hiphopMagar[8]=new Song("להתעורר","עטר מיינר",9, R.raw.song9p1, R.raw.song9p2, R.raw.song9p3);

        hiphopsongs=new Song[current];
        israeliSongs=new Song[current];
        for (int i=0;i<current; i++){
            hiphopsongs[i]=hiphopMagar[i];
        }
    }

    public Song[] getsongs(int num) {
        if(num==0){
            return hiphopsongs;
        }
        return israeliSongs;
    }

   /* public Song[] hiphop(){
        songs[0]=new Song("פרפר","עטר מיינר",1, R.raw.song1p1, R.raw.song1p2, R.raw.song1p3);
        songs[1]=new Song("בין הבודדים","איזי & ג'ורדי",2, R.raw.song2p1, R.raw.song2p2, R.raw.song2p3);
        songs[2]=new Song("עולם משוגע","טונה & רביד פלוטניק",3, R.raw.song3p1, R.raw.song3p2, R.raw.song3p3);
        songs[3]=new Song("רוק 30","טונה",4, R.raw.song4p1, R.raw.song4p2, R.raw.song4p3);
        songs[4]=new Song("מקס","וייב איש",5, R.raw.song5p1, R.raw.song5p2, R.raw.song5p3);
        songs[5]=new Song("חתולים","ג'ימבו ג'יי ולהקת הספא& רביד פלוטניק",6, R.raw.song6p1, R.raw.song6p2, R.raw.song6p3);
        songs[6]=new Song("איידישע ראסטה מאן","רביד פלוטניק",7, R.raw.song7p1, R.raw.song7p2, R.raw.song7p3);
        songs[7]=new Song("גלישה בסתר","כהן & סוויסה",8, R.raw.song8p1, R.raw.song8p2, R.raw.song8p3);
        songs[8]=new Song("להתעורר","עטר מיינר",9, R.raw.song9p1, R.raw.song9p2, R.raw.song9p3);
        return songs;
    }*/
   // public Song[]
}


