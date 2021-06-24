package donnees.dto.soustournoi.match;

public class JoueurDto {

    private int idJoueur;

    private String nomJoueur;

    private String prenomJoueur;

    private String nationaliteJoueur;

    private String sexe;

    private int classementJoueur;

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public String getPrenomJoueur() {
        return prenomJoueur;
    }

    public void setPrenomJoueur(String prenomJoueur) {
        this.prenomJoueur = prenomJoueur;
    }

    public String getNationaliteJoueur() {
        return nationaliteJoueur;
    }

    public void setNationaliteJoueur(String nationaliteJoueur) {
        this.nationaliteJoueur = nationaliteJoueur;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getClassementJoueur() {
        return classementJoueur;
    }

    public void setClassementJoueur(int classementJoueur) {
        this.classementJoueur = classementJoueur;
    }
}
