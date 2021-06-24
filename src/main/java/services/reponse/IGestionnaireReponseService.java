package services.reponse;

import javax.ejb.Local;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Fournit des methodes pour envoyer une reponse au front.
 * Si elle contient un body, celui-ci peut posseder une entite seule ou une liste d'entites.
 */
@Local
public interface IGestionnaireReponseService {

    /**
     * Envoie une reponse avec le code de statut HTTP fourni, contenant, dans son body,
     * la collection d'entites qui a fait l'objet d'une requete de recuperation (GET /collection).
     * @param listeEntites Liste d'entites recuperees
     * @param reponse      Reponse retournee (celle prise en parametre de la methode appelee de la servlet)
     * @param <TEntite>    Classe de l'entite (generique, pour prendre n'importe quel objet)
     * @throws IOException en cas d'erreur de creation de l'objet de sortie du contenu du body renvoye
     */
    <TEntite> void envoiReponseAvecDonnees(ArrayList<TEntite> listeEntites, HttpServletResponse reponse, int codeDeStatutHttp, String message) throws IOException;

    /**
     * Envoie une reponse avec le code de statut HTTP fourni, contenant, dans son body,
     * l'entite qui a fait l'objet d'une requete de recuperation, de creation ou de modification.
     * @param entite Entite recuperee
     * @param reponse Reponse retournee (celle prise en parametre de la methode appelee de la servlet)
     * @param <TEntite> Classe de l'entite (generique, pour prendre n'importe quel objet)
     * @throws IOException en cas d'erreur de creation de l'objet de sortie du contenu du body renvoye
     */
    <TEntite> void envoiReponseAvecDonnees(TEntite entite, HttpServletResponse reponse, int codeDeStatutHttp, String message) throws IOException;

    /**
     * Envoie une reponse 200 OK sans body. Appel uniquement par une route DELETE (suppression d'entite).
     *
     * @param reponse Reponse renvoyee (celle prise en parametre de la methode appelee de la servlet)
     */
    void envoiReponseSansDonnees(HttpServletResponse reponse);

}
