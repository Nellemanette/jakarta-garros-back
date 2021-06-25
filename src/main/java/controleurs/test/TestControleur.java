package controleurs.test;

import constantes.reponse.ReponseConstantes;
import donnees.dto.utilisateur.UtilisateurSansMdpDto;
import services.reponse.IGestionnaireReponseService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Test",  urlPatterns ="/")
public class TestControleur extends HttpServlet {

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    @Override
    protected void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }

}
