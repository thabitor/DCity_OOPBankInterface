package bstorm.akimts.oo.avance.exo;

import java.util.List;
import java.util.Set;


// Cette interface me permet de rester dans le cadre de l'énoncé
public interface Competition {

    void lancer();
    void inscrire(Sportif sportif);
    void desinscrire(Sportif sportif);
    Set<Sportif> getGagnants();
    boolean isTerminee();

    int getLimiteParticipant();

}
