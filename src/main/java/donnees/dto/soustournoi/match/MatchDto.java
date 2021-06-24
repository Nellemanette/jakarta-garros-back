package donnees.dto.soustournoi.match;


import java.util.List;

public class MatchDto {

    private int idMatch;

    private String dateDebut;

    private boolean estMatchValide;

    private String duree;

    private ArbitreDto arbitre;

    private List<EquipeDto> equipes;

    private Integer score1;

    private Integer score2;

    private int idSousTournoi;

    private CourtDto court;

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

    public ArbitreDto getArbitre() {
        return arbitre;
    }

    public void setArbitre(ArbitreDto arbitre) {
        this.arbitre = arbitre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public List<EquipeDto> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<EquipeDto> equipes) {
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

    public int getIdSousTournoi() {
        return idSousTournoi;
    }

    public void setIdSousTournoi(int idSousTournoi) {
        this.idSousTournoi = idSousTournoi;
    }

    public CourtDto getCourt() {
        return court;
    }

    public void setCourt(CourtDto court) {
        this.court = court;
    }
}
