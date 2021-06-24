package controleurs.soustournoi;

import constantes.reponse.ReponseConstantes;
import donnees.dto.soustournoi.SousTournoiSimplifieDto;
import services.reponse.IGestionnaireReponseService;
import services.soustournoi.ISousTournoiService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SousTournoiCollection", urlPatterns = "/soustournois")
public class SousTournoiCollectionControleur extends HttpServlet {

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    @EJB
    private ISousTournoiService sousTournoiService;

    protected void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        List<SousTournoiSimplifieDto> listeSousTournoisSimplifies = sousTournoiService.consulterTousLesSousTournoisSimplifies();
        envoyerReponseRecuperationListeSousTournoisSimplifies(listeSousTournoisSimplifies, reponse);
    }

    private void envoyerReponseRecuperationListeSousTournoisSimplifies(List<SousTournoiSimplifieDto> listeSousTournoisSimplifies, HttpServletResponse reponse) throws IOException{
        gestionnaireReponseService.envoiReponseAvecDonnees(listeSousTournoisSimplifies, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }

}
