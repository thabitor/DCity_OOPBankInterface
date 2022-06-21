package bstorm.akimts.oo.avance.exo.exceptions;

public class EtatCompetitionException extends IllegalStateException{

    private final boolean etatCourant;
    private final boolean etatAttendu;

    public EtatCompetitionException(boolean etatCourant, boolean etatAttendu) {
        super("La competition est "+ (etatCourant ? "" : "non ") + "terminée. Pour faire cette action, " +
                "elle devrait être " + (etatAttendu ? "" : "non ") + "terminée." );
        this.etatCourant = etatCourant;
        this.etatAttendu = etatAttendu;
    }

    public boolean isEtatCourant() {
        return etatCourant;
    }

    public boolean isEtatAttendu() {
        return etatAttendu;
    }
}
