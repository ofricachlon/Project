package com.example.project10;

public class Song {

    private String Songname;
    private String Artistname;
    private int Numlevel;
    private int idsong1;
    private int idsong2;
    private int idsong3;


    public Song(String songname, String artistname, int numlevel, int idsong1, int idsong2, int idsong3) {
        Songname = songname;
        Artistname = artistname;
        Numlevel = numlevel;
        this.idsong1 = idsong1;
        this.idsong2 = idsong2;
        this.idsong3 = idsong3;
    }

    public String getSongname() {
        return Songname;
    }

    public void setSongname(String songname) {
        Songname = songname;
    }

    public String getArtistname() {
        return Artistname;
    }

    public void setArtistname(String artistname) {
        Artistname = artistname;
    }

    public int getNumlevel() {
        return Numlevel;
    }

    public void setNumlevel(int numlevel) {
        Numlevel = numlevel;
    }

    public int getIdsong1() {
        return idsong1;
    }

    public void setIdsong1(int idsong1) {
        this.idsong1 = idsong1;
    }

    public int getIdsong2() {
        return idsong2;
    }

    public void setIdsong2(int idsong2) {
        this.idsong2 = idsong2;
    }

    public int getIdsong3() {
        return idsong3;
    }

    public void setIdsong3(int idsong3) {
        this.idsong3 = idsong3;
    }
}
