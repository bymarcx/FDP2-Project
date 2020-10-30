package com.example.tutorium;

public class Pokemon {

    public int id;

    public boolean matched = false;

    public int[] favs;

    public int matchedID;



    Pokemon(int id, int[] favs ) {
        this.id = id;
        this.favs = favs;
    }

}
