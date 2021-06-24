package controleurs.soustournoi.match;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constantes.reponse.ReponseConstantes;
import donnees.dto.soustournoi.match.JoueurDto;
import services.joueurs.IJoueursService;
import services.reponse.IGestionnaireReponseService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "JoueurEntiteControleur", urlPatterns = "/joueurs")
public class JoueurEntiteControleur extends HttpServlet {

    private final Gson gson = new GsonBuilder().serializeNulls().create();
    @EJB
    private IJoueursService joueursService;

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<JoueurDto> listeJoueurs = joueursService.obtenirJoueurs();
        System.out.println(listeJoueurs);
        if(listeJoueurs == null)
            gestionnaireReponseService.envoiReponseSansDonnees(response);
        envoyerReponseRecuperationJoueur(listeJoueurs, response);

    }
    private void envoyerReponseRecuperationJoueur(List<JoueurDto> listeArbitre, HttpServletResponse reponse) throws IOException{
        gestionnaireReponseService.envoiReponseAvecDonnees(listeArbitre, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_SUCCES,
                ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }
}

