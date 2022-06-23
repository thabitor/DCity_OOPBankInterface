package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Programme {

    public static void main(String[] args) {


        Competition<Coureur> cooru = new Competition<>(Localisation.INTERNATIONAL);
        Competition<Coureur> cooru2 = new Competition<>(Localisation.INTERNATIONAL);

        Coureur bruce = new Coureur("Bruce", "Willis", 1980, 9, 80);
        Coureur johnny = new Coureur("Johnny", "Depp", 1980, 10, 70);
        Coureur will = new Coureur("Will", "Smith", 1980, 11, 90);
        Coureur brad = new Coureur("Brad", "Pitt", 1980, 12, 80);

        ArrayList<Coureur> runners = new ArrayList<>();

        for (int i = 0; i < cooru.getLimiteParticipant(); i++) {
            Random rand = new Random();
            int random = rand.nextInt(8, 13);
            int random2 = rand.nextInt(69, 81);
            runners.add(new Coureur(String.valueOf(i+1), String.valueOf(i+1), 1980, random, random2));
        }

        System.out.println(cooru);

        try {

        }
        catch(EtatCompetitionException ex) {
            ex.printStackTrace();
        }

        cooru.registerGroup(runners);
        cooru.transfertParticipants(cooru2);
        cooru.launch();
        System.out.println("THE WINNER IS :" + cooru.getWinner());

        System.out.println("--------------------");
        cooru2.launch();
        System.out.println("THE WINNER IS :" + cooru2.getWinner());




    }

}
