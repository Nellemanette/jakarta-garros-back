package repertoires.arbitre;

import donnees.entites.soustournoi.match.Arbitre;
import repertoires.ParentRepertoire;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ArbitreRepertoire extends ParentRepertoire implements IArbitreRepertoire {


    @Override
    public List<Arbitre> obtenirArbitre() {
        String requeteConsulter = "select a from Arbitre a";
        return gestionnaireDesEntites
            .createQuery(requeteConsulter, Arbitre.class)
            .getResultList();
    }

}
