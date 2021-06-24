package services.arbitre;

import convertisseurs.soustournoi.match.ArbitreConvertisseur;
import donnees.dto.soustournoi.match.ArbitreDto;
import donnees.entites.soustournoi.match.Arbitre;
import repertoires.arbitre.IArbitreRepertoire;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ArbitreService implements IArbitreService {

    @EJB
    private IArbitreRepertoire repository;

    @Override
    public List<ArbitreDto> obtenirArbitres() {
        List<Arbitre> listeArbitres = repository.obtenirArbitre();
        return consulterArbitreDtoRecuperes(listeArbitres);
    }

    @Override
    public List<ArbitreDto> consulterArbitreDtoRecuperes(List<Arbitre> listeArbitres) {
        List<ArbitreDto> listeArbitresDto = new ArrayList<>();
        listeArbitres.forEach(resultat -> listeArbitresDto
                .add(ArbitreConvertisseur.convertirVersArbitreDto(resultat)));
        return listeArbitresDto;
    }
}
