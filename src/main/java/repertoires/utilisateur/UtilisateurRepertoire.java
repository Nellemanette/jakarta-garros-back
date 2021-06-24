package repertoires.utilisateur;

import donnees.entites.utilisateur.Utilisateur;
import repertoires.ParentRepertoire;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UtilisateurRepertoire extends ParentRepertoire implements IUtilisateurRepertoire {

    @Override
    public Utilisateur creerUtilisateur(Utilisateur nouvelUtilisateur) {
        gestionnaireDesEntites.persist(nouvelUtilisateur);
        return nouvelUtilisateur;
    }

    @Override
    public List<Utilisateur> consulterTousLesUtilisateurs() {
        String requeteConsultation = "select u from Utilisateur u ";
        return gestionnaireDesEntites
            .createQuery(requeteConsultation, Utilisateur.class)
            .getResultList();
    }

    @Override
    public Utilisateur consulterUtilisateurSeul(int idUtilisateur) {
        return gestionnaireDesEntites.find(Utilisateur.class, idUtilisateur);
    }

    @Override
    public Utilisateur modifierUtilisateur(Utilisateur donneesModifications) {
        Utilisateur utilisateurAModifier = gestionnaireDesEntites.find(Utilisateur.class, donneesModifications.getIdUtilisateur());
        utilisateurAModifier.setLogin(donneesModifications.getLogin());
        utilisateurAModifier.setMotDePasse(donneesModifications.getMotDePasse());
        gestionnaireDesEntites.persist(utilisateurAModifier);
        return utilisateurAModifier;
    }

    @Override
    public void supprimerUtilisateur(Utilisateur utilisateurASupprimer, int idUtilisateur) {
        utilisateurASupprimer = gestionnaireDesEntites.find(Utilisateur.class, idUtilisateur);
        gestionnaireDesEntites.remove(utilisateurASupprimer);
    }

    @Override
    public Utilisateur consulterUtilisateurParLogin(String login) {
        return gestionnaireDesEntites
            .createQuery("select u from Utilisateur u where u.login = :login ", Utilisateur.class)
            .setParameter("login", login)
            .getResultList()
            .stream()
            .findFirst()
            .orElse(null);
    }
}