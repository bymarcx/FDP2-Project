package com.example.tutorium;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tutorium {

    public static void main(String[] args) {

        System.out.println("START");

        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        List<Trainer> trainers = new ArrayList<Trainer>();

        pokemons.add(new Pokemon(0, new int[] {1, 0, 2} ));
        pokemons.add(new Pokemon(1, new int[] {2, 1, 0} ));
        pokemons.add(new Pokemon(2, new int[] {0, 2, 1} ));

        trainers.add(new Trainer(0, new int[] {1, 0, 2} ));
        trainers.add(new Trainer(1, new int[] {1, 2, 0} ));
        trainers.add(new Trainer(2 ,new int[] {0, 2, 1} ));

        matching(trainers, pokemons);

    }

    public static void matching(List<Trainer>trainers, List<Pokemon>pokemons) {
        // Set Trainers matched to false
        for(int i=0; i<trainers.size(); i++) {
            trainers.get(i).matched = false;
        }
        // Set Pokemons matched to false
        for(int i=0; i<pokemons.size(); i++) {
            pokemons.get(i).matched = false;
        }

        // *******************************************
        // While at least one trainer is unmatched
        int CountFreeTrainer = trainers.size();
        while(CountFreeTrainer > 0) {
            System.out.println("FREE TRAINERS: " + CountFreeTrainer);

            // *******************************************
            // GET FIRST FREE TRAINER
            int aktFreeTrainer = 0;
            for(int i=0; i<trainers.size(); i++) {
                if(trainers.get(i).matched == false) {
                    aktFreeTrainer = trainers.get(i).id ;
                    break;
                }
                else{
                    System.out.println("dont");
                }
            }

            System.out.println("Akt Free Trainer " + aktFreeTrainer);


            // *******************************************
            //
            int p;
            for(p=0; p< trainers.get(aktFreeTrainer).favs.length; p++) {
                if(pokemons.get(p).matched == false) {

                    //Match pokemon to trainer
                    pokemons.get(p).matched = true;
                    pokemons.get(p).matchedID = aktFreeTrainer;
                    trainers.get(aktFreeTrainer).matched = true;
                    trainers.get(aktFreeTrainer).matchedID = p;

                    CountFreeTrainer--;
                    break;

                }
                else {
                    int ot = pokemons.get(p).matchedID;

                    Boolean switching = false;
                    for(int b = 0; b < trainers.size(); b++) {

                        if(trainers.get(aktFreeTrainer).matchedID == pokemons.get(p).favs[b]) {
                            switching = false;
                        }
                        if(trainers.get(ot).matchedID == pokemons.get(p).favs[b]) {
                            switching = true;
                            break;
                        }

                    }

                        if (switching == true) {
                            trainers.get(ot).matched = false;
                            trainers.get(ot).matchedID = 0;

                            trainers.get(aktFreeTrainer).matched = true;
                            trainers.get(aktFreeTrainer).matchedID = pokemons.get(p).id;

                        }


                    for(int y = 0; y < trainers.size(); y++) {
                        if(trainers.get(aktFreeTrainer).favs[y] == pokemons.get(p).matchedID) {
                            trainers.get(aktFreeTrainer).crossed[y] = true;
                        }
                    }


                }

            }


        }

        for(int d = 0; d<trainers.size(); d++) {
            System.out.println("Trainer" + trainers.get(d).id + "is matched with Pokemon" + trainers.get(d).matchedID);
            System.out.println("Pokemon:" + pokemons.get(d).id + "is matched with Trainer" + pokemons.get(d).matchedID);
        }




    }

}
