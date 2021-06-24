package repertoires.soustournoi;

import donnees.entites.soustournoi.match.Arbitre;
import donnees.entites.soustournoi.match.Court;
import donnees.entites.soustournoi.match.Equipe;
import donnees.entites.soustournoi.match.Joueur;
import donnees.entites.soustournoi.match.Match;
import donnees.entites.soustournoi.SousTournoi;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ISousTournoiRepertoire {

    SousTournoi consulterSousTournoiSeul(int idSousTournoi);

    Match consulterMatchSeul(int idMatchAConsulter);

    Match creerMatch(Match nouveauMatch);

    void supprimerMatch(int idMatchASupprimer);

    Arbitre consulterArbitreSeul(int idArbitre);

    Match modifierMatch(Match donneesModificationMatch);

    List<SousTournoi> consulterTousLesSousTournois();

    Joueur consulterJoueurSeul(int idJoueurAConsulter);

    Equipe consulterEquipeSeule(int idEquipe);

    Equipe creerEquipe(Equipe nouvelleEquipe);

    SousTournoi validerSousTournoi(int idSousTournoiAValider);

    void mettreAJourDonneesTemporellesSousTournoi(SousTournoi sousTournoiMisAJour);

    void validerMatch(int idMatchAValider);

}
