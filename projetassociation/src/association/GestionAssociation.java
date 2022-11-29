package association;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


/**
 * Gère la sauvegarde des données de l'association.
 */
public class GestionAssociation implements InterGestionAssociation {

  /**
   * Le gestionnaire d'evenements.
   */
  private static InterGestionEvenements gestionEvenements = null;
  private static InterGestionMembres gestionMembres = null;

  /**
   * Renvoie le gestionnaire d'événements de l'association. L'objet retourné est
   * unique. Au premier appel de la méthode, il est créé et aux appels suivants,
   * c'est la référence sur cet objet qui est retournée.
   *
   * @return le gestionnaire d'évènements de l'association
   */
  @Override
  public InterGestionEvenements gestionnaireEvenements() {
    if (gestionEvenements == null) {
      // L'initialiser a partir de la classe
      gestionEvenements = null;
    }
    return gestionEvenements;
  }

  /**
   * Renvoie le gestionnaire de membres de l'association. L'objet retourné est
   * unique. Au premier appel de la méthode, il est créé et aux appels suivants,
   * c'est la référence sur cet objet qui est retournée.
   *
   * @return le gestionnaire de membres de l'association
   */
  @Override
  public InterGestionMembres gestionnaireMembre() {
    if (gestionMembres == null) {
      // L'initialiser a partir de la classe
      gestionMembres = null;
    }
    return gestionMembres;
  }

  /**
   * Enregistre dans un fichier toutes les données de l'association,
   * c'est-à-dire l'ensemble des membres et des événéments.
   *
   * @param nomFichier le fichier dans lequel enregistrer les données
   * @throws IOException en cas de problème d'écriture dans le fichier
   */
  @Override
  public void sauvegarderDonnees(String nomFichier) throws IOException {
    OutputStream output = new BufferedOutputStream(new FileOutputStream(nomFichier));
    ObjectOutputStream outObjStream = new ObjectOutputStream(output);
    outObjStream.writeObject(gestionMembres);
    outObjStream.writeObject(gestionEvenements);
    outObjStream.close();
    output.close();
  }

  /**
   * Charge à partir d'un fichier toutes les données de l'association,
   * c'est-à-dire un ensemble de membres et d'évènements. Si des membres et des
   * évènements avaient déjà été définis, ils sont écrasés par le contenu trouvé
   * dans le fichier.
   *
   * @param nomFichier le fichier à partir duquel charger les données
   * @throws IOException en cas de problème de lecture dans le fichier
   */
  @Override
  public void chargerDonnees(String nomFichier) throws IOException {
    InputStream input = new BufferedInputStream(new FileInputStream(nomFichier));
    ObjectInputStream inObjStream = new ObjectInputStream(input);
    try {
      gestionMembres = (InterGestionMembres) inObjStream.readObject();
    } catch (ClassNotFoundException e) {
      gestionMembres = null;
    }
    try {
      gestionEvenements = (InterGestionEvenements) inObjStream.readObject();
    } catch (ClassNotFoundException e) {
      gestionEvenements = null;
    }

    input.close();
    inObjStream.close();
  }
}
