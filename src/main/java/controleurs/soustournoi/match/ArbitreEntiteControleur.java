package controleurs.soustournoi.match;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constantes.reponse.ReponseConstantes;
import donnees.dto.soustournoi.match.ArbitreDto;
import services.arbitre.IArbitreService;
import services.reponse.IGestionnaireReponseService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArbitreEntiteControleur", urlPatterns = "/arbitres")
public class ArbitreEntiteControleur extends HttpServlet {

    private final Gson gson = new GsonBuilder().serializeNulls().create();
    @EJB
    private IArbitreService arbitreService;

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArbitreDto> listeArbitres = arbitreService.obtenirArbitres();
        System.out.println(listeArbitres);
        if(listeArbitres == null)
            gestionnaireReponseService.envoiReponseSansDonnees(response);
        envoyerReponseRecuperationArbitre(listeArbitres, response);

    }
    private void envoyerReponseRecuperationArbitre(List<ArbitreDto> listeArbitre, HttpServletResponse reponse) throws IOException{
        gestionnaireReponseService.envoiReponseAvecDonnees(listeArbitre, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_SUCCES,
                ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }
}

