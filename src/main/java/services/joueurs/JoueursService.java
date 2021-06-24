package services.joueurs;

import convertisseurs.soustournoi.match.JoueurConvertisseur;
import donnees.dto.soustournoi.match.JoueurDto;
import donnees.entites.soustournoi.match.Joueur;
import repertoires.joueurs.IJoueursRepertoire;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JoueursService implements IJoueursService {

    @EJB
    private IJoueursRepertoire repository;

    @Override
    public List<JoueurDto> obtenirJoueurs() {
        List<Joueur> listeJoueurs = repository.obtenirJoueur();
        return consulterJoueurDtoRecuperes(listeJoueurs);
    }

    @Override
    public List<JoueurDto> consulterJoueurDtoRecuperes(List<Joueur> listeJoueurs) {
        List<JoueurDto> listeJoueursDto = new ArrayList<>();
        listeJoueurs.forEach(resultat -> listeJoueursDto
                .add(JoueurConvertisseur.convertirVersJoueurDto(resultat)));
        return listeJoueursDto;
    }
}
