package services.login;


import convertisseurs.utilisateur.UtilisateurConvertisseur;
import donnees.dto.utilisateur.UtilisateurSansMdpDto;
import donnees.entites.utilisateur.Utilisateur;
import org.apache.commons.codec.digest.DigestUtils;
import repertoires.utilisateur.IUtilisateurRepertoire;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LoginService implements ILoginService {

     @EJB
     private IUtilisateurRepertoire utilisateurRepertoire ;

     @Override
     public UtilisateurSansMdpDto demandeConnexion (String login, String motDePasse){
         Utilisateur utilisateur = utilisateurRepertoire.consulterUtilisateurParLogin(login);
         String motDePasseFourniHashe = new DigestUtils("SHA3-256").digestAsHex(motDePasse);
         return estUtilisateurAuthentifie(motDePasseFourniHashe, utilisateur)
             ? UtilisateurConvertisseur.convertirVersUtilisateurDto(utilisateur)
             : null;
     }

    private boolean estUtilisateurAuthentifie(String motDePasse, Utilisateur utilisateur) {
        return possedeLoginExistant(utilisateur) && possedeMotDePasseCorrect(motDePasse, utilisateur);
    }

    private boolean possedeLoginExistant(Utilisateur utilisateur) {
        return utilisateur != null;
    }

    private boolean possedeMotDePasseCorrect(String motDePasse, Utilisateur utilisateur) {
        return utilisateur.getMotDePasse().equals(motDePasse);
    }
}
