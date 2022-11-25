package association;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestion des événements.
 * 
 * @author romain
 *
 */
public class GestionEvenements implements InterGestionEvenements {

  /**
   * La liste des événements.
   *
   */
  List<Evenement> listeEvenements = new ArrayList<Evenement>();
  
  
  /**
   * Getters/Setters de la liste des événements.
   * @return listeEvenements La liste des événements
   */
  public List<Evenement> getListeEvenements() {
    return listeEvenements;
  }


  public void setListeEvenements(List<Evenement> listeEvenements) {
    this.listeEvenements = listeEvenements;
  }
  
  /**
   * Crée un nouvel événement. Plusieurs vérifications sont effectuées : que les
   * dates et heures sont cohérentes et qu'il n'y a pas un chevauchement sur la
   * même période avec un autre événement dans le même lieu.
   * 
   * @param nom le nom de l'événement
   * @param lieu le lieu
   * @param jour le jour dans le mois (nombre de 0 à 31)
   * @param mois le mois dans l'année
   * @param annee l'année
   * @param heure l'heure de la journée (nombre entre 0 et 23)
   * @param minutes les minutes de l'heure (nombre entre 0 et 59)
   * @param duree la durée (en minutes)
   * @param nbParticipants le nombre maximum de participants (0 signifie un
   *        nombre quelconque)
   * 
   * @return Si l'événement vérifie toutes les conditions, on retourne
   *         l'événement sinon on retourne null
   */
  @Override
  public Evenement creerEvenement(String nom, String lieu, int jour, Month mois,
      int annee, int heure, int minutes, int duree, int nbParticipants) {
    
    Evenement newEvent = new Evenement(nom, lieu, annee, mois, jour, heure,
        minutes, duree, nbParticipants);
    for (Evenement e : listeEvenements) {
      if (!e.pasDeChevauchementLieu(newEvent)) {
        return null;
      }
    }
    listeEvenements.add(newEvent);
    return newEvent;
  }
  
  
  /**
   * Supprime un événement. Les membres qui étaient inscrits sont
   * automatiquement désinscrits de l'événement supprimé. Si l'événement
   * n'existait pas, la méthode ne fait rien.
   *
   * @param evt l'événement à supprimer.
   */
  @Override
  public void supprimerEvenement(Evenement evt) {
    
    if (listeEvenements.remove(evt)) {
      for (InterMembre e : evt.getParticipants()) {
        // TODO Placeholder
        e.ensembleEvenements().remove(evt);
      }
    }
    
    
  }
  
  
  /**
   * Renvoie l'ensemble des événements de l'association.
   *
   * @return l'ensemble des événements
   */
  @Override
  public List<Evenement> ensembleEvenements() {
    
    return listeEvenements;
  }
  
  /**
   * Renvoie l'ensemble des événements à venir de l'association.
   *
   * @return l'ensemble des événements à venir
   */
  @Override
  public List<Evenement> ensembleEvenementAvenir() {
    List<Evenement> aVenir = new ArrayList<>();
    for (Evenement e : listeEvenements) {
      if (e.getDate().isAfter(LocalDateTime.now())) {
        aVenir.add(e);
      }
    }
    return aVenir;
  }
  
  /**
   * Un membre est incrit à un événement.
   *
   * @param evt l'événement auquel s'inscrire
   * @param mbr le membre qui s'inscrit
   * @return <code>true</code> s'il n'y a pas eu de problème, <code>false</code>
   *         si l'événement est en conflit de calendrier avec un événement
   *         auquel est déjà inscrit le membre ou si le nombre de participants
   *         maximum est déjà atteint
   */
  @Override
  public boolean inscriptionEvenement(Evenement evt, InterMembre mbr) {
    for (Evenement e : listeEvenements) {
      if (!e.pasDeChevauchementTemps(evt)) {
        return false;
      }
    }
    return evt.ajouterParticipant(mbr);
  }
  
  /**
   * Désincrit un membre d'un événement.
   *
   * @param evt l'événement auquel se désinscrire
   * @param mbr le membre qui se désinscrit
   * @return si le membre était bien inscrit à l'événement, renvoie
   *         <code>true</code> pour préciser que l'annulation est effective,
   *         sinon <code>false</code> si le membre n'était pas inscrit à
   *         l'événement
   */
  @Override
  public boolean annulerEvenement(Evenement evt, InterMembre mbr) {
    return evt.enleverParticipant(mbr);
  }


 
  
  
  
}
