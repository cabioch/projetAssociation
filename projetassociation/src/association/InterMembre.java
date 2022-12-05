package association;

import java.util.List;

/**
 * Définit les actions pour un membre d'une association qui peut participer à
 * des évènements organisés par l'association.
 *
 * @author Eric Cariou
 */
public interface InterMembre {
  /**
   * La liste des évènements auquel le membre est inscrit ou a participé.
   *
   * @return la liste des évènements du membre
   */
  List<Evenement> ensembleEvenements();

  /**
   * La liste des évènements auquel le membre est inscrit et qui n'ont pas
   * encore eu lieu.
   *
   * @return la liste des évènements à venir du membre
   */
  List<Evenement> ensembleEvenementsAvenir();

  /**
   * D�finit les informations personnelles du membre.
   *
   * @param info les informations personnelles du membre
   */
  void definirInformationPersonnnelle(InformationPersonnelle info);

  /**
   * Renvoie les informations personnelles du membre.
   *
   * @return l'objet contenant les informations personnelles du membre ou
   *         <code>null</code> si elles n'ont pas été définies
   */
  InformationPersonnelle getInformationPersonnelle();
}
