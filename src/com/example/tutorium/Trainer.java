package com.example.tutorium;

public class Trainer {

    public int id;

    public boolean matched = false;

    public int[] favs;

    public int matchedID;

    public boolean[] crossed;

    Trainer(int id, int[] favs ) {
        this.id = id;
        this.favs = favs;

        crossed = new boolean[] {false, false, false};
    }


}
