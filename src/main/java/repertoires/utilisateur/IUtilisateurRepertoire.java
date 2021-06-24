package repertoires.utilisateur;

import donnees.entites.utilisateur.Utilisateur;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IUtilisateurRepertoire {

    Utilisateur creerUtilisateur(Utilisateur nouvelUtilisateur);

    List<Utilisateur> consulterTousLesUtilisateurs();

    Utilisateur consulterUtilisateurSeul(int idUtilisateur);

    Utilisateur modifierUtilisateur(Utilisateur donneesModifications);

    void supprimerUtilisateur(Utilisateur utilisateurASupprimer, int idUtilisateur);

    Utilisateur consulterUtilisateurParLogin(String login);
}
