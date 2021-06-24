package repertoires.court;

import donnees.entites.soustournoi.match.Court;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICourtRepertoire {

    List<Court> obtenirCourt();
}
