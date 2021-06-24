package convertisseurs.soustournoi.match;

import donnees.dto.soustournoi.match.ArbitreDto;
import donnees.entites.soustournoi.match.Arbitre;

public class ArbitreConvertisseur {

    public static ArbitreDto convertirVersArbitreDto(Arbitre arbitreAConvertir){

        if(arbitreAConvertir == null)
            return null;

        ArbitreDto arbitreConverti = new ArbitreDto();
        arbitreConverti.setIdArbitre(arbitreAConvertir.getIdArbitre());
        arbitreConverti.setNomArbitre(arbitreAConvertir.getNomArbitre());
        arbitreConverti.setPrenomArbitre(arbitreAConvertir.getPrenomArbitre());
        arbitreConverti.setNationaliteArbitre(arbitreAConvertir.getNationaliteArbitre());

        return arbitreConverti;
    }

}
