package services.utilisateur;

import constantes.utilisateur.UtilisateurConstantes;
import convertisseurs.utilisateur.UtilisateurConvertisseur;
import donnees.dto.utilisateur.UtilisateurAvecMdpDto;
import donnees.dto.utilisateur.UtilisateurSansMdpDto;
import donnees.entites.utilisateur.Utilisateur;
import org.apache.commons.codec.digest.DigestUtils;
import repertoires.utilisateur.IUtilisateurRepertoire;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UtilisateurService implements IUtilisateurService {

    @EJB
    private IUtilisateurRepertoire utilisateurRepertoire;

    @Override
    public UtilisateurSansMdpDto creerUtilisateur(UtilisateurAvecMdpDto utilisateurAvecMdpDto) {
        Utilisateur nouvelUtilisateur = new Utilisateur();
        nouvelUtilisateur.setLogin(utilisateurAvecMdpDto.getLogin());
        String motDePasseHashe = new DigestUtils("SHA3-256").digestAsHex(utilisateurAvecMdpDto.getMotDePasse());
        nouvelUtilisateur.setMotDePasse(motDePasseHashe);
        nouvelUtilisateur.setRole(UtilisateurConstantes.ORGANISATEUR);
        utilisateurRepertoire.creerUtilisateur(nouvelUtilisateur);
        return UtilisateurConvertisseur.convertirVersUtilisateurDto(nouvelUtilisateur);
    }

    @Override
    public List<UtilisateurSansMdpDto> consulterTousLesUtilisateurs() {
        List<Utilisateur> listeUtilisateursAConsulter = utilisateurRepertoire.consulterTousLesUtilisateurs();
        return obtenirListeUtilisateurDtoRecuperes(listeUtilisateursAConsulter);
    }

    @Override
    public List<UtilisateurAvecMdpDto> obtenirTousLesUtilisateurs() {
        List<Utilisateur> listeUtilisateursAConsulter = utilisateurRepertoire.consulterTousLesUtilisateurs();
        return obtenirListeUtilisateurAvecDtoRecuperes(listeUtilisateursAConsulter);
    }
    @Override
    public UtilisateurSansMdpDto consulterUtilisateurSeul(int idUtilisateur) {
        Utilisateur utilisateurAConsulter = utilisateurRepertoire.consulterUtilisateurSeul(idUtilisateur);
        return UtilisateurConvertisseur.convertirVersUtilisateurDto(utilisateurAConsulter);
    }

    @Override
    public void supprimerUtilisateur(UtilisateurSansMdpDto utilisateurASupprimerDto) {
        if(!utilisateurASupprimerDto.getRole().equals(UtilisateurConstantes.ADMIN)){
            Utilisateur utilisateurASupprimer = new Utilisateur();
            utilisateurRepertoire.supprimerUtilisateur(utilisateurASupprimer, utilisateurASupprimerDto.getIdUtilisateur());
        }
    }

    @Override
    public UtilisateurSansMdpDto modifierUtilisateur(UtilisateurAvecMdpDto donneesModifs) {
        Utilisateur utilisateurRecherche = utilisateurRepertoire.consulterUtilisateurSeul(donneesModifs.getIdUtilisateur());
        if (utilisateurRecherche == null) {
            return null;
        }
        Utilisateur utilisateurLogin = utilisateurRepertoire.consulterUtilisateurParLogin(donneesModifs.getLogin());
        return gererNouveauLoginPourModification(donneesModifs, utilisateurLogin);
    }

    @Override
    public UtilisateurSansMdpDto consulterUtilisateurParLogin(String login) {
        Utilisateur utilisateurAConsulter = utilisateurRepertoire.consulterUtilisateurParLogin(login);
        return UtilisateurConvertisseur.convertirVersUtilisateurDto(utilisateurAConsulter);
    }

    private List<UtilisateurSansMdpDto> obtenirListeUtilisateurDtoRecuperes(List<Utilisateur> listeUtilisateursAConsulter) {
        List<UtilisateurSansMdpDto> listeUtilisateurDto = new ArrayList<>();
        listeUtilisateursAConsulter.forEach(utilisateur -> listeUtilisateurDto
                .add(UtilisateurConvertisseur.convertirVersUtilisateurDto(utilisateur)));
        return listeUtilisateurDto;
    }

    private List<UtilisateurAvecMdpDto> obtenirListeUtilisateurAvecDtoRecuperes(List<Utilisateur> listeUtilisateursAConsulter) {
        List<UtilisateurAvecMdpDto> listeUtilisateurDto = new ArrayList<>();
        listeUtilisateursAConsulter.forEach(utilisateur -> listeUtilisateurDto
                .add(UtilisateurConvertisseur.convertirVersUtilisateurAvecDto(utilisateur)));
        return listeUtilisateurDto;
    }

    private UtilisateurSansMdpDto gererNouveauLoginPourModification(UtilisateurAvecMdpDto donneesModifs, Utilisateur utilisateurLogin) {
        return utilisateurLogin == null
            ? recupererUtilisateurModifieAvecNouveauLoginDisponible(donneesModifs)
            :  gererNouveauLoginDejaExistantPourModification(donneesModifs, utilisateurLogin);
    }

    private UtilisateurSansMdpDto recupererUtilisateurModifieAvecNouveauLoginDisponible(UtilisateurAvecMdpDto donneesModifs) {
        Utilisateur utilisateurAModifier = new Utilisateur();
        utilisateurAModifier.setIdUtilisateur(donneesModifs.getIdUtilisateur());
        utilisateurAModifier.setLogin(donneesModifs.getLogin());
        String nouveauMotDePasseHashe = new DigestUtils("SHA3-256").digestAsHex(donneesModifs.getMotDePasse());
        utilisateurAModifier.setMotDePasse(nouveauMotDePasseHashe);
        Utilisateur utilisateurModifie = utilisateurRepertoire.modifierUtilisateur(utilisateurAModifier);
        return UtilisateurConvertisseur.convertirVersUtilisateurDto(utilisateurModifie);
    }

    private UtilisateurSansMdpDto gererNouveauLoginDejaExistantPourModification(UtilisateurAvecMdpDto donneesModifs, Utilisateur utilisateurLogin) {
        return utilisateurLogin.getIdUtilisateur() == donneesModifs.getIdUtilisateur()
            ? recupererUtilisateurModifieAvecNouveauLoginDisponible(donneesModifs)
            : UtilisateurConvertisseur.convertirVersUtilisateurDto(new Utilisateur());
    }
}
