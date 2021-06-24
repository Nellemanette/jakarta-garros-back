package repertoires;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repertoire dont doivent heriter tous les nouveaux repertoires pour pouvoir recuperer le gestionnaire des entites.
 */
@Stateless
public class ParentRepertoire {

    /**
     * Gestionnaire des entites qui effectuera des requetes vers la BD. (ORM)
     */
    @PersistenceContext
    protected EntityManager gestionnaireDesEntites;

}
