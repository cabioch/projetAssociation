package association;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe d'implémentation de l'interface de Gestion des
 * Membres.{@link InterGestionMembres}
 *
 * @author Nicolas RENARD
 * @version 1.0.0
 */
public class GestionMembres implements InterGestionMembres {
  
  /**
   * Ensemble des membres de l'association.
   */
  
  private Set<InterMembre> membres = null;
  
  /**
   * Le rôle du président de l'association.
   */
  
  private InterMembre president = null;
  
  /**
   * Ajoute un membre dans l'association. Ne fait rien si le membre était déja
   * présent dans l'association (meme nom et meme prenom) ou que l'une des
   * informations personnelles est une chaine vide ou <code>null</code>.
   *
   * @param membre le membre à rajouter
   * @return <code>true</code> si le membre a bien été ajouté,
   *         <code>false</code> dans le cas contraire.
   */
  
  @Override
  public boolean ajouterMembre(InterMembre membre) {
    
    for (InterMembre m : membres) {
      // verifie que le membre ajouté n'est pas égale a un membre déja existant
      // par verification du nom et prénom.
      if (m.getInformationPersonnelle().getNom()
          .equals(membre.getInformationPersonnelle().getNom())
          && m.getInformationPersonnelle().getPrenom()
              .equals(membre.getInformationPersonnelle().getPrenom())) {
        return false;
      }
    }
    // verifie que le membre ajouté n'a pas un attribut null en prénom ou nom.
    if (membre.getInformationPersonnelle().getNom() == null
        || membre.getInformationPersonnelle().getPrenom() == null) {
      return false;
    }
    // verifie que le membre ajouté n'a pas une chaine vide ou de caractères
    // vide en nom ou prénom.
    if (membre.getInformationPersonnelle().getNom().isBlank()
        || membre.getInformationPersonnelle().getPrenom().isBlank()) {
      return false;
    }
    membres.add(membre);
    return true;
  }
  
  /**
   * Supprime un membre de l'association.
   *
   * @param membre le membre à supprimer
   * @return <code>true</code> si le membre était présent et a été supprimé,
   *         <code>false</code> si le membre n'était pas dans l'association.
   */
  @Override
  public boolean supprimerMembre(InterMembre membre) {
    if (membres.contains(membre)) {
      membres.remove(membre);
      return true;
    }
    return false;
  }
  
  /**
   * Désigne le président de l'association. Il doit être un des membres de
   * l'association. Un président déjà nommé est remplacé.
   *
   * @param membre le membre à désigner comme président.
   * @return <code>false</code> si le membre n'était pas dans l'association (le
   *         président n'est alors pas positionné), <code>true</code> si le
   *         membre a été nommé président ou remplacé.
   */
  @Override
  public boolean designerPresident(InterMembre membre) {
    if (!membres.contains(membre)) {
      return false;
    }
    president = membre;
    return true;
  }
  
  /**
   * Renvoie l'ensemble des membres de l'association. Si l'ensemble est null, un
   * ensemble vide est créé.
   *
   * @return l'ensemble des membres de l'association.
   */
  @Override
  public Set<InterMembre> ensembleMembres() {
    if (membres == null) {
      membres = new HashSet<>();
    }
    return membres;
  }
  
  /**
   * Renvoie le président de l'association.
   *
   * @return le membre président de l'association s'il avait été désigné sinon
   *         retourne <code>null</code>.
   */
  @Override
  public InterMembre president() {
    return president;
  }
}
