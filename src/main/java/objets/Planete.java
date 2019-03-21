package objets;

public class Planete {
    private String nom;
    private int id;

    public Planete(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }



    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }



}
