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

@WebServlet(name = "MatchEntite", urlPatterns = "/matchs/*")
public class MatchEntiteControleur extends HttpServlet {

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    @EJB
    private ISousTournoiService sousTournoiService;

    @Override
    protected void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        Integer idMatchAConsulter = tentativeRecuperationIdMatch(requete);
        if (idMatchAConsulter == null){
            envoyerReponseMauvaisId(reponse);
        }
        else{
            MatchDto matchTrouve = sousTournoiService.consulterMatchSeul(idMatchAConsulter);
            if(matchTrouve != null){
                envoyerReponseRecuperationMatchSeul(matchTrouve, reponse);
            }
            else{
                envoyerReponseMatchIntrouvable(reponse);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        Integer idMatchAConsulter = tentativeRecuperationIdMatch(requete);
        if (idMatchAConsulter == null){
            envoyerReponseMauvaisId(reponse);
        }
        else{
            MatchDto donneesModificationMatch = gson.fromJson(requete.getReader(), MatchDto.class);
            MatchDto matchModifie = sousTournoiService.modifierMatch(donneesModificationMatch);
            if(matchModifie != null){
                envoyerReponseRecuperationMatchSeul(matchModifie, reponse);
            }
            else{
                envoyerReponseMatchIntrouvable(reponse);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        Integer idMatchASupprimer = tentativeRecuperationIdMatch(requete);
        if (idMatchASupprimer == null){
            envoyerReponseMauvaisId(reponse);
        }
        else{
            sousTournoiService.supprimerMatch(idMatchASupprimer);
            gestionnaireReponseService.envoiReponseSansDonnees(reponse);
        }
    }

    private Integer tentativeRecuperationIdMatch(HttpServletRequest requete) throws IOException {
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

    private void envoyerReponseRecuperationMatchSeul(MatchDto matchRecupere, HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(matchRecupere, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }

    private void envoyerReponseMatchIntrouvable(HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
            ReponseConstantes.CODE_STATUT_HTTP_INTROUVABLE, ReponseConstantes.MESSAGE_REPONSE_INTROUVABLE);
    }
}
