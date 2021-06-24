package services.arbitre;

import donnees.dto.soustournoi.match.ArbitreDto;
import donnees.entites.soustournoi.match.Arbitre;
import javax.ejb.Local;
import java.util.List;

@Local
public interface IArbitreService {
    List<ArbitreDto> obtenirArbitres();
    List<ArbitreDto> consulterArbitreDtoRecuperes(List<Arbitre> listedesArbitres);
}