package donnees.dto.soustournoi;

import donnees.dto.soustournoi.match.MatchDto;

import java.util.Date;
import java.util.List;

/**
 * Dto des sous-tournois.
 */
public class SousTournoiDto {

    private int idSousTournoi;

    private String dateDebutSousTournoi;

    private String dateFinSousTournoi;

    private boolean estSousTournoiValide;

    private String typeSousTournoi;

    private List<MatchDto> listeMatch;

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

    public boolean estSousTournoiValide() {
        return estSousTournoiValide;
    }

    public void setValiditeSousTournoi(boolean estValide) {
        estSousTournoiValide = estValide;
    }

    public String getTypeSousTournoi() {
        return typeSousTournoi;
    }

    public void setTypeSousTournoi(String typeSousTournoi) {
        this.typeSousTournoi = typeSousTournoi;
    }

    public List<MatchDto> getListeMatch() {
        return listeMatch;
    }

    public void setListeMatch(List<MatchDto> listeMatch) {
        this.listeMatch = listeMatch;
    }
}
