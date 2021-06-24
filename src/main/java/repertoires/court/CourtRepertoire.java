package repertoires.court;

import donnees.entites.soustournoi.match.Court;
import repertoires.ParentRepertoire;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CourtRepertoire extends ParentRepertoire implements ICourtRepertoire {


    @Override
    public List<Court> obtenirCourt() {
        String requeteConsulter = "select c from Court c";
        return gestionnaireDesEntites
                .createQuery(requeteConsulter, Court.class)
                .getResultList();
    }

}
