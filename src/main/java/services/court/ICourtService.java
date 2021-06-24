package services.court;

import donnees.dto.soustournoi.match.CourtDto;
import donnees.entites.soustournoi.match.Court;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICourtService {

    List<CourtDto> obtenirCourt();

    List<CourtDto> consulterCourtDtoRecuperes(List<Court> listedesCourt);
}