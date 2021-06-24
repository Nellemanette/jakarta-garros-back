package convertisseurs.soustournoi.match;

import donnees.dto.soustournoi.match.CourtDto;
import donnees.entites.soustournoi.match.Court;

public class CourtConvertisseur {

    public static CourtDto convertirVersCourtDto(Court courtAConvertir){

        if(courtAConvertir == null)
            return null;

        CourtDto courtConverti = new CourtDto();
        courtConverti.setIdCourt(courtAConvertir.getIdCourt());
        courtConverti.setNomCourt(courtAConvertir.getNomCourt());
        courtConverti.setTypeCourt(courtAConvertir.getTypeCourt());
        courtConverti.setZoneCourt(courtAConvertir.getZoneCourt());

        return courtConverti;
    }

}
