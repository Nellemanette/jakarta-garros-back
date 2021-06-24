package convertisseurs.soustournoi;

import convertisseurs.soustournoi.match.MatchConvertisseur;
import donnees.dto.soustournoi.SousTournoiDto;
import donnees.dto.soustournoi.match.MatchDto;
import donnees.entites.soustournoi.SousTournoi;
import donnees.entites.soustournoi.match.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Convertit une entite SousTournoi en SousTournoiDto.
 */
public class SousTournoiConvertisseur {

    public static SousTournoiDto convertirVersSousTournoiDto(SousTournoi entiteSousTournoi){

        if (entiteSousTournoi == null)
            return null;

        SousTournoiDto sousTournoiDto = new SousTournoiDto();
        sousTournoiDto.setIdSousTournoi(entiteSousTournoi.getIdSousTournoi());
        sousTournoiDto.setDateDebutSousTournoi(entiteSousTournoi.getDateDebutSousTournoi());
        sousTournoiDto.setDateFinSousTournoi(entiteSousTournoi.getDateFinSousTournoi());
        sousTournoiDto.setValiditeSousTournoi(entiteSousTournoi.getValiditeSousTournoi());
        sousTournoiDto.setTypeSousTournoi(entiteSousTournoi.getTypeSousTournoi());
        sousTournoiDto.setListeMatch(convertirListeMatch(entiteSousTournoi.getListeMatch()));

        return sousTournoiDto;
    }

    private static List<MatchDto> convertirListeMatch(List<Match> listeMatchAConvertir){
        List<MatchDto> matchConvertis = new ArrayList<>();
        listeMatchAConvertir.forEach(matchAConvertir -> ajouterMatchAListeMatchDto(matchConvertis, matchAConvertir));
        return matchConvertis;
    }

    private static void ajouterMatchAListeMatchDto(List<MatchDto> matchConvertis, Match matchAConvertir) {
        matchConvertis.add(MatchConvertisseur.convertirVersMatchDto(matchAConvertir));
    }

}
