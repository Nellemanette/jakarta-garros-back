package controleurs.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constantes.reponse.ReponseConstantes;
import donnees.dto.login.IdentifiantDto;
import donnees.dto.utilisateur.UtilisateurSansMdpDto;
import services.login.ILoginService;
import services.reponse.IGestionnaireReponseService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login",  urlPatterns ="/login")
public class LoginControleur extends HttpServlet {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @EJB
    private ILoginService loginService;

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    @Override
    protected void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        IdentifiantDto identifiant = gson.fromJson(requete.getReader(), IdentifiantDto.class);
        UtilisateurSansMdpDto utilisateurTrouve = loginService.demandeConnexion(identifiant.getLogin(), identifiant.getMotDePasse());
        if(utilisateurTrouve!=null){
            envoyerReponseRecuperationUtilisateurSansMdp(utilisateurTrouve, reponse);
        }
        else{
            envoyerReponseUtilisateurIntrouvable(reponse);
        }
    }

    private void envoyerReponseRecuperationUtilisateurSansMdp(UtilisateurSansMdpDto utilisateurRecupere, HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(utilisateurRecupere, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }

    private void envoyerReponseUtilisateurIntrouvable(HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_INTROUVABLE, ReponseConstantes.MESSAGE_REPONSE_INTROUVABLE);
    }
}