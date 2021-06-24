package donnees.entites.soustournoi.match;

import javax.persistence.*;
import java.util.List;

/**
 * Entite des arbitres.
 */
@Entity
@Table(name = "arbitre")
public class Arbitre {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idArbitre")
    private int idArbitre;

    @Column(name="nomArbitre")
    private String nomArbitre;

    @Column(name="prenomArbitre")
    private String prenomArbitre;

    @Column(name="nationaliteArbitre")
    private String nationaliteArbitre;

    @OneToMany(mappedBy = "arbitre")
    private List<Match> listeMatch;

    public int getIdArbitre() {
        return idArbitre;
    }

    public void setIdArbitre(int idArbitre) {
        this.idArbitre = idArbitre;
    }

    public String getNomArbitre() {
        return nomArbitre;
    }

    public void setNomArbitre(String nomArbitre) {
        this.nomArbitre = nomArbitre;
    }

    public String getPrenomArbitre() {
        return prenomArbitre;
    }

    public void setPrenomArbitre(String prenomArbitre) {
        this.prenomArbitre = prenomArbitre;
    }

    public String getNationaliteArbitre() {
        return nationaliteArbitre;
    }

    public void setNationaliteArbitre(String nationaliteArbitre) {
        this.nationaliteArbitre = nationaliteArbitre;
    }

    public List<Match> getListeMatch() {
        return listeMatch;
    }

    public void setListeMatch(List<Match> listeMatch) {
        this.listeMatch = listeMatch;
    }
}
