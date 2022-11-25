
package association;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Nicolas RENARD
 * 
 * @version 1.0.0 Classe d'implémentation de l'interface de Gestion des Membres
 *          {@link InterGestionMembres}
 *
 */

public class GestionMembres implements InterGestionMembres {
  
  /**
   * Ensemble des membres présents
   */
  private static final Set<InterMembre> MEMBRES = new HashSet<>();
  /**
   * Le président de l'association
   */
  private static final Set<InterMembre> PRESIDENT = new HashSet<>();
  
  /**
   * Ajoute un membre dans l'association. Ne fait rien si le membre était déja
   * présent dans l'association.
   *
   * @param membre le membre à rajouter
   * @return <code>true</code> si le membre a bien été ajouté,
   *         <code>false</code> si le membre était déjà présent (dans ce cas il
   *         n'est pas ajouté à nouveau)
   */
  
  @Override
  public boolean ajouterMembre(InterMembre membre) {
    // verifie dans le set si les membres sont présent.
    // present => ne fait rien
    // ajoute sinon
    if (!MEMBRES.contains(membre)) {
      MEMBRES.add(membre);
      return true;
    }
    return false;
  }
  
  /**
   * Supprime un membre de l'association.
   *
   * @param membre le membre à supprimer
   * @return <code>true</code> si le membre àtait présent et a été supprimé,
   *         <code>false</code> si le membre n'était pas dans l'association
   */
  @Override
  public boolean supprimerMembre(InterMembre membre) {
    if (MEMBRES.contains(membre)) {
      MEMBRES.remove(membre);
      return true;
    }
    return false;
  }
  
  /**
   * Désigne le président de l'association. Il doit être un des membres de
   * l'association.
   *
   * @param membre le membre à désigner comme président.
   * @return <code>false</code> si le membre n'était pas dans l'association (le
   *         président n'est alors pas positionné), <code>true</code> si le
   *         membre a été nommé président
   */
  @Override
  public boolean designerPresident(InterMembre membre) {
    if (MEMBRES.contains(membre) && PRESIDENT.isEmpty()) {
      PRESIDENT.add(membre);
      return true;
    }
    return false;
  }
  
  /**
   * Renvoie l'ensemble des membres de l'association.
   *
   * @return l'ensemble des membres de l'association.
   */
  @Override
  public Set<InterMembre> ensembleMembres() {
    for (InterMembre m : MEMBRES) {
      
      return m;
    }
    return null;
  }
  
  /**
   * Renvoie le président de l'association.
   *
   * @return le membre président de l'association s'il avait été désigné sinon
   *         retourne <code>null</code>
   */
  @Override
  public InterMembre president() {
    if (!PRESIDENT.isEmpty()) {
      
      return PRESIDENT;
    }
    return null;
  }
  
}
