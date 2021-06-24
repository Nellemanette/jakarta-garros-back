package donnees.entites.soustournoi;

import donnees.entites.soustournoi.match.Match;

import javax.persistence.*;
import java.util.List;

/**
 * Entite des sous-tournois.
 */
@Entity
@Table(name = "sous_tournoi")
public class SousTournoi {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idSousTournoi")
    private int idSousTournoi;

    @Column(name="dateDebutSousTournoi")
    private String dateDebutSousTournoi;

    @Column(name="dateFinSousTournoi")
    private String dateFinSousTournoi;

    @Column(name="estSousTournoiValide")
    private boolean validiteSousTournoi;

    @Column(name="typeSousTournoi")
    private String typeSousTournoi;

    @OneToMany(mappedBy = "sousTournoi")
    private List<Match> listeMatch;

    public int getIdSousTournoi() {
        return idSousTournoi;
    }

    public void setIdSousTournoi(int idSousTournoi) {
        this.idSousTournoi = idSousTournoi;
    }

    public String getDateDebutSousTournoi() {
        return dateDebutSousTournoi;
    }

    public void setDateDebutSousTournoi(String dateDebutSousTournoi) {
        this.dateDebutSousTournoi = dateDebutSousTournoi;
    }

    public String getDateFinSousTournoi() {
        return dateFinSousTournoi;
    }

    public void setDateFinSousTournoi(String dateFinSousTournoi) {
        this.dateFinSousTournoi = dateFinSousTournoi;
    }

    public boolean getValiditeSousTournoi() {
        return validiteSousTournoi;
    }

    public void setValiditeSousTournoi(boolean validite) {
        this.validiteSousTournoi = validite;
    }

    public String getTypeSousTournoi() {
        return typeSousTournoi;
    }

    public void setTypeSousTournoi(String typeSousTournoi) {
        this.typeSousTournoi = typeSousTournoi;
    }

    public List<Match> getListeMatch() {
        return listeMatch;
    }

    public void setListeMatch(List<Match> listeMatch) {
        this.listeMatch = listeMatch;
    }
}
