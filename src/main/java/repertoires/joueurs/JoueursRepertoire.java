package repertoires.joueurs;

import donnees.entites.soustournoi.match.Joueur;
import repertoires.ParentRepertoire;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class JoueursRepertoire extends ParentRepertoire implements IJoueursRepertoire {

    @Override
    public List<Joueur> obtenirJoueur() {
        String requeteConsulter = "select j from Joueur j";
        return gestionnaireDesEntites
                .createQuery(requeteConsulter, Joueur.class)
                .getResultList();
    }

}
