package bstorm.akimts.oo.avance.demo.exceptions;

public class MontantNegatifException extends IllegalArgumentException {
    public MontantNegatifException() {
        super("Le montant devrait être positif");
    }
}
