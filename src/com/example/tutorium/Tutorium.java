package com.example.tutorium;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tutorium {

    public static void main(String[] args) {

        System.out.println("*** START ***");

        List<Pokemon> pokemons = new ArrayList<>();
        List<Trainer> trainers = new ArrayList<>();

        pokemons.add(new Pokemon(0, new ArrayList<Integer>(Arrays.asList(1, 0, 2)) ));
        pokemons.add(new Pokemon(1, new ArrayList<Integer>(Arrays.asList(2, 1, 0)) ));
        pokemons.add(new Pokemon(2, new ArrayList<Integer>(Arrays.asList(0, 2, 1)) ));

        trainers.add(new Trainer(0, new ArrayList<Integer>(Arrays.asList(1, 0, 2)) ));
        trainers.add(new Trainer(1, new ArrayList<Integer>(Arrays.asList(1, 2, 0)) ));
        trainers.add(new Trainer(2, new ArrayList<Integer>(Arrays.asList(0, 2, 1)) ));


        matching(trainers, pokemons);

    }

    public static void matching(List<Trainer>trainers, List<Pokemon>pokemons) {

        // *** Set all Trainers matched to false
        for(int i=0; i<trainers.size(); i++) {
            trainers.get(i).matched = false;
            trainers.get(i).matchedID = -1;
        }
        // *** Set all Pokemons matched to false
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
            int aktFreeTrainer = -1;
            for(int i=0; i<trainers.size(); i++) {
                aktFreeTrainer = trainers.get(i).id ;

                if(trainers.get(aktFreeTrainer).matched == false) {

                    System.out.println("  ");
                    System.out.println("Akt. free Trainer " + aktFreeTrainer);


                    // *******************************************
                    // CHECKING AKT-FREE-TRAINERS FAVS
                    int p = 0;

                    //if (trainers.get(aktFreeTrainer).favs.size() > 0) {

                        System.out.println("Akt Pokemon" + p);

                        if(pokemons.get(trainers.get(aktFreeTrainer).favs.get(p)).matched == false) {
                            System.out.println("Akt Pokemon" + p +"is not matched yet");

                            //Match pokemon to trainer
                            pokemons.get(trainers.get(aktFreeTrainer).favs.get(p)).matched = true;
                            pokemons.get(trainers.get(aktFreeTrainer).favs.get(p)).matchedID = aktFreeTrainer;
                            trainers.get(aktFreeTrainer).matched = true;
                            trainers.get(aktFreeTrainer).matchedID = pokemons.get(trainers.get(aktFreeTrainer).favs.get(p)).id;

                            System.out.println("Trainer " + trainers.get(aktFreeTrainer).id + " matched with Pokemon " + trainers.get(aktFreeTrainer).matchedID +"/"+ p);

                            // matched means not available anymore
                            CountFreeTrainer--;
                            //break;

                        }
                        else {
                            int ot = pokemons.get(trainers.get(aktFreeTrainer).favs.get(p)).matchedID;
                            System.out.println("Akt Pokemon " +p+ " is already matched with trainer " + trainers.get(ot).id + "/" + ot);

                            boolean switching = false;
                            for(int b = 0; b < trainers.size(); b++) {

                                System.out.println("Favs checking" + b);

                                if(trainers.get(ot).id == pokemons.get(p).favs.get(b)) {
                                    System.out.println("Trainer ot" + ot + " is first! NO SWITCH");
                                    break;
                                }
                                if(trainers.get(aktFreeTrainer).id == pokemons.get(p).favs.get(b)) {
                                    System.out.println("Trainer aktFreeTrainer" + aktFreeTrainer + " is first! DO SWITCH");
                                    switching = true;
                                    break;
                                }

                            }

                            if (switching) {
                                trainers.get(ot).matched = false;
                                trainers.get(ot).matchedID = -1;

                                trainers.get(aktFreeTrainer).matched = true;
                                trainers.get(aktFreeTrainer).matchedID = pokemons.get(trainers.get(aktFreeTrainer).favs.get(p)).id;

                                pokemons.get(trainers.get(aktFreeTrainer).favs.get(p)).matched = true;
                                pokemons.get(trainers.get(aktFreeTrainer).favs.get(p)).matchedID = trainers.get(aktFreeTrainer).id;

                                System.out.println("Pokemon " +p + "/" + pokemons.get(p).id+ " changed match to Trainer" + trainers.get(aktFreeTrainer).id);
                            }

                        }

                        trainers.get(aktFreeTrainer).favs.remove(p);
                        System.out.println("FAVS LENGTH: " + trainers.get(aktFreeTrainer).favs.size());
                        System.out.println();

                // } // END LOOPING AKT TRAINERS FAVS

                    System.out.println(" *** END OF LOOP *** ");

                }
                else{
                    System.out.println("*** ERROR: TRAINER IS ALREADY MATCHED");
                }
            }

        } // END WHILE

        System.out.println();
        System.out.println("END RESULTS");
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

}
