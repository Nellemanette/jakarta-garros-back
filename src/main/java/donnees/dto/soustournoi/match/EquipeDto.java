package donnees.dto.soustournoi.match;

import java.util.List;

public class EquipeDto {

    private int idEquipe;

    private List<JoueurDto> joueurs;

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public List<JoueurDto> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<JoueurDto> joueurs) {
        this.joueurs = joueurs;
    }

}
