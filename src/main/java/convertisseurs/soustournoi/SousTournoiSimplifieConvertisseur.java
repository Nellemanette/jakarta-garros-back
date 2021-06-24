package convertisseurs.soustournoi;

import donnees.dto.soustournoi.SousTournoiSimplifieDto;
import donnees.entites.soustournoi.SousTournoi;

/**
 * Convertit une entite SousTournoi en SousTournoiSimplifieDto.
 */
public class SousTournoiSimplifieConvertisseur {

    public static SousTournoiSimplifieDto convertirVersSousTournoiSimplifieDto(SousTournoi sousTournoiAConvertir){
        SousTournoiSimplifieDto sousTournoiSimplifie = new SousTournoiSimplifieDto();
        sousTournoiSimplifie.setIdSousTournoi(sousTournoiAConvertir.getIdSousTournoi());
        sousTournoiSimplifie.setTypeSousTournoi(sousTournoiAConvertir.getTypeSousTournoi());
        sousTournoiSimplifie.setDateDebutSousTournoi(sousTournoiAConvertir.getDateDebutSousTournoi());
        sousTournoiSimplifie.setDateFinSousTournoi(sousTournoiAConvertir.getDateFinSousTournoi());
        sousTournoiSimplifie.setEstSousTournoiValide(sousTournoiAConvertir.getValiditeSousTournoi());
        return sousTournoiSimplifie;
    }
}
