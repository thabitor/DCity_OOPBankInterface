package bstorm.akimts.oo.avance.exo;

import java.util.Random;

abstract class Sportif {

    private String nom;
    private String prenom;
    private Integer anneeNaiss;

    private double totalAward;

    public Sportif(String nom, String prenom, Integer anneeNaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.anneeNaiss = anneeNaiss;
        this.totalAward  = 0;
    }

    public int performer() {
        return new Random().nextInt(1,101);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAnneeNaiss() {
        return anneeNaiss;
    }

    public void setAnneeNaiss(Integer anneeNaiss) {
        this.anneeNaiss = anneeNaiss;
    }

    public void setTotalAward(double award) {
        this.totalAward += award;
    }

    @Override
    public String toString() {
        return "Sportif{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", anneeNaiss=" + anneeNaiss +
                ", totalAward=" + totalAward +
                '}';
    }
}
