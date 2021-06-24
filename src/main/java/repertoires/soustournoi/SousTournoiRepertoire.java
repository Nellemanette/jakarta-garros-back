package repertoires.soustournoi;

import donnees.entites.soustournoi.SousTournoi;
import donnees.entites.soustournoi.match.Arbitre;
import donnees.entites.soustournoi.match.Equipe;
import donnees.entites.soustournoi.match.Joueur;
import donnees.entites.soustournoi.match.Match;
import repertoires.ParentRepertoire;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SousTournoiRepertoire extends ParentRepertoire implements ISousTournoiRepertoire{

    @Override
    public SousTournoi consulterSousTournoiSeul(int idSousTournoi) {
        return gestionnaireDesEntites.find(SousTournoi.class, idSousTournoi);
    }

    @Override
    public Match consulterMatchSeul(int idMatchAConsulter) {
        return gestionnaireDesEntites.find(Match.class, idMatchAConsulter);
    }

    @Override
    public Match creerMatch(Match nouveauMatch) {
        int idDernierMatchExistant = creerMatchEtRecupererIdDeCreation(nouveauMatch);
        nouveauMatch.setIdMatch(idDernierMatchExistant);
        return nouveauMatch;
    }

    @Override
    public void supprimerMatch(int idMatchASupprimer) {
        Match matchASupprimer = consulterMatchSeul(idMatchASupprimer);
        if(matchASupprimer != null)
            gestionnaireDesEntites.remove(matchASupprimer);
    }

    @Override
    public Arbitre consulterArbitreSeul(int idArbitre) {
        return gestionnaireDesEntites.find(Arbitre.class, idArbitre);
    }

    @Override
    public Match modifierMatch(Match donneesModificationMatch) {
        Match matchModifie = gestionnaireDesEntites.find(Match.class, donneesModificationMatch.getIdMatch());
        if(matchModifie == null)
            return null;
        modifierMatchDepuisDonnees(donneesModificationMatch, matchModifie);
        gestionnaireDesEntites.persist(matchModifie);
        return matchModifie;
    }

    @Override
    public List<SousTournoi> consulterTousLesSousTournois() {
        String requeteConsultation = "select s from SousTournoi s ";
        return gestionnaireDesEntites
            .createQuery(requeteConsultation, SousTournoi.class)
            .getResultList();
    }

    @Override
    public Joueur consulterJoueurSeul(int idJoueurAConsulter) {
        return gestionnaireDesEntites.find(Joueur.class, idJoueurAConsulter);
    }

    @Override
    public Equipe consulterEquipeSeule(int idEquipe) {
        return gestionnaireDesEntites.find(Equipe.class, idEquipe);
    }

    @Override
    public Equipe creerEquipe(Equipe nouvelleEquipe) {
        List<Equipe> equipes = consulterToutesLesEquipes();
        Equipe equipeAvecMemesMembres = recupererEquipeAvecMemesMembresDejaExistante(nouvelleEquipe, equipes);
        if(equipeAvecMemesMembres != null)
            return equipeAvecMemesMembres;
        return creerNouvelleEquipeEtLuiAssocierUnId(nouvelleEquipe);
    }

    @Override
    public SousTournoi validerSousTournoi(int idSousTournoiAValider) {
        SousTournoi sousTournoiAValider = gestionnaireDesEntites.find(SousTournoi.class, idSousTournoiAValider);
        sousTournoiAValider.setValiditeSousTournoi(true);
        gestionnaireDesEntites.persist(sousTournoiAValider);
        return sousTournoiAValider;
    }

    @Override
    public void mettreAJourDonneesTemporellesSousTournoi(SousTournoi sousTournoiMisAJour) {
        gestionnaireDesEntites.persist(sousTournoiMisAJour);
    }

    @Override
    public void validerMatch(int idMatchAValider) {
        Match matchAValider = gestionnaireDesEntites.find(Match.class, idMatchAValider);
        matchAValider.setEstMatchValide(true);
        gestionnaireDesEntites.persist(matchAValider);
    }

    private Equipe creerNouvelleEquipeEtLuiAssocierUnId(Equipe nouvelleEquipe) {
        gestionnaireDesEntites.merge(nouvelleEquipe);
        donnerIdALaDerniereEquipeCreee(nouvelleEquipe);
        return nouvelleEquipe;
    }

    private void donnerIdALaDerniereEquipeCreee(Equipe nouvelleEquipe) {
        List<Equipe> equipesRecuperees = consulterToutesLesEquipes();
        nouvelleEquipe.setIdEquipe(equipesRecuperees.get(equipesRecuperees.size() - 1).getIdEquipe());
    }

    private Equipe recupererEquipeAvecMemesMembresDejaExistante(Equipe nouvelleEquipe, List<Equipe> equipes) {
        return equipes
            .stream()
            .filter(equipeExistante -> possedentLesMemesJoueurs(nouvelleEquipe, equipeExistante))
            .findFirst()
            .orElse(null);
    }

    private List<Equipe> consulterToutesLesEquipes(){
        String requeteConsultation = "select e from Equipe e";
        return gestionnaireDesEntites.createQuery(requeteConsultation, Equipe.class)
            .getResultList();
    }

    private void modifierMatchDepuisDonnees(Match donneesModificationMatch, Match matchModifie) {
        matchModifie.setEquipes(donneesModificationMatch.getEquipes());
        matchModifie.setDuree(donneesModificationMatch.getDuree());
        matchModifie.setArbitre(donneesModificationMatch.getArbitre());
        matchModifie.setEstMatchValide(donneesModificationMatch.isEstMatchValide());
        matchModifie.setDateDebut(donneesModificationMatch.getDateDebut());
        matchModifie.setScore1(donneesModificationMatch.getScore1());
        matchModifie.setScore2(donneesModificationMatch.getScore2());
        matchModifie.setCourt(donneesModificationMatch.getCourt());
    }

    private int creerMatchEtRecupererIdDeCreation(Match nouveauMatch) {
        gestionnaireDesEntites.merge(nouveauMatch);
        return recupererIdDuDernierMatchCree();
    }

    private int recupererIdDuDernierMatchCree() {
        String requeteConsultation = "select m from Match m";
        List<Match> matchsExistants = gestionnaireDesEntites
            .createQuery(requeteConsultation, Match.class)
            .getResultList();
        return matchsExistants.get(matchsExistants.size() - 1).getIdMatch();
    }

    private boolean possedentLesMemesJoueurs(Equipe nouvelleEquipe, Equipe equipeExistante){
        if(nouvelleEquipe.getJoueurs().size() != equipeExistante.getJoueurs().size())
            return false;
        boolean ontLesMemesJoueurs = false;
        ontLesMemesJoueurs = determinerSiEquipesOntLesMemesJoueurs(nouvelleEquipe, equipeExistante, ontLesMemesJoueurs);
        return ontLesMemesJoueurs;
    }

    private boolean determinerSiEquipesOntLesMemesJoueurs(Equipe nouvelleEquipe, Equipe equipeExistante, boolean ontLesMemesJoueurs) {
        List<Joueur> joueursNouvelleEquipe = nouvelleEquipe.getJoueurs();
        List<Joueur> joueursEquipeExistante = equipeExistante.getJoueurs();
        for (Joueur joueurNouvelleEquipe : joueursNouvelleEquipe) {
            for (Joueur joueurEquipeExistante : joueursEquipeExistante) {
                ontLesMemesJoueurs = estLeMemeJoueur(joueurNouvelleEquipe, joueurEquipeExistante);
            }
        }
        return ontLesMemesJoueurs;
    }

    private boolean estLeMemeJoueur(Joueur joueurNouvelleEquipe, Joueur joueurEquipeExistante){
        return joueurNouvelleEquipe.getIdJoueur() == joueurEquipeExistante.getIdJoueur();
    }

}
