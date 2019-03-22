package objets;

import java.math.BigInteger;
import java.util.List;

public class Crise
{
    private BigInteger crise_id;
    private Mission mission;
    private String description;
    private List<Personne> personnes;

    public Crise(BigInteger crise_id, Mission mission, String description, List<Personne> personnes) {
        this.crise_id = crise_id;
        this.mission = mission;
        this.description = description;
        this.personnes = personnes;
    }

    public BigInteger getCrise_id() {
        return crise_id;
    }

    public Mission getMission() {
        return mission;
    }

    public String getDescription() {
        return description;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }
}
