package services.court;

import convertisseurs.soustournoi.match.CourtConvertisseur;
import donnees.dto.soustournoi.match.CourtDto;
import donnees.entites.soustournoi.match.Court;
import repertoires.court.ICourtRepertoire;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CourtService implements ICourtService {
    @EJB
    private ICourtRepertoire repository;

    @Override
    public List<CourtDto> obtenirCourt() {
        List<Court> listeCourt = repository.obtenirCourt();
        return consulterCourtDtoRecuperes(listeCourt);
    }

    @Override
    public List<CourtDto> consulterCourtDtoRecuperes(List<Court> listeCourt) {
        List<CourtDto> listeCourtDto = new ArrayList<>();
        listeCourt.forEach(resultat -> listeCourtDto
                .add(CourtConvertisseur.convertirVersCourtDto(resultat)));
        return listeCourtDto;
    }
}
