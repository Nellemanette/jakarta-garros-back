package services.soustournoi;

import constantes.soustournoi.SousTournoiConstantes;
import convertisseurs.soustournoi.SousTournoiConvertisseur;
import convertisseurs.soustournoi.SousTournoiSimplifieConvertisseur;
import convertisseurs.soustournoi.match.MatchConvertisseur;
import donnees.dto.soustournoi.SousTournoiDto;
import donnees.dto.soustournoi.SousTournoiSimplifieDto;
import donnees.dto.soustournoi.match.CourtDto;
import donnees.dto.soustournoi.match.EquipeDto;
import donnees.dto.soustournoi.match.MatchDto;
import donnees.entites.soustournoi.match.*;
import donnees.entites.soustournoi.SousTournoi;
import repertoires.soustournoi.ISousTournoiRepertoire;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless
public class SousTournoiService implements ISousTournoiService{

    @EJB
    private ISousTournoiRepertoire sousTournoiRepertoire;

    @Override
    public SousTournoiDto consulterSousTournoiSeul(int idSousTournoi) {
        SousTournoi sousTournoiTrouve = sousTournoiRepertoire.consulterSousTournoiSeul(idSousTournoi);
        return SousTournoiConvertisseur.convertirVersSousTournoiDto(sousTournoiTrouve);
    }

    @Override
    public void supprimerMatch(int idMatchASupprimer) {
        Match matchASupprimer = sousTournoiRepertoire.consulterMatchSeul(idMatchASupprimer);
        if(matchASupprimer != null){
            sousTournoiRepertoire.supprimerMatch(idMatchASupprimer);
        }
    }

    @Override
    public MatchDto consulterMatchSeul(int idMatchAConsulter) {
        Match matchTrouve = sousTournoiRepertoire.consulterMatchSeul(idMatchAConsulter);
        return MatchConvertisseur.convertirVersMatchDto(matchTrouve);
    }

    @Override
    public MatchDto creerMatch(MatchDto donneesCreationMatch) {
        Match nouveauMatch = obtenirEntiteMatchDepuisDonnees(donneesCreationMatch, true);
        Match matchCree = sousTournoiRepertoire.creerMatch(nouveauMatch);
        return MatchConvertisseur.convertirVersMatchDto(matchCree);
    }

    @Override
    public MatchDto modifierMatch(MatchDto donneesModificationMatch) {
        Match matchAModifier = obtenirEntiteMatchDepuisDonnees(donneesModificationMatch, false);
        Match matchModifie = sousTournoiRepertoire.modifierMatch(matchAModifier);
        return MatchConvertisseur.convertirVersMatchDto(matchModifie);
    }

    @Override
    public List<SousTournoiSimplifieDto> consulterTousLesSousTournoisSimplifies() {
        List<SousTournoi> listeSousTournois = sousTournoiRepertoire.consulterTousLesSousTournois();
        listeSousTournois.forEach(this::mettreAJourDonneesTemporellesSousTournoi);
        return obtenirListeSousTournoiSimplifieDtoDepuisListeSousTournoi(listeSousTournois);
    }

    @Override
    public SousTournoiSimplifieDto validerSousTournoi(int idSousTournoiAValider) {
        SousTournoi sousTournoiValide = sousTournoiRepertoire.validerSousTournoi(idSousTournoiAValider);
        sousTournoiValide.getListeMatch().forEach(match -> sousTournoiRepertoire.validerMatch(match.getIdMatch()));
        return SousTournoiSimplifieConvertisseur.convertirVersSousTournoiSimplifieDto(sousTournoiValide);
    }

    private Match obtenirEntiteMatchDepuisDonnees(MatchDto donneesCreationMatch, boolean estContexteCreation) {
        Match nouveauMatch = new Match();
        nouveauMatch.setIdMatch(donneesCreationMatch.getIdMatch());
        nouveauMatch.setDateDebut(donneesCreationMatch.getDateDebut());
        nouveauMatch.setEstMatchValide(donneesCreationMatch.isEstMatchValide());
        nouveauMatch.setDuree(donneesCreationMatch.getDuree());
        nouveauMatch.setScore1(donneesCreationMatch.getScore1());
        nouveauMatch.setScore2(donneesCreationMatch.getScore2());
        SousTournoi sousTournoiDuMatch = sousTournoiRepertoire.consulterSousTournoiSeul(donneesCreationMatch.getIdSousTournoi());
        nouveauMatch.setSousTournoi(sousTournoiDuMatch);
        nouveauMatch.setCourt(recupererEntiteCourtDepuisDto(donneesCreationMatch.getCourt()));
        if(estContexteCreation)
            affecterRessourcesAMatch(donneesCreationMatch, nouveauMatch);
        else
            modifierRessourcesMatch(donneesCreationMatch, nouveauMatch);
        return nouveauMatch;
    }

    private void modifierRessourcesMatch(MatchDto donneesModificationMatch, Match matchModifie){
        matchModifie.setEquipes(recupererEquipesExistantesDepuisListeDto(donneesModificationMatch.getEquipes()));
        affecterArbitreAMatch(donneesModificationMatch, matchModifie);
    }

    private void affecterRessourcesAMatch(MatchDto donneesCreationMatch, Match nouveauMatch) {
        nouveauMatch.setEquipes(recupererEntitesEquipeDepuisListeDto(donneesCreationMatch.getEquipes()));
        affecterArbitreAMatch(donneesCreationMatch, nouveauMatch);
    }

    private void affecterArbitreAMatch(MatchDto donneesCreationMatch, Match nouveauMatch) {
        Arbitre arbitreNouveauMatch = sousTournoiRepertoire.consulterArbitreSeul(donneesCreationMatch.getArbitre().getIdArbitre());
        nouveauMatch.setArbitre(arbitreNouveauMatch);
    }

    private List<Equipe> recupererEquipesExistantesDepuisListeDto(List<EquipeDto> equipes){
        List<Equipe> equipesRecuperees = new ArrayList<>();
        equipes.forEach(equipe ->
            equipesRecuperees.add(sousTournoiRepertoire.consulterEquipeSeule(equipe.getIdEquipe())));
        return equipesRecuperees;
    }

    private List<Equipe> recupererEntitesEquipeDepuisListeDto(List<EquipeDto> equipes){
        List<Equipe> equipesRecuperees = new ArrayList<>();
        equipes.forEach(equipeDto -> ajouterEquipeRecupereeDansListe(equipesRecuperees, equipeDto));
        return equipesRecuperees;
    }

    private void ajouterEquipeRecupereeDansListe(List<Equipe> equipesRecuperees, EquipeDto equipeDto) {
        Equipe nouvelleEquipe = new Equipe();
        List<Joueur> joueursDeLaNouvelleEquipe = new ArrayList<>();
        recupererJoueursPourEquipeModifiee(equipeDto, joueursDeLaNouvelleEquipe);
        nouvelleEquipe.setJoueurs(joueursDeLaNouvelleEquipe);
        Equipe equipeCree = sousTournoiRepertoire.creerEquipe(nouvelleEquipe);
        equipesRecuperees.add(equipeCree);
    }

    private void recupererJoueursPourEquipeModifiee(EquipeDto equipeDto, List<Joueur> joueursDeLaNouvelleEquipe) {
        equipeDto.getJoueurs().forEach(joueurDto -> {
            Joueur joueurConverti = sousTournoiRepertoire.consulterJoueurSeul(joueurDto.getIdJoueur());
            joueursDeLaNouvelleEquipe.add(joueurConverti);
        });
    }

    private List<SousTournoiSimplifieDto> obtenirListeSousTournoiSimplifieDtoDepuisListeSousTournoi(List<SousTournoi> listeSousTournois){
        List<SousTournoiSimplifieDto> listeSousTournoiSimplifieDto = new ArrayList<>();
        listeSousTournois.forEach(sousTournoiAConvertir -> ajouterSousTournoiSimplifieALaListe(listeSousTournoiSimplifieDto, sousTournoiAConvertir));
        return listeSousTournoiSimplifieDto;
    }

    private void ajouterSousTournoiSimplifieALaListe(List<SousTournoiSimplifieDto> listeSousTournoiSimplifieDto, SousTournoi sousTournoiAConvertir) {
        listeSousTournoiSimplifieDto.add(SousTournoiSimplifieConvertisseur.convertirVersSousTournoiSimplifieDto(sousTournoiAConvertir));
    }

    private Court recupererEntiteCourtDepuisDto(CourtDto courtAConvertir){
        Court courtConverti = new Court();
        courtConverti.setIdCourt(courtAConvertir.getIdCourt());
        courtConverti.setNomCourt(courtAConvertir.getNomCourt());
        courtConverti.setTypeCourt(courtAConvertir.getTypeCourt());
        courtConverti.setZoneCourt(courtAConvertir.getZoneCourt());
        return courtConverti;
    }

    private void mettreAJourDonneesTemporellesSousTournoi(SousTournoi sousTournoiAMettreAJour){
        List<Match> matchs = sousTournoiAMettreAJour.getListeMatch();

        List<Timestamp> datesDebut = obtenirListeDesDatesConvertiesDesMatchs(matchs);

        if(!datesDebut.isEmpty()){
            donnerDateDebutASousTournoi(sousTournoiAMettreAJour, datesDebut);
            mettreAJourDateFinSousTournoi(sousTournoiAMettreAJour, matchs, datesDebut);
            sousTournoiRepertoire.mettreAJourDonneesTemporellesSousTournoi(sousTournoiAMettreAJour);
        }
    }

    private void mettreAJourDateFinSousTournoi(SousTournoi sousTournoiAMettreAJour, List<Match> matchs, List<Timestamp> datesDebut) {
        Timestamp dateDebutMax = Collections.max(datesDebut);
        List<Match> matchsAyantLaDateDeDebutMax = obtenirMatchsAyantDateDebutMax(matchs, dateDebutMax);
        List<Time> dureesMatchsAvecDateDebutMax = obtenirListeDesDureesConvertiesDesMatchs(matchsAyantLaDateDeDebutMax);
        if(!dureesMatchsAvecDateDebutMax.isEmpty()){
            donnerDateFinASousTournoi(sousTournoiAMettreAJour, dateDebutMax, dureesMatchsAvecDateDebutMax);
        }
    }

    private List<Match> obtenirMatchsAyantDateDebutMax(List<Match> matchs, Timestamp dateDebutMax) {
        List<Match> matchsAyantLaDateDeDebutMax = new ArrayList<>();
        matchs.forEach(match -> {
            if(obtenirDateDepuisChaine(match.getDateDebut()).equals(dateDebutMax))
                matchsAyantLaDateDeDebutMax.add(match);
        });
        return matchsAyantLaDateDeDebutMax;
    }

    private void donnerDateFinASousTournoi(SousTournoi sousTournoiAMettreAJour, Timestamp dateDebutMax, List<Time> dureesMatchsAvecDateDebutMax) {
        Time dureeMax = Collections.max(dureesMatchsAvecDateDebutMax);
        Timestamp dateFinMax = new Timestamp(dateDebutMax.getTime() + dureeMax.getTime());
        sousTournoiAMettreAJour.setDateFinSousTournoi(dateFinMax.toString().replace(".0", ""));
    }

    private void donnerDateDebutASousTournoi(SousTournoi sousTournoiAMettreAJour, List<Timestamp> datesDebut) {
        Timestamp dateDebutMin = Collections.min(datesDebut);
        sousTournoiAMettreAJour.setDateDebutSousTournoi(dateDebutMin.toString().replace(".0", ""));
    }

    private List<Timestamp> obtenirListeDesDatesConvertiesDesMatchs(List<Match> matchs) {
        List<Timestamp> datesDebut = new ArrayList<>();
        matchs.forEach(match -> {
            Timestamp dateConvertie = obtenirDateDepuisChaine(match.getDateDebut());
            if(dateConvertie != null)
                datesDebut.add(dateConvertie);
        });
        return datesDebut;
    }

    private Timestamp obtenirDateDepuisChaine(String dateAConvertir){
        SimpleDateFormat dateFormat = new SimpleDateFormat(SousTournoiConstantes.FORMAT_DATE);
        try {
            Date dateConvertie = dateFormat.parse(dateAConvertir);
            return new Timestamp(dateConvertie.getTime());
        }
        catch (ParseException e) {
            return null;
        }
    }

    private List<Time> obtenirListeDesDureesConvertiesDesMatchs(List<Match> matchs) {
        List<Time> durees = new ArrayList<>();
        matchs.forEach(match -> {
            if(match.getDuree() != null){
                Time dureeConvertie = obtenirDureeDepuisChaine(match.getDuree());
                durees.add(dureeConvertie);
            }
        });
        return durees;
    }

    private Time obtenirDureeDepuisChaine(String dureeAConvertir){
        SimpleDateFormat dateFormat = new SimpleDateFormat(SousTournoiConstantes.FORMAT_DUREE);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date dureeConvertie = dateFormat.parse(dureeAConvertir);
            return new Time(dureeConvertie.getTime());
        }
        catch (ParseException e) {
            return null;
        }
    }


}
