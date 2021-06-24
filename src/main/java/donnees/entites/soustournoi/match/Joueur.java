package donnees.entites.soustournoi.match;

import javax.persistence.*;

@Entity
@Table(name = "joueur")
public class Joueur {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idJoueur")
    private int idJoueur;

    @Column(name="nomJoueur")
    private String nomJoueur;

    @Column(name="prenomJoueur")
    private String prenomJoueur;

    @Column(name="nationaliteJoueur")
    private String nationaliteJoueur;

    @Column(name="sexe")
    private String sexe;

    @Column(name="classementJoueur")
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
