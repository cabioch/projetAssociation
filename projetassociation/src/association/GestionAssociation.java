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
import java.io.Serializable;
import java.util.Objects;


/**
 * Gère la sauvegarde des données de l'association.
 */
public class GestionAssociation
    implements InterGestionAssociation, Serializable {
  
  private static final long serialVersionUID = 392L;
  /**
   * Le gestionnaire d'evenements.
   */
  private GestionEvenements gestionEvenements = null;
  private GestionMembres gestionMembres = null;
  
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
      gestionEvenements = new GestionEvenements();
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
      gestionMembres = new GestionMembres();
    }
    return gestionMembres;
  }
  
  /**
   * Enregistre dans un fichier toutes les données de l'association,
   * c'est-à-dire l'ensemble des membres et des événéments.
   *
   * @param nomFichier le fichier dans lequel enregistrer les données.
   * @throws IOException en cas de problème d'écriture dans le fichier.
   */
  @Override
  public void sauvegarderDonnees(String nomFichier) throws IOException {
    OutputStream output =
        new BufferedOutputStream(new FileOutputStream(nomFichier));
    ObjectOutputStream outObjStream = new ObjectOutputStream(output);
    outObjStream.writeObject(this);
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
    InputStream input =
        new BufferedInputStream(new FileInputStream(nomFichier));
    ObjectInputStream inObjStream = new ObjectInputStream(input);
    try {
      GestionAssociation tmp = (GestionAssociation) inObjStream.readObject();
      gestionEvenements = tmp.gestionEvenements;
      gestionMembres = tmp.gestionMembres;
    } catch (ClassNotFoundException e) {
      gestionMembres = null;
      gestionEvenements = null;
    }
    input.close();
    inObjStream.close();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    
    GestionAssociation that = (GestionAssociation) o;
    
    if (!Objects.equals(gestionEvenements, that.gestionEvenements)) {
      return false;
    }
    return Objects.equals(gestionMembres, that.gestionMembres);
  }
  
  @Override
  public int hashCode() {
    int result = gestionEvenements != null ? gestionEvenements.hashCode() : 0;
    result =
        31 * result + (gestionMembres != null ? gestionMembres.hashCode() : 0);
    return result;
  }
}
