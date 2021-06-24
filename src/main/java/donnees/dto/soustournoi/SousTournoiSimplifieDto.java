package donnees.dto.soustournoi;

public class SousTournoiSimplifieDto {

    private int idSousTournoi;

    private String typeSousTournoi;

    private String dateDebutSousTournoi;

    private String dateFinSousTournoi;

    private boolean estSousTournoiValide;

    public int getIdSousTournoi() {
        return idSousTournoi;
    }

    public void setIdSousTournoi(int idSousTournoi) {
        this.idSousTournoi = idSousTournoi;
    }

    public String getTypeSousTournoi() {
        return typeSousTournoi;
    }

    public void setTypeSousTournoi(String typeSousTournoi) {
        this.typeSousTournoi = typeSousTournoi;
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

    public boolean isEstSousTournoiValide() {
        return estSousTournoiValide;
    }

    public void setEstSousTournoiValide(boolean estSousTournoiValide) {
        this.estSousTournoiValide = estSousTournoiValide;
    }
}
