package services.utilisateur;

import donnees.dto.utilisateur.UtilisateurAvecMdpDto;
import donnees.dto.utilisateur.UtilisateurSansMdpDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IUtilisateurService {
    UtilisateurSansMdpDto creerUtilisateur(UtilisateurAvecMdpDto utilisateurDto);

    UtilisateurSansMdpDto consulterUtilisateurSeul(int idUtilisateur);

    void supprimerUtilisateur(UtilisateurSansMdpDto utilisateurASupprimerDto);

    UtilisateurSansMdpDto modifierUtilisateur(UtilisateurAvecMdpDto utilisateurAvecMdpDto);

    List<UtilisateurSansMdpDto> consulterTousLesUtilisateurs();

    List<UtilisateurAvecMdpDto> obtenirTousLesUtilisateurs();

    UtilisateurSansMdpDto consulterUtilisateurParLogin(String login);
}
