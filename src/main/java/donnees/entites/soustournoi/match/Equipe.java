package donnees.entites.soustournoi.match;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idEquipe")
    private int idEquipe;

    @Column(name="colonneRemplissage")
    private String colonneRemplissage;

    @ManyToMany
    @JoinTable(
        name = "constitution_equipe",
        joinColumns = @JoinColumn(name = "idEquipe", referencedColumnName = "idEquipe"),
        inverseJoinColumns = @JoinColumn(name = "idJoueur", referencedColumnName = "idJoueur")
    )
    private List<Joueur> joueurs;

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public String getColonneRemplissage() {
        return colonneRemplissage;
    }

    public void setColonneRemplissage(String colonneRemplissage) {
        this.colonneRemplissage = colonneRemplissage;
    }
}
