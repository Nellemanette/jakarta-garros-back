package services.login;
import donnees.dto.utilisateur.UtilisateurSansMdpDto;

import javax.ejb.Local;

@Local
public interface ILoginService {

    UtilisateurSansMdpDto demandeConnexion (String login, String password);
}
