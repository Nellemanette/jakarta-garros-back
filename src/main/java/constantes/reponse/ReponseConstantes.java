package constantes.reponse;

/**
 * Constantes relatives aux reponses des controleurs.
 */
public class ReponseConstantes {

    /**
     * Codes de statut HTTP utilises.
     */
    public static final int CODE_STATUT_HTTP_SUCCES = 200;
    public static final int CODE_STATUT_HTTP_INTROUVABLE = 404;
    public static final int CODE_STATUT_HTTP_MAUVAISE_REQUETE = 400;

    /**
     * Messages transmis avec la reponse du controleur.
     */
    public static final String MESSAGE_REPONSE_SUCCES = "Ok";
    public static final String MESSAGE_REPONSE_MAUVAIS_ID = "Identifiant incorrect";
    public static final String MESSAGE_REPONSE_CONFLIT_ID_BODY_ET_REQUETE = "Identifiants du corps de la requête et de l'URL differents";
    public static final String MESSAGE_REPONSE_INTROUVABLE = "Entite introuvable";

    /**
     * Constantes des reponses relatives aux utilisateurs.
     */
    public static final String MESSAGE_REPONSE_LOGIN_DEJA_PRIS = "Ce login est deja utilise";
    public static final String MESSAGE_REPONSE_UTILISATEUR_DEJA_EXISTANT = "Cet utilisateur existe deja";
    public static final String MESSAGE_REPONSE_SUPPRESSION_ADMIN_IMPOSSIBLE = "Impossible de supprimer un administrateur";

    /**
     * Constantes des reponses relatives aux résultats des matchs.
     */
    public static final String MESSAGE_REPONSE_RESULTATS_INTROUVABLES = "Les resultats n'ont pas pu être trouvés.";
}
