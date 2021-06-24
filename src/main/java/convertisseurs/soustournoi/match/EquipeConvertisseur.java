package convertisseurs.soustournoi.match;

import donnees.dto.soustournoi.match.EquipeDto;
import donnees.dto.soustournoi.match.JoueurDto;
import donnees.entites.soustournoi.match.Equipe;
import donnees.entites.soustournoi.match.Joueur;

import java.util.ArrayList;
import java.util.List;

public class EquipeConvertisseur {

    public static EquipeDto convertirVersEquipeDto(Equipe equipeAConvertir){

        if(equipeAConvertir == null)
            return null;

        EquipeDto equipeConvertie = new EquipeDto();
        equipeConvertie.setIdEquipe(equipeAConvertir.getIdEquipe());
        equipeConvertie.setJoueurs(convertirJoueurs(equipeAConvertir.getJoueurs()));

        return equipeConvertie;
    }

    private static List<JoueurDto> convertirJoueurs(List<Joueur> joueursAConvertir){

        List<JoueurDto> joueursConvertis = new ArrayList<>();
        joueursAConvertir.forEach(joueurAConvertir -> ajouterJoueurConvertiALaListe(joueursConvertis, joueurAConvertir));

        return joueursConvertis;
    }

    private static void ajouterJoueurConvertiALaListe(List<JoueurDto> joueursConvertis, Joueur joueurAConvertir) {
        joueursConvertis.add(JoueurConvertisseur.convertirVersJoueurDto(joueurAConvertir));
    }

}
