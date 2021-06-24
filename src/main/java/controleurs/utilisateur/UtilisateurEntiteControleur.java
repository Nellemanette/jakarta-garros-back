package controleurs.utilisateur;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constantes.reponse.ReponseConstantes;
import constantes.utilisateur.UtilisateurConstantes;
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


@WebServlet(name = "UtilisateurEntite", urlPatterns = "/utilisateurs/*")
public class UtilisateurEntiteControleur extends HttpServlet {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @EJB
    private IUtilisateurService utilisateurService;

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    @Override
    protected void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        Integer idUtilisateur = tentativeRecuperationIdUtilisateur(requete);
        if (idUtilisateur == null){
            envoyerReponseMauvaisId(reponse);
        }
        else{
            UtilisateurSansMdpDto utilisateurRecupere = utilisateurService.consulterUtilisateurSeul(idUtilisateur);
            if (utilisateurRecupere == null)
                envoyerReponseUtilisateurIntrouvable(reponse);
            else
                envoyerReponseRecuperationUtilisateurSeul(utilisateurRecupere, reponse);
        }
    }

    @Override
    protected void doPut(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        Integer idUtilisateur = tentativeRecuperationIdUtilisateur(requete);
        if (idUtilisateur == null){
            envoyerReponseMauvaisId(reponse);
        }
        else{
            UtilisateurAvecMdpDto donneesDeModification = gson.fromJson(requete.getReader(), UtilisateurAvecMdpDto.class);
            gererReponseModificationUtilisateur(idUtilisateur, donneesDeModification, reponse);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        Integer idUtilisateur = tentativeRecuperationIdUtilisateur(requete);
        if (idUtilisateur == null){
            envoyerReponseMauvaisId(reponse);
        }
        else{
            UtilisateurSansMdpDto utilisateurTrouve = utilisateurService.consulterUtilisateurSeul(idUtilisateur);
            if (peutEtreSupprime(utilisateurTrouve)) {
                utilisateurService.supprimerUtilisateur(utilisateurTrouve);
                gestionnaireReponseService.envoiReponseSansDonnees(reponse);
            }
            else{
                envoyerReponseSuppressionImpossibleAdmin(reponse);
            }
        }
    }

    private Integer tentativeRecuperationIdUtilisateur(HttpServletRequest requete) throws IOException {
        try{
            return Integer.parseInt(requete.getPathInfo().replace("/", ""));
        }
        catch(NumberFormatException e){
            return null;
        }
    }

    private void envoyerReponseMauvaisId(HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_MAUVAISE_REQUETE, ReponseConstantes.MESSAGE_REPONSE_MAUVAIS_ID);
    }

    private void envoyerReponseUtilisateurIntrouvable(HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_INTROUVABLE, ReponseConstantes.MESSAGE_REPONSE_INTROUVABLE);
    }

    private void envoyerReponseRecuperationUtilisateurSeul(UtilisateurSansMdpDto utilisateurRecupere, HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(utilisateurRecupere, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }

    private void gererReponseModificationUtilisateur(int idUtilisateur, UtilisateurAvecMdpDto donneesDeModification, HttpServletResponse reponse) throws IOException {
        if (idUtilisateur != donneesDeModification.getIdUtilisateur()) {
            envoyerReponseConflitIdBodyEtRequete(reponse);
        }
        else {
            UtilisateurSansMdpDto utilisateurModifie = utilisateurService.modifierUtilisateur(donneesDeModification);
            envoyerReponseApresModificationUtilisateur(utilisateurModifie, reponse);
        }
    }

    private void envoyerReponseConflitIdBodyEtRequete(HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_MAUVAISE_REQUETE,
            ReponseConstantes.MESSAGE_REPONSE_CONFLIT_ID_BODY_ET_REQUETE);
    }

    private void envoyerReponseApresModificationUtilisateur(UtilisateurSansMdpDto utilisateurModifie, HttpServletResponse reponse) throws IOException {
        if (utilisateurModifie == null) {
            envoyerReponseUtilisateurIntrouvable(reponse);
        }
        else if (utilisateurModifie.getLogin() == null) {
            envoyerReponseLoginDejaPris(reponse);
        }
        else {
            envoyerReponseRecuperationUtilisateurSeul(utilisateurModifie, reponse);
        }
    }

    private void envoyerReponseLoginDejaPris(HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_MAUVAISE_REQUETE,
            ReponseConstantes.MESSAGE_REPONSE_LOGIN_DEJA_PRIS);
    }

    private boolean peutEtreSupprime(UtilisateurSansMdpDto utilisateurTrouve){
        return utilisateurTrouve != null && !utilisateurTrouve.getRole().equals(UtilisateurConstantes.ADMIN);
    }

    private void envoyerReponseSuppressionImpossibleAdmin(HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_MAUVAISE_REQUETE,
                ReponseConstantes.MESSAGE_REPONSE_SUPPRESSION_ADMIN_IMPOSSIBLE);
    }

}

