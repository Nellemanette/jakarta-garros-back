package donnees.dto.login;

public class IdentifiantDto {

    private int idUtilisateur;

    private String login;

    private String role;

    private String motDePasse;

    public int getIdUtilisateur() { return idUtilisateur; }

    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String password) {
        this.motDePasse = password;
    }

}
