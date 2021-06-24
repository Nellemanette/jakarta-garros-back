package convertisseurs.soustournoi.match;

import donnees.dto.soustournoi.match.JoueurDto;
import donnees.entites.soustournoi.match.Joueur;

public class JoueurConvertisseur {

    public static JoueurDto convertirVersJoueurDto(Joueur joueurAConvertir){

        if(joueurAConvertir == null)
            return null;

        JoueurDto joueurConverti = new JoueurDto();
        joueurConverti.setIdJoueur(joueurAConvertir.getIdJoueur());
        joueurConverti.setNomJoueur(joueurAConvertir.getNomJoueur());
        joueurConverti.setPrenomJoueur(joueurAConvertir.getPrenomJoueur());
        joueurConverti.setNationaliteJoueur(joueurAConvertir.getNationaliteJoueur());
        joueurConverti.setSexe(joueurAConvertir.getSexe());
        joueurConverti.setClassementJoueur(joueurAConvertir.getClassementJoueur());

        return joueurConverti;

    }

}
