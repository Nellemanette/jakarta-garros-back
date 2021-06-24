package controleurs.soustournoi.match;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constantes.reponse.ReponseConstantes;
import donnees.dto.soustournoi.match.MatchDto;
import services.reponse.IGestionnaireReponseService;
import services.soustournoi.ISousTournoiService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MatchCollection", urlPatterns = "/matchs")
public class MatchCollectionControleur extends HttpServlet {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    @EJB
    private ISousTournoiService sousTournoiService;

    @Override
    protected void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        MatchDto donneesCreationMatch = gson.fromJson(requete.getReader(), MatchDto.class);
        MatchDto matchCree = sousTournoiService.creerMatch(donneesCreationMatch);
        envoyerReponseRecuperationMatchSeul(matchCree, reponse);
    }

    private void envoyerReponseRecuperationMatchSeul(MatchDto matchRecupere, HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(matchRecupere, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }
}
