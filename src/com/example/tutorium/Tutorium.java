package com.example.tutorium;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tutorium {

    public static void main(String[] args) {
        List<Pokemon> pokemons = new ArrayList<>();
        List<Trainer> trainers = new ArrayList<>();

        trainers.add(new Trainer(0, new ArrayList<>(Arrays.asList(2,1,4,0,3)) ));
        trainers.add(new Trainer(1, new ArrayList<>(Arrays.asList(0,1,4,2,3)) ));
        trainers.add(new Trainer(2, new ArrayList<>(Arrays.asList(3,2,1,0,4)) ));
        trainers.add(new Trainer(3, new ArrayList<>(Arrays.asList(0,2,3,1,4)) ));
        trainers.add(new Trainer(4, new ArrayList<>(Arrays.asList(0,1,3,4,2)) ));

        pokemons.add(new Pokemon(0, new ArrayList<>(Arrays.asList(2,4,1,0,3)) ));
        pokemons.add(new Pokemon(1, new ArrayList<>(Arrays.asList(4,1,0,3,2)) ));
        pokemons.add(new Pokemon(2, new ArrayList<>(Arrays.asList(3,2,4,0,1)) ));
        pokemons.add(new Pokemon(3, new ArrayList<>(Arrays.asList(0,1,2,3,4)) ));
        pokemons.add(new Pokemon(4, new ArrayList<>(Arrays.asList(1,2,3,0,4)) ));

        // run!
        matching(trainers, pokemons);
    }

    public static void matching(List<Trainer>trainers, List<Pokemon>pokemons) {
        System.out.println("*** START ***");

        // set all Trainers matched to false
        for(int i=0; i<trainers.size(); i++) {
            trainers.get(i).matched = false;
            trainers.get(i).matchedID = -1;
        }
        // set all Pokemons matched to false
        for(int i=0; i<pokemons.size(); i++) {
            pokemons.get(i).matched = false;
            pokemons.get(i).matchedID = -1;
        }

        // *******************************************
        // While at least one trainer is unmatched
        int CountFreeTrainer = trainers.size();
        while(CountFreeTrainer > 0) {
            System.out.println("Free Trainers: " + CountFreeTrainer);

            // *******************************************
            // GET FIRST FREE TRAINER
            int aktFreeTrainer;
            for(int i=0; i<trainers.size(); i++) {
                aktFreeTrainer = i; // trainers.get(i).id ;

                if(trainers.get(aktFreeTrainer).matched == false) {

                    System.out.println("  ");
                    System.out.println("Akt. free Trainer " + aktFreeTrainer);


                    // *******************************************
                    // CHECKING AKT-FREE-TRAINERS FAVS
                    int p = 0;
                    int favPoke = trainers.get(aktFreeTrainer).favs.get(p);

                    System.out.println("Akt Pokemon" + favPoke);

                    if(pokemons.get(favPoke).matched == false) {
                        System.out.println("Akt Pokemon" + favPoke +"is not matched yet");

                        //Match pokemon to trainer
                        pokemons.get(favPoke).matched = true;
                        pokemons.get(favPoke).matchedID = trainers.get(aktFreeTrainer).id;
                        trainers.get(aktFreeTrainer).matched = true;
                        trainers.get(aktFreeTrainer).matchedID = pokemons.get(favPoke).id;

                        System.out.println("Trainer " + trainers.get(aktFreeTrainer).id + " matched with Pokemon " + trainers.get(aktFreeTrainer).matchedID);

                        // matched means not available anymore
                        CountFreeTrainer--;

                    }
                    else {
                        int ot = trainers.get(pokemons.get(favPoke).matchedID).id;

                        System.out.println("ot1 :" + ot);

                        System.out.println("Akt Pokemon " + favPoke + " is already matched with trainer " + trainers.get(ot).id);

                        // check which trainer is preferred



                        if (ChangeTrainer(trainers, pokemons, aktFreeTrainer, ot, favPoke)) {
                            // set old trainer unmatched
                            trainers.get(ot).matched = false;
                            trainers.get(ot).matchedID = -1;

                            // set new trainer matched
                            trainers.get(aktFreeTrainer).matched = true;
                            trainers.get(aktFreeTrainer).matchedID = pokemons.get(favPoke).id;

                            // set pokemon matched to aktFreeTrainer
                            pokemons.get(favPoke).matched = true;
                            pokemons.get(favPoke).matchedID = trainers.get(aktFreeTrainer).id;

                            System.out.println("Pokemon " + favPoke + " changed match to Trainer" + trainers.get(aktFreeTrainer).id);
                        }

                    }

                    // *******************************************
                    // remove pokemon from trainer fravs
                    trainers.get(aktFreeTrainer).favs.remove(p);
                    System.out.println("Favs of trainer -1: " + trainers.get(aktFreeTrainer).favs.size());
                    System.out.println();

                    System.out.println("*** END OF LOOP ***");
                    System.out.println();

                }
                else{
                    System.out.println(" TRAINER IS ALREADY MATCHED");
                }
            }



        } // END WHILE

        System.out.println();
        System.out.println("*** END RESULTS ***");
        System.out.println();

        for(int d = 0; d<trainers.size(); d++) {
            if(trainers.get(d).matched) {
                System.out.println("Trainer " + trainers.get(d).id + " is matched with Pokemon " + trainers.get(d).matchedID);
            }
            else {
                System.out.println("Trainer " + trainers.get(d).id + " is unmatched // MatchedID: " + trainers.get(d).matchedID);
            }
        }

        for(int d = 0; d<pokemons.size(); d++) {
            if(pokemons.get(d).matched) {
                System.out.println("Pokemon " + pokemons.get(d).id + " is matched with Trainer " + pokemons.get(d).matchedID);
            }
            else {
                System.out.println("Pokomen " + pokemons.get(d).id + " is unmatched // MatchedID: " + pokemons.get(d).matchedID);
            }
        }


    }


    public static boolean ChangeTrainer(List<Trainer>trainers, List<Pokemon> pokemons, int aktFreeTrainer, int ot, int favPoke ) {

        for(int b = 0; b < trainers.size(); b++) {
            if(pokemons.get(favPoke).favs.get(b) == ot) {
                System.out.println("Trainer ot" + ot + " is first! DO NOT SWITCH");
                return false;
            }
            if(pokemons.get(favPoke).favs.get(b) == aktFreeTrainer) {
                System.out.println("Trainer aktFreeTrainer" + aktFreeTrainer + " is first! DO SWITCH");
                return true;
            }
        }
        return false;
    }

}
