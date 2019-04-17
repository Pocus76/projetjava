package objets;

public class Autorisation {

    private int autorisation_id;
    private String autorisation_libelle;

    public Autorisation(int autorisation_id, String autorisation_libelle){
        this.autorisation_id = autorisation_id;
        this.autorisation_libelle = autorisation_libelle;

    }

    public int getAutorisation_id(){ return autorisation_id;}
    public String getAutorisation_libelle() {
        return autorisation_libelle;
    }
}
