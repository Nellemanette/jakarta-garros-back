package repertoires.arbitre;

import donnees.entites.soustournoi.match.Arbitre;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IArbitreRepertoire {

    List<Arbitre> obtenirArbitre();
}
