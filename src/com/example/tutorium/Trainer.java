package com.example.tutorium;

import java.util.ArrayList;
import java.util.List;

public class Trainer {

    public int id;

    public boolean matched = false;

    public List<Integer> favs;

    public int matchedID = -1;


    Trainer(int id, ArrayList<Integer> favs ) {
        this.id = id;
        this.favs = favs;
    }


}
