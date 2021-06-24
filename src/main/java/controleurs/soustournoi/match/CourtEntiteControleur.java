package controleurs.soustournoi.match;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constantes.reponse.ReponseConstantes;
import donnees.dto.soustournoi.match.CourtDto;
import services.court.ICourtService;
import services.reponse.IGestionnaireReponseService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourtEntiteControleur", urlPatterns = "/courts")
public class CourtEntiteControleur extends HttpServlet {

    private final Gson gson = new GsonBuilder().serializeNulls().create();
    @EJB
    private ICourtService courtService;

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CourtDto> listeCourt = courtService.obtenirCourt();
        System.out.println(listeCourt);
        if(listeCourt == null)
            gestionnaireReponseService.envoiReponseSansDonnees(response);
        envoyerReponseRecuperationNomJoueurs(listeCourt, response);

    }
    private void envoyerReponseRecuperationNomJoueurs(List<CourtDto> listeCourt, HttpServletResponse reponse) throws IOException{
        gestionnaireReponseService.envoiReponseAvecDonnees(listeCourt, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_SUCCES,
                ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }
}

