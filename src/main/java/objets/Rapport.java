package objets;

import java.math.BigInteger;
import java.util.List;

public class Rapport {
    private BigInteger rapport_id;
    private Mission mission;
    private List<Crise> crises;
    private Personne avenger;
    private Personne personne;
    private String description;
    private Boolean succes;

    public Rapport(BigInteger rapport_id, Mission mission, List<Crise> crises, Personne avenger, Personne personne, String description, Boolean succes) {
        this.rapport_id = rapport_id;
        this.mission = mission;
        this.crises = crises;
        this.avenger = avenger;
        this.personne = personne;
        this.description = description;
        this.succes = succes;
    }

    public BigInteger getRapport_id() {
        return rapport_id;
    }

    public Mission getMission() {
        return mission;
    }

    public List<Crise> getCrises() {
        return crises;
    }

    public Personne getAvenger() {
        return avenger;
    }

    public Personne getPersonne() {
        return personne;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getSucces() {
        return succes;
    }
}
