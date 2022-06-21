package bstorm.akimts.oo.avance.exo;

import java.util.Set;


// Cette interface me permet de rester dans le cadre de l'énoncé
public interface iCompetition<S extends Sportif> {

    void launch();
    void register(S sportif);
    void deregister(S sportif);
    Set<S> getWinner();
    boolean isTerminee();


    int getLimiteParticipant();

}
