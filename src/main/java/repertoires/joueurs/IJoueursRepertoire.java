package repertoires.joueurs;

import donnees.entites.soustournoi.match.Joueur;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IJoueursRepertoire {

    List<Joueur> obtenirJoueur();
}
