package donnees.entites.soustournoi.match;

import javax.persistence.*;


/**
 * Entite des courts.
 */
@Entity
@Table(name = "court")
public class Court {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idCourt ")
    private int idCourt ;

    @Column(name="nomCourt ")
    private String nomCourt ;

    @Column(name="zoneCourt")
    private String zoneCourt;

    @Column(name="typeCourt")
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
