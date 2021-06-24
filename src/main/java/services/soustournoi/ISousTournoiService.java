package services.soustournoi;

import donnees.dto.soustournoi.SousTournoiDto;
import donnees.dto.soustournoi.SousTournoiSimplifieDto;
import donnees.dto.soustournoi.match.MatchDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ISousTournoiService {

    SousTournoiDto consulterSousTournoiSeul(int idSousTournoi);

    void supprimerMatch(int idMatchASupprimer);

    MatchDto consulterMatchSeul(int idMatchAConsulter);

    MatchDto creerMatch(MatchDto donneesCreationMatch);

    MatchDto modifierMatch(MatchDto donneesModificationMatch);

    List<SousTournoiSimplifieDto> consulterTousLesSousTournoisSimplifies();

    SousTournoiSimplifieDto validerSousTournoi(int idSousTournoiAValider);
}
