package convertisseurs.utilisateur;

import donnees.dto.utilisateur.UtilisateurAvecMdpDto;
import donnees.dto.utilisateur.UtilisateurSansMdpDto;
import donnees.entites.utilisateur.Utilisateur;

public class UtilisateurConvertisseur {

    public static UtilisateurSansMdpDto convertirVersUtilisateurDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        else {
            UtilisateurSansMdpDto utilisateurDto = new UtilisateurSansMdpDto();
            utilisateurDto.setIdUtilisateur(utilisateur.getIdUtilisateur());
            utilisateurDto.setLogin(utilisateur.getLogin());
            utilisateurDto.setRole(utilisateur.getRole());
            return utilisateurDto;
        }

    }
    public static UtilisateurAvecMdpDto convertirVersUtilisateurAvecDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        else {
            UtilisateurAvecMdpDto utilisateurAvecDto = new UtilisateurAvecMdpDto();
            utilisateurAvecDto.setIdUtilisateur(utilisateur.getIdUtilisateur());
            utilisateurAvecDto.setMotDePasse(utilisateur.getMotDePasse());
            utilisateurAvecDto.setLogin(utilisateur.getLogin());
            utilisateurAvecDto.setRole(utilisateur.getRole());
            return utilisateurAvecDto;
        }

    }
}
