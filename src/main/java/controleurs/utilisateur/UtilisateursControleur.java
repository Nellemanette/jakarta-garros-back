package controleurs.utilisateur;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constantes.reponse.ReponseConstantes;
import donnees.dto.utilisateur.UtilisateurAvecMdpDto;
import donnees.dto.utilisateur.UtilisateurSansMdpDto;
import services.reponse.IGestionnaireReponseService;
import services.utilisateur.IUtilisateurService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UtilisateursControleur", urlPatterns = "/utilisateurs/all")
public class UtilisateursControleur extends HttpServlet {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @EJB
    private IUtilisateurService utilisateurService;

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    @Override
    protected void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        List<UtilisateurAvecMdpDto> listeUtilisateurs = utilisateurService.obtenirTousLesUtilisateurs();
        envoyerReponseRecuperationListeUtilisateurs(listeUtilisateurs, reponse);
    }

    private void envoyerReponseRecuperationListeUtilisateurs(List<UtilisateurAvecMdpDto> utilisateurRecupere, HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(utilisateurRecupere, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }
}
