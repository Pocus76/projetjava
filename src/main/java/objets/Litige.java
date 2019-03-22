package objets;

import java.math.BigInteger;
import java.util.List;

public class Litige {
    private BigInteger litige_id;
    private Mission mission;
    private List<Document> documents;
    private String type;
    private Float cout;
    private String description;

    public Litige(BigInteger litige_id, Mission mission, List<Document> documents, String type, Float cout, String description) {
        this.litige_id = litige_id;
        this.mission = mission;
        this.documents = documents;
        this.type = type;
        this.cout = cout;
        this.description = description;
    }

    public BigInteger getLitige_id() {
        return litige_id;
    }

    public Mission getMission() {
        return mission;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public String getType() {
        return type;
    }

    public Float getCout() {
        return cout;
    }

    public String getDescription() {
        return description;
    }
}
