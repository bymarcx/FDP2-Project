package com.example.tutorium;
import java.util.ArrayList;
import java.util.List;

public class Tutorium {

    public static void main(String[] args) {

        System.out.println("START");

        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        List<Trainer> trainers = new ArrayList<Trainer>();

        pokemons.add(new Pokemon(1, new int[] {2, 1, 3} ));
        pokemons.add(new Pokemon(2, new int[] {3, 2, 1} ));
        pokemons.add(new Pokemon(3, new int[] {1, 3, 2} ));

        trainers.add(new Trainer(1, new int[] {2, 1, 3} ));
        trainers.add(new Trainer(2, new int[] {2, 3, 1} ));
        trainers.add(new Trainer(3 ,new int[] {1, 3, 2} ));

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

        int CountFreeTrainer = trainers.size();
        while(CountFreeTrainer > 0) {

            // GET FIRST FREE TRAINER
            int aktFreeTrainer;
            for(int i=0; i<trainers.size(); i++) {
                if(trainers.get(i).matched == false) {
                    aktFreeTrainer = trainers.get(i).id;
                    System.out.println("Free Trainer" + aktFreeTrainer);
                    break;
                }
                else{System.out.println("dont");}
            }

            int freePoke;
            for(int i=0; i<pokemons.size(); i++) {
                if(trainers.get(i).matched == false) {
                    freePoke = pokemons.get(i).id;
                    System.out.println("Free Poke" + freePoke);
                    break;
                }
            }



        }




    }

}
