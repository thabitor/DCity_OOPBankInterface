package bstorm.akimts.oo.avance.exo.exceptions;

public class LimiteAtteinteException extends RuntimeException {

    private final int limite;

    public LimiteAtteinteException(int limite) {
        super("la limite de {" + limite + "} a été atteinte");
        this.limite = limite;
    }

    public int getLimite() {
        return limite;
    }
}
