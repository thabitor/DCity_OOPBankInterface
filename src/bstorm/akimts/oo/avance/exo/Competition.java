package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;
import bstorm.akimts.oo.avance.exo.exceptions.LimiteAtteinteException;

import java.util.*;

public class Competition<S extends Sportif> implements iCompetition<S> {

    private final int limiteParticipant;

    private static Localisation localisation;
    private final Map<S, Integer> participants = new HashMap<>();
    private List<S> classements;
    private double award;

    public Competition(Localisation localisation) {
        this.localisation = localisation;
        this.limiteParticipant = localisation.getParticpants();
        this.award = localisation.getAward();
    }

    public Competition(int limiteParticipant) {
        if(limiteParticipant < 0)
            throw new IllegalArgumentException("la limite de participant invalide (devrait être positive");
        this.limiteParticipant = limiteParticipant;
    }

    public Competition(int limiteParticipant, double award) {
        this.limiteParticipant = limiteParticipant;
        this.award = award;
        this.localisation = null;
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
            int score = s.performer();
            participants.put(s, score);
            System.out.println(s + " Score: " + score);

        }

        classements = genererClassement();
    }

    @Override
    public void register(S aInscrire) {

        // n'est pas termine
        if( isTerminee()  )
            throw new EtatCompetitionException(isTerminee() , false);

        // il y a de la place
        if( limiteParticipant != 0 && participants.size() >= limiteParticipant )
            throw new LimiteAtteinteException(limiteParticipant);

        // doit etre non inscrit+
        if( participants.containsKey(aInscrire) )
            throw new IllegalArgumentException("s déjà inscrit");

        participants.put(aInscrire, null);
    }

    public Map<S, Integer> getParticipants() {
        return participants;
    }

    @Override
    public void deregister(S s) {

        // deja terminée
        if( isTerminee()  )
            throw new EtatCompetitionException(isTerminee() , false);

        // n'existe pas
        if( !participants.containsKey(s) )
            throw new IllegalArgumentException("s non inscrit");

        participants.remove(s);
    }

    @Override
    public Set<S> getWinner() {

        if( !isTerminee() )
            throw new EtatCompetitionException(isTerminee() , true);

        Collection<Integer> values = participants.values();
        int maxPerf = 0;
        for (Integer value : values) {
            if( value > maxPerf ) {
                maxPerf = value;
            }
        }
        System.out.println(maxPerf);
        Set<S> gagnants = new HashSet<>();
        for (Map.Entry<S, Integer> entry : participants.entrySet()) {
            if( entry.getValue() == maxPerf )
                gagnants.add(entry.getKey() );
        }
        for (S gagnant : gagnants) {
            gagnant.setTotalAward(award/gagnants.size());
        }

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

    public void registerGroup(Collection<? extends S> aInscrire){
        for (S s : aInscrire) {
            register(s);
            System.out.println(s);
        }
    }

    public void transfertParticipants(Competition<? super S> autreCompet){
        autreCompet.registerGroup( participants.keySet() );
    }

    // Créer une compétition non terminée sur base d'une autre.
    // Elle aura les mêmes participants
    public static <T extends Sportif> Competition<T> fusionner(Competition<? extends T> membre1, Competition<? extends T> membre2){

        Competition<T> compet = new Competition<>(localisation);
        membre1.transfertParticipants(compet);
        membre2.transfertParticipants(compet);
        return compet;

    }

    public <O extends S> Set<O> getOfType( Class<O> clazz ){
        Set<O> set = new HashSet<>();

        for (S part : participants.keySet()) {
            if(part != null && part.getClass().equals(clazz))
                set.add((O)part);
        }

        return set;
    }

    public List<Sportif> getClassements() {
        return new ArrayList<>(classements);
    }
}


