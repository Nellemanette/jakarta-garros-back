package services.joueurs;

import donnees.dto.soustournoi.match.JoueurDto;
import donnees.entites.soustournoi.match.Joueur;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IJoueursService {
    List<JoueurDto> obtenirJoueurs();
    List<JoueurDto> consulterJoueurDtoRecuperes(List<Joueur> listedesArbitres);
}