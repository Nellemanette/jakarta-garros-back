package convertisseurs.soustournoi.match;

import donnees.dto.soustournoi.match.EquipeDto;
import donnees.dto.soustournoi.match.MatchDto;
import donnees.entites.soustournoi.match.Equipe;
import donnees.entites.soustournoi.match.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchConvertisseur {

    public static MatchDto convertirVersMatchDto(Match matchAconvertir){

        if(matchAconvertir == null)
            return null;

        MatchDto matchConverti = new MatchDto();
        matchConverti.setIdMatch(matchAconvertir.getIdMatch());
        matchConverti.setDateDebut(matchAconvertir.getDateDebut());
        matchConverti.setEstMatchValide(matchAconvertir.isEstMatchValide());
        matchConverti.setDuree(matchAconvertir.getDuree());
        matchConverti.setArbitre(ArbitreConvertisseur.convertirVersArbitreDto(matchAconvertir.getArbitre()));
        matchConverti.setEquipes(convertirEquipes(matchAconvertir.getEquipes()));
        matchConverti.setScore1(matchAconvertir.getScore1());
        matchConverti.setScore2(matchAconvertir.getScore2());
        matchConverti.setIdSousTournoi(matchAconvertir.getSousTournoi().getIdSousTournoi());
        matchConverti.setCourt(CourtConvertisseur.convertirVersCourtDto(matchAconvertir.getCourt()));
        return matchConverti;
    }

    private static List<EquipeDto> convertirEquipes(List<Equipe> equipesAConvertir){
        List<EquipeDto> equipesConverties = new ArrayList<>();
        equipesAConvertir.forEach(equipeAConvertir -> ajouterEquipeConvertieALaListe(equipesConverties, equipeAConvertir));
        return equipesConverties;
    }

    private static void ajouterEquipeConvertieALaListe(List<EquipeDto> equipesConverties, Equipe equipeAConvertir) {
        equipesConverties.add(EquipeConvertisseur.convertirVersEquipeDto(equipeAConvertir));
    }

}
