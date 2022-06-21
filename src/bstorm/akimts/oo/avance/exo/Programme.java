package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;

public class Programme {

    public static void main(String[] args) {

        Competition<Judoka> compet = new Competition<>(3);
        Competition<LanceurJavelot> javos = new Competition<>(3);
        Competition<Coureur> cooru = new Competition<>(3);

        try {
            compet.register(new Judoka("Rodrigez", "Jayjay", 1985));
            compet.register(new Judoka("Rami", "Rodrigez", 1985));
            compet.register(new Judoka("Noyo", "brun", 1985));

            javos.register(new LanceurJavelot("Marcelo", "James", 1985));
            javos.register(new LanceurJavelot("Rodrigez", "Nolan", 1985));
            javos.register(new LanceurJavelot("Max", "Noyo", 1985));

            cooru.register(new Coureur("Chidi", "Strong", 1985));
            cooru.register(new Coureur("Mindi", "Valo", 1985));
            cooru.register(new Coureur("Rufat", "Nino", 1985));
        }
        catch(EtatCompetitionException ex) {
            ex.printStackTrace();
        }

        compet.launch();
        javos.launch();
        cooru.launch();


        System.out.println(compet.getWinner() + " " + javos.getWinner() + " " + cooru.getWinner());


    }

}
