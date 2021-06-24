package donnees.dto.soustournoi.match;

public class CourtDto {

    private int idCourt;

    private String nomCourt;

    private String zoneCourt;

    private String typeCourt;

    public int getIdCourt() {
        return idCourt;
    }

    public void setIdCourt(int idCourt) {
        this.idCourt = idCourt;
    }

    public String getNomCourt() {
        return nomCourt;
    }

    public void setNomCourt(String nomCourt) {
        this.nomCourt = nomCourt;
    }

    public String getZoneCourt() {
        return zoneCourt;
    }

    public void setZoneCourt(String zoneCourt) {
        this.zoneCourt = zoneCourt;
    }

    public String getTypeCourt() {
        return typeCourt;
    }

    public void setTypeCourt(String typeCourt) {
        this.typeCourt = typeCourt;
    }
}
