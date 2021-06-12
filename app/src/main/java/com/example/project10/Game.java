package com.example.project10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Song[] hiphopMagar;
    private Song[] israeliMagar;
    private Song[] hiphopsongs;
    private Song[] israeliSongs;


    private boolean AsShuffeld = false;

    private final int MAX = 9;//גודל מאגר השירים של האפליקציה
    private final int current = 9;//מספר השירים שניתן לשחק איתם
    public Game() {
        hiphopMagar = new Song[MAX];
        hiphopMagar[0] = new Song("פרפר", "עטר מיינר", 1, R.raw.song1p1, R.raw.song1p2, R.raw.song1p3);
        hiphopMagar[1] = new Song("בין הבודדים", "איזי & ג'ורדי", 2, R.raw.song2p1, R.raw.song2p2, R.raw.song2p3);
        hiphopMagar[2] = new Song("עולם משוגע", "טונה & רביד פלוטניק", 3, R.raw.song3p1, R.raw.song3p2, R.raw.song3p3);
        hiphopMagar[3] = new Song("רוק 30", "טונה", 4, R.raw.song4p1, R.raw.song4p2, R.raw.song4p3);
        hiphopMagar[4] = new Song("מקס", "וייב איש", 5, R.raw.song5p1, R.raw.song5p2, R.raw.song5p3);
        hiphopMagar[5] = new Song("חתולים", "ג'ימבו ג'יי ולהקת הספא& רביד פלוטניק", 6, R.raw.song6p1, R.raw.song6p2, R.raw.song6p3);
        hiphopMagar[6] = new Song("איידישע ראסטה מאן", "רביד פלוטניק", 7, R.raw.song7p1, R.raw.song7p2, R.raw.song7p3);
        hiphopMagar[7] = new Song("גלישה בסתר", "כהן & סוויסה", 8, R.raw.song8p1, R.raw.song8p2, R.raw.song8p3);
        hiphopMagar[8] = new Song("להתעורר", "עטר מיינר", 9, R.raw.song9p1, R.raw.song9p2, R.raw.song9p3);
        israeliMagar = new Song[MAX];

        israeliMagar[0] = new Song("מכה אפורה", "מוניקה סקס", 10, R.raw.p2song1p1, R.raw.p2song1p2, R.raw.p2song1p3);
        israeliMagar[1] = new Song("אני שוב מתאהב", "גידי גוב", 11, R.raw.p2song2p1, R.raw.p2song2p2, R.raw.p2song2p3);
        israeliMagar[2] = new Song("מי אוהב אותך יותר ממני", "ארקדי דוכין", 12, R.raw.p2song3p1, R.raw.p2song3p2, R.raw.p2song3p3);
        israeliMagar[3] = new Song("ימים של שקט", "לולה", 13, R.raw.p2song4p1, R.raw.p2song4p2, R.raw.p2song4p3);
        israeliMagar[4] = new Song("פסק זמן", "אריק איינשטיין", 14, R.raw.p2song5p1, R.raw.p2song5p2, R.raw.p2song5p3);
        israeliMagar[5] = new Song("מוכרת לי מפעם", "דין דין אביב", 15, R.raw.p2song6p1, R.raw.p2song6p2, R.raw.p2song6p3);
        israeliMagar[6] = new Song("רוב השעות", "עידן רייכל", 16, R.raw.p2song7p1, R.raw.p2song7p2, R.raw.p2song7p3);
        israeliMagar[7] = new Song("ירח", "שלמה ארצי", 17, R.raw.p2song8p1, R.raw.p2song8p2, R.raw.p2song8p3);
        israeliMagar[8] = new Song("עברתי רק כדי לראות", "ביצוע של: תומר יוסף ובן הנדלר", 18, R.raw.p2song9p1, R.raw.p2song9p2, R.raw.p2song9p3);

        hiphopsongs = new Song[current];
        israeliSongs = new Song[current];
        if (!AsShuffeld) {
            for (int i = 0; i < current; i++) {
                hiphopsongs[i] = hiphopMagar[i];
                israeliSongs[i] = israeliMagar[i];
            }
        }
    }


    public Song[] getsongs(int page) {
        if (page <= 1) {
            return hiphopsongs;
        }
        return israeliSongs;
    }

    public Song[] Shuffle(int page) {
        List<Integer> numbers = new ArrayList<Integer>();
        AsShuffeld = true;
        int next=0;
        if (page <= 1) {
            for (int i = 0; i < current; i++) {
                next = getRandomInt(MAX);
                while (numbers.contains(next)) {
                    next = getRandomInt(MAX);
                }
                numbers.add(next);
                hiphopsongs[i] = hiphopMagar[next];
                hiphopsongs[i].setNumlevel(i + 1);

            }
            return hiphopsongs;
        }
        else {
            for (int i=0;i<current;i++){
                next= getRandomInt(MAX);
                while (numbers.contains(next)){
                    next=getRandomInt(MAX);
                }
                numbers.add(next);
                israeliSongs[i]=israeliMagar[next];
                israeliSongs[i].setNumlevel(i+10);
            }
            return israeliSongs;
        }
    }

    public int getRandomInt(int max){
        return (int) Math.floor(Math.random()*max);
    }

    public int getCurrent() {
        return current;
    }
}


