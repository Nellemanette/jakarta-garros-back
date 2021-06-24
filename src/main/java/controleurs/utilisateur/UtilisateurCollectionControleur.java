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


@WebServlet(name = "UtilisateurCollection", urlPatterns = "/utilisateurs")
public class UtilisateurCollectionControleur extends HttpServlet {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @EJB
    private IUtilisateurService utilisateurService;

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    @Override
    protected void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        List<UtilisateurSansMdpDto> listeUtilisateurs = utilisateurService.consulterTousLesUtilisateurs();
        envoyerReponseRecuperationListeUtilisateurs(listeUtilisateurs, reponse);
    }

    @Override
    protected void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        UtilisateurAvecMdpDto nouvelUtilisateur = gson.fromJson(requete.getReader(), UtilisateurAvecMdpDto.class);
        UtilisateurSansMdpDto utilisateurAConsulter = utilisateurService.consulterUtilisateurParLogin(nouvelUtilisateur.getLogin());
        if (utilisateurAConsulter == null) {
            UtilisateurSansMdpDto utilisateurCreee = utilisateurService.creerUtilisateur(nouvelUtilisateur);
            envoyerReponseRecuperationUtilisateurSeul(utilisateurCreee, reponse);
        }
        else {
            envoyerReponseUtilisateurDejaExistant(reponse);
        }
    }

    private void envoyerReponseRecuperationListeUtilisateurs(List<UtilisateurSansMdpDto> utilisateurRecupere, HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(utilisateurRecupere, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }

    private void envoyerReponseRecuperationUtilisateurSeul(UtilisateurSansMdpDto utilisateurRecupere, HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(utilisateurRecupere, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }

    private void envoyerReponseUtilisateurDejaExistant(HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_MAUVAISE_REQUETE,
            ReponseConstantes.MESSAGE_REPONSE_UTILISATEUR_DEJA_EXISTANT);
    }
}
