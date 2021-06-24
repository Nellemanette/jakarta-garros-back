package controleurs.soustournoi;

import donnees.dto.soustournoi.SousTournoiSimplifieDto;
import services.reponse.IGestionnaireReponseService;
import constantes.reponse.ReponseConstantes;
import donnees.dto.soustournoi.SousTournoiDto;
import services.soustournoi.ISousTournoiService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controleur des sous-tournois.
 */
@WebServlet(name= "SousTournoiEntite", urlPatterns = "/soustournois/*")
public class SousTournoiEntiteControleur extends HttpServlet {

    @EJB
    private IGestionnaireReponseService gestionnaireReponseService;

    @EJB
    private ISousTournoiService sousTournoiService;

    /**
     * Permet de recuperer un sous-tournoi,
     * sinon de le valider.
     * @param requete Requete entrante.
     * @param reponse Reponse qui sera retournee au front.
     * @throws ServletException en cas d'erreur quelconque dans la servlet.
     * @throws IOException en cas d'erreur de creation de l'objet de sortie (PrintWriter).
     */
    @Override
    protected void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        Integer idSousTournoiRecherche = tentativeRecuperationIdSousTournoi(requete);
        if (idSousTournoiRecherche == null){
            envoyerReponseMauvaisId(reponse);
        }
        else{
            SousTournoiDto sousTournoiTrouve = sousTournoiService.consulterSousTournoiSeul(idSousTournoiRecherche);
            if(sousTournoiTrouve != null){
                envoyerReponseRecuperationSousTournoi(sousTournoiTrouve, reponse);
            }
            else{
                envoyerReponseSousTournoiIntrouvable(reponse);
            }
        }
    }

    /***
     * Validation d'un sous tournoi
     * @param requete
     * @param reponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
        Integer idSousTournoiAValider = tentativeRecuperationIdSousTournoi(requete);
        if (idSousTournoiAValider == null){
            envoyerReponseMauvaisId(reponse);
        }
        else{
            SousTournoiSimplifieDto sousTournoiValide = sousTournoiService.validerSousTournoi(idSousTournoiAValider);
            if(sousTournoiValide != null){
                envoyerReponseRecuperationSousTournoiSimplifie(sousTournoiValide, reponse);
            }
            else{
                envoyerReponseSousTournoiIntrouvable(reponse);
            }
        }
    }

    private Integer tentativeRecuperationIdSousTournoi(HttpServletRequest requete) throws IOException {
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

    private void envoyerReponseRecuperationSousTournoi(SousTournoiDto sousTournoiRecupere, HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(sousTournoiRecupere, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }

    private void envoyerReponseRecuperationSousTournoiSimplifie(SousTournoiSimplifieDto sousTournoiRecupere, HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(sousTournoiRecupere, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_SUCCES, ReponseConstantes.MESSAGE_REPONSE_SUCCES);
    }

    private void envoyerReponseSousTournoiIntrouvable(HttpServletResponse reponse) throws IOException {
        gestionnaireReponseService.envoiReponseAvecDonnees(null, reponse,
                ReponseConstantes.CODE_STATUT_HTTP_INTROUVABLE, ReponseConstantes.MESSAGE_REPONSE_INTROUVABLE);
    }
}
