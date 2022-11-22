package association;

import java.util.Set;

/**
 * Gestion des membres de l'association.
 *
 * @author Eric Cariou
 */
public interface InterGestionMembres {
  /**
   * Ajoute un membre dans l'association. Ne fait rien si le membre était déjà
   * présent dans l'association.
   *
   * @param membre le membre à rajouter
   * @return <code>true</code> si le membre a bien été ajouté,
   *         <code>false</code> si le membre était déjà présent (dans ce cas il
   *         n'est pas ajouté à nouveau)
   */
  boolean ajouterMembre(InterMembre membre);

  /**
   * Supprime un membre de l'association.
   *
   * @param membre le membre à supprimer
   * @return <code>true</code> si le membre était présent et a été supprimé,
   *         <code>false</code> si le membre n'était pas dans l'association
   */
  boolean supprimerMembre(InterMembre membre);

  /**
   * Désigne le président de l'association. Il doit être un des membres de
   * l'association.
   *
   * @param membre le membre à désigner comme président.
   * @return <code>false</code> si le membre n'�tait pas dans l'association (le
   *         pr�sident n'est alors pas positionn�), <code>true</code> si le
   *         membre a �t� nomm� pr�sident
   */
  boolean designerPresident(InterMembre membre);

  /**
   * Renvoie l'ensemble des membres de l'association.
   *
   * @return l'ensemble des membres de l'association.
   */
  Set<InterMembre> ensembleMembres();

  /**
   * Renvoie le pr�sident de l'association.
   *
   * @return le membre pr�sident de l'association s'il avait �t� d�sign� sinon
   *         retourne <code>null</code>
   */
  InterMembre president();
}
