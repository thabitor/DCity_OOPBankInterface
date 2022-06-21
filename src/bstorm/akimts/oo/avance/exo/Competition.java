package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;
import bstorm.akimts.oo.avance.exo.exceptions.LimiteAtteinteException;

import java.util.*;

public class Competition<S extends Sportif> implements iCompetition<S> {

    private final int limiteParticipant;
    private final Map<S, Integer> participants = new HashMap<>();
    private List<S> classements;

    public Competition() {
        limiteParticipant = 0;
    }


    public Competition(int limiteParticipant) {
        if(limiteParticipant < 0)
            throw new IllegalArgumentException("la limite de participant invalide (devrait être positive");
        this.limiteParticipant = limiteParticipant;
    }

    @Override
    public void launch() {

        // n'est pas terminé
        if( isTerminee()  )
            throw new EtatCompetitionException(isTerminee() , false);

        // Pas de participants
        if( participants.size() < 3 )
            throw new IllegalStateException("La compet n'a pas de participants");

        for (S s : participants.keySet()) {
            participants.put(s, s.performer());
        }

        classements = genererClassement();
    }

    @Override
    public void register(S sportif) {

        // n'est pas termine
        if( isTerminee()  )
            throw new EtatCompetitionException(isTerminee() , false);

        // il y a de la place
        if( limiteParticipant != 0 && participants.size() >= limiteParticipant )
            throw new LimiteAtteinteException(limiteParticipant);

        // doit etre non inscrit+
        if( participants.containsKey(sportif) )
            throw new IllegalArgumentException("sportif déjà inscrit");

        participants.put(sportif, null);
    }

    @Override
    public void deregister(Sportif sportif) {

        // deja terminée
        if( isTerminee()  )
            throw new EtatCompetitionException(isTerminee() , false);

        // n'existe pas
        if( !participants.containsKey(sportif) )
            throw new IllegalArgumentException("sportif non inscrit");

        participants.remove(sportif);
    }

    @Override
    public Set<S> getWinner() {

        if( !isTerminee() )
            throw new EtatCompetitionException(isTerminee() , true);

        Collection<Integer> values = participants.values();
        int maxPerf = 0;
        for (Integer value : values) {
            if( value > maxPerf )
                maxPerf = value;
        }

        Set<S> gagnants = new HashSet<>();
        for (Map.Entry<S, Integer> entry : participants.entrySet()) {
            if( entry.getValue() == maxPerf )
                gagnants.add(entry.getKey() );
        }

//        int maxPerf = participants.values()
//                .stream()
//                .mapToInt(i -> i)
//                .max()
//                .getAsInt();

//        Set<Sportif> gagnants = participants.entrySet().stream()
//                .filter( e -> e.getValue() == maxPerf )
//                .map( Map.Entry::getKey )
//                .collect(Collectors.toSet());

        return gagnants;
    }

    @Override
    public boolean isTerminee() {
        return classements != null;
    }

    @Override
    public int getLimiteParticipant() {
        return limiteParticipant;
    }

    private List<S> genererClassement(){

        List<S> classement = new ArrayList<>();

        for (S sportif : participants.keySet()) {

            boolean place = false;
            for (int i = 0; i < classement.size() && !place; i++) {

                S currentSportif = classement.get(i);
                int currentPerf = participants.get(currentSportif);

                int perfSportAPlacer = participants.get(sportif);

                if( perfSportAPlacer > currentPerf ){
                    classement.add(i, sportif);
                    place = true;
                }
            }

            if( !place )
                classement.add(sportif);

        }

        return classement;
    }

    public List<Sportif> getClassements() {
        return new ArrayList<>(classements);
    }
}


