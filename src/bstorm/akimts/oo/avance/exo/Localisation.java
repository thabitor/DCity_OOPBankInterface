package bstorm.akimts.oo.avance.exo;

public enum Localisation {

    REGIONAL(1000, 25),
    NATIONAL(10.000, 50),
    INTERNATIONAL(100.000, 100);

    private double award;
    private int particpants;

    Localisation(double award, int particpant) {
        this.award = award;
        this.particpants = particpant;
    }

    public double getAward() {
        return award;
    }

    public int getParticpants() {
        return particpants;
    }
}
