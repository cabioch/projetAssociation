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
  List<Evenement> listeEvenements;
  
  
  /**
   * Getters/Setters de la liste des événements.
   *
   * @return listeEvenements La liste des événements.
   * 
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
    //Instanciation de l'événement avec les paramètres donnés 
    Evenement newEvent = new Evenement(nom, lieu, annee, mois, jour, heure,
        minutes, duree, nbParticipants);
    //On parcours la liste de tous les événements et on compare
    //l'événement avec les autres pour voir si il y a un chevauchement en 
    //lieu et en temps.
    for (Evenement e : listeEvenements) {
      if (!e.pasDeChevauchementLieu(newEvent)) {
        return null;
      }
    }
    //On ajoute cet événement dans la liste des événements
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
    
    //Avant de supprimer l'événement de l'ensemble des événements des participants
    for (InterMembre m : evt.getParticipants()) {
      m.ensembleEvenements().remove(evt);
    }
    //Puis on enlève l'événement de la liste des événements de l'association.
    listeEvenements.remove(evt);
  }
  
  
  /**
   * Renvoie l'ensemble des événements de l'association.
   *
   * @return l'ensemble des événements
   */
  @Override
  public List<Evenement> ensembleEvenements() {
    if (listeEvenements == null) {
      listeEvenements = new ArrayList<>();
    }
    
    return listeEvenements;
  }
  
  /**
   * Renvoie l'ensemble des événements à venir de l'association.
   *
   * @return l'ensemble des événements à venir
   */
  @Override
  public List<Evenement> ensembleEvenementAvenir() {
    //On créer une nouvelle liste qui comportera tous les événements
    //à venir.
    List<Evenement> avenir = new ArrayList<>();
    //On parcours les événements de l'association et si l'événement
    // a lieu après la date à l'instanté. Alors on l'ajoute à la liste
    // des événements à venir.
    for (Evenement e : listeEvenements) {
      if (e.getDate().isAfter(LocalDateTime.now())) {
        avenir.add(e);
      }
    }
    //On retourne cette liste des événements à venir.
    return avenir;
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
    //On regarde pour le membre si il est déjà inscrit à cet événement. Si oui,
    //On retourne false.
    if (mbr.ensembleEvenements().contains(evt)) {
      return false;
    }
    //Ensuite, on regarde l'ensemble des événements du membre 
    //et pour l'ensemble des événements, on regarde si il y a un chevauchement
    //en temps et en lieu avec l'événement auquel on veut qu'il s'inscrive.
    for (Evenement e : mbr.ensembleEvenements()) {
      if (!e.pasDeChevauchementTemps(evt)) {
        return false;
      }
    }
    //Si toutes l'événement passe toutes les vérifications, on l'ajoute à la 
    //liste des événements du membre.
    mbr.ensembleEvenements().add(evt); // A Changer ?
    //Puis on ajoute le membre aux participants de l'événement.
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
    //On regarde si on peut enlever le membre à l'événement
    if (evt.enleverParticipant(mbr)) {
      //Si on peut, on enlève cet événement de la liste des événements 
      //de l'association et on retourne True
      listeEvenements.remove(evt);
      return true;
    }
    //Sinon on retourne false.
    return false;
  }
  
  
  
}
