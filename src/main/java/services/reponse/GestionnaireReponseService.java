package services.reponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constantes.reponse.ReponseConstantes;
import donnees.dto.reponse.ReponseDto;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * S'occupe d'envoyer la reponse de l'API au front avec les donnnees, le code de statut HTTP et le message fournis.
 */
@Stateless
public class GestionnaireReponseService implements IGestionnaireReponseService {

    /**
     * Objet permettant de serialiser les donnees recuperees en JSON.
     */
    private final Gson gson = new GsonBuilder().serializeNulls().create();

    /**
     * Envoie une reponse avec le code de statut HTTP fourni, contenant, dans son body,
     * la collection d'entites qui a fait l'objet d'une requete de recuperation (GET /collection).
     * Si la liste d'entites fournie est null ou vide, aucun body n'est retourne avec la reponse.
     *
     * @param listeEntites Liste d'entites recuperees
     * @param reponse      Reponse retournee (celle prise en parametre de la methode appelee de la servlet)
     * @param <TEntite>    Classe de l'entite (generique, peut prendre n'importe quel objet)
     * @throws IOException en cas d'erreur de creation de l'objet de sortie du contenu du body renvoye
     */
    @Override
    public <TEntite> void envoiReponseAvecDonnees(ArrayList<TEntite> listeEntites, HttpServletResponse reponse, int codeDeStatutHttp, String message) throws IOException {
        parametrerReponse(reponse, codeDeStatutHttp);
        retournerDonneesDansReponse(reponse, listeEntites, message);
    }

    /**
     * Envoie une reponse avec le code de statut HTTP fourni, contenant, dans son body,
     * l'entite qui a fait l'objet d'une requete de recuperation, de creation ou de modification.
     * Si l'entite fournie est null, aucun body n'est retourne avec la reponse.
     *
     * @param entite    Entite recuperee
     * @param reponse   Reponse retournee (celle prise en parametre de la methode appelee de la servlet)
     * @param <TEntite> Classe de l'entite (generique, pour prendre n'importe quel objet)
     * @throws IOException en cas d'erreur de creation de l'objet de sortie du contenu du body renvoye
     */
    @Override
    public <TEntite> void envoiReponseAvecDonnees(TEntite entite, HttpServletResponse reponse, int codeDeStatutHttp, String message) throws IOException {
        parametrerReponse(reponse, codeDeStatutHttp);
        retournerDonneesDansReponse(reponse, entite, message);
    }

    /**
     * Envoie une reponse 200 OK sans body. Utilise uniquement par une route DELETE (suppression d'entite).
     *
     * @param reponse Reponse renvoyee (celle prise en parametre de la methode appelee de la servlet)
     */
    @Override
    public void envoiReponseSansDonnees(HttpServletResponse reponse) {

        parametrerReponse(reponse, ReponseConstantes.CODE_STATUT_HTTP_SUCCES);

    }

    /**
     * Donne l'en-tete de gestion des CORS à la reponse et la parametre pour qu'elle puisse renvoyer du JSON (encodage UTF8).
     *
     * @param reponse Reponse renvoyee par l'API (celle prise en parametre de la methode appelee de la servlet)
     * @param codeDeStatutHttp Code de statut HTTP de la reponse
     */
    private void parametrerReponse(HttpServletResponse reponse, int codeDeStatutHttp) {
        reponse.addHeader("Access-Control-Allow-Origin", "*");
        reponse.setContentType("application/json");
        reponse.setCharacterEncoding("UTF-8");
        reponse.setStatus(codeDeStatutHttp);

        reponse.addHeader("Access-Control-Allow-Methods", "POST,GET, PUT, DELETE");
        reponse.addHeader("Access-Control-Max-Age", "1000");
        reponse.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
        reponse.addHeader("Access-Control-Allow-Credentials", "true");
    }

    /**
     * @param reponse         Reponse renvoyee par l'API (celle prise en parametre de la methode appelee de la servlet)
     * @param donneeRetournee Donnee(s) retournee(s)
     * @param message         message transmis au front
     * @param <TDonnee>       Type de la donnee renvoyee
     * @throws IOException en cas d'erreur de creation de l'objet de sortie du contenu du body renvoye
     */
    private <TDonnee> void retournerDonneesDansReponse(HttpServletResponse reponse, TDonnee donneeRetournee, String message) throws IOException {
        ReponseDto<TDonnee> contenuReponse = new ReponseDto<>();
        contenuReponse.setContenu(donneeRetournee);
        contenuReponse.setMessage(message);

        PrintWriter sortie = reponse.getWriter();
        sortie.print(gson.toJson(contenuReponse));
    }
}
