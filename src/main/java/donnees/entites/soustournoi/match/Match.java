package donnees.entites.soustournoi.match;

import donnees.entites.soustournoi.SousTournoi;

import javax.persistence.*;
import java.util.List;

/**
 * Entite des match.
 */
@Entity
@Table(name = "detail_match")
public class Match {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idMatch")
    private int idMatch;

    @Column(name="dateDebut")
    private String dateDebut;

    @Column(name="estMatchValide")
    private boolean estMatchValide;

    @Column(name="duree")
    private String duree;

    @ManyToOne(targetEntity = SousTournoi.class)
    @JoinColumn( name="idSousTournoi")
    private SousTournoi sousTournoi;

    @ManyToOne(targetEntity = Arbitre.class)
    @JoinColumn(name="idArbitre")
    private Arbitre arbitre;

    @ManyToMany
    @JoinTable(
        name = "participation_match",
        joinColumns = @JoinColumn(name = "idMatch", referencedColumnName = "idMatch"),
        inverseJoinColumns = @JoinColumn(name = "idEquipe", referencedColumnName = "idEquipe")
    )
    private List<Equipe> equipes;

    private Integer score1;

    private Integer score2;

    @OneToOne
    @JoinColumn(name="idCourt")
    private Court court;

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public boolean isEstMatchValide() {
        return estMatchValide;
    }

    public void setEstMatchValide(boolean estMatchValide) {
        this.estMatchValide = estMatchValide;
    }

    public SousTournoi getSousTournoi() {
        return sousTournoi;
    }

    public void setSousTournoi(SousTournoi sousTournoi) {
        this.sousTournoi = sousTournoi;
    }

    public Arbitre getArbitre() {
        return arbitre;
    }

    public void setArbitre(Arbitre arbitre) {
        this.arbitre = arbitre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }
}
