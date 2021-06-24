package donnees.dto.reponse;

/**
 * Dto de reponse avec body utilise dans le service GestionnaireReponseService.
 *
 * @param <T> Type de la donnee renvoyee dans la reponse.
 */
public class ReponseDto<T> {

    /**
     * Message associe avec la reponse.
     */
    private String message;

    /**
     * Donnee(s) recuperee(s) qui sera(ont) renvoye(es)
     */
    private T contenu;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContenu() {
        return contenu;
    }

    public void setContenu(T contenu) {
        this.contenu = contenu;
    }

}
