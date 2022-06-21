package bstorm.akimts.oo.avance.demo.exceptions;

public class SoldeInsuffisantException extends IllegalArgumentException{

    private final double soldeRestant;
    private final double retrait;

    public SoldeInsuffisantException(double soldeRestant, double retrait) {
        super("Le solde est insuffissant");
        this.soldeRestant = soldeRestant;
        this.retrait = retrait;
    }

    public double getSoldeRestant() {
        return soldeRestant;
    }

    public double getRetrait() {
        return retrait;
    }
}
