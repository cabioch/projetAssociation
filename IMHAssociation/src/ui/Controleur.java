package ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import association.Evenement;
import association.GestionAssociation;
import association.InformationPersonnelle;
import association.InterGestionAssociation;
import association.InterMembre;
import association.Membre;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Classe publique de contrôle de l'interface Utilisateur.
 *TODO Il n'y a pas encore la possibilité de mettre a jour des trucs je crois
 * (par ex changer l'age d'un membre...)
 */

public class Controleur implements Initializable {
  
  @FXML
  private InterGestionAssociation association;
  
  @FXML
  private TextField entreAdresseMembre;
  
  @FXML
  private TextField entreAgeMembre;
  
  @FXML
  private TextField entreeDateEvt;
  
  @FXML
  private TextField entreeDureeEvt;
  
  @FXML
  private TextField entreeHeureEvt;
  
  @FXML
  private TextField entreeLieuEvt;
  
  @FXML
  private TextField entreeMaxParticipantsEvt;
  
  @FXML
  private TextField entreeNomEvt;
  
  @FXML
  private TextField entreeNomMembre;
  
  @FXML
  private TextField entreePrenomMembre;
  
  @FXML
  private Label labelListeAfficheeEvt;
  
  @FXML
  private Label labelListeAfficheeMembre;
  
  @FXML
  private ListView<Evenement> listeEvenements;
  
  @FXML
  private ListView<InterMembre> listeMembres;
  
  @FXML
  private TextArea message;
  
  @FXML
  private Font x1;
  
  @FXML
  private Color x2;
  
  
  /**
   * Afficher le membre sélectionné : si un membre est sélectionné dans la
   * liste, affiche ses informations personnelles dans les 4 champs en haut de
   * la fenêtre.
   * 
   * @param event 
   */
  @FXML
  void actionBoutonAfficherMembreSelectionneMembre(ActionEvent event) {
    InterMembre m = listeMembres.getSelectionModel().getSelectedItem();
    InformationPersonnelle info = m.getInformationPersonnelle();
    // TODO Vérifier qu'il est toujours dans l'association
    entreeNomMembre.setText(info.getNom());
    entreePrenomMembre.setText(info.getPrenom());
    entreAgeMembre.setText(Integer.toString(info.getAge()));
    entreAdresseMembre.setText(info.getAdresse());
  }
  
  /**
   * Afficher les participants : affiche dans la liste de gauche, les
   * participants inscrits à l’événement dont les informations sont affichées.
   * 
   */
  @FXML
  void actionBoutonAfficherParticipantsEvt(ActionEvent event) {
    listeEvenements.getItems().clear();
    Evenement e = getEvenementFromFields();
    for (InterMembre m : e.getParticipants()) {
      listeMembres.getItems().add(m);
    }
  }
  
  /**
   * Afficher tous les membres : affiche dans la liste tous les membres de
   * l’association.
   */
  @FXML
  void actionBoutonAfficherTousMembresMembre() {
    listeMembres.getItems().clear();
    
    for (InterMembre m : association.gestionnaireMembre().ensembleMembres()) {
      listeMembres.getItems().add(m);
    }
  }
  
  /**
   * Affiche l’événement sélectionné : si un événement est sélectionné dans la
   * liste, affiche ses informations dans les champs en haut de la fenêtre.
   * 
   * @param event
   */
  @FXML
  void actionBoutonEvenementSelectionneEvt(ActionEvent event) {
    // TODO Gestion d'erreur
    Evenement e = listeEvenements.getSelectionModel().getSelectedItem();
    
    entreeNomEvt.setText(e.getNom());
    entreeLieuEvt.setText(e.getLieu());
    // TODO Afficher correctement
    entreeDateEvt.setText(e.getDate().toString());
    // Un truc comme ça
    // entreeHeureEvt.setText(e.getDate().format(new ...));
    entreeHeureEvt.clear(); // A enlever
    entreeDureeEvt.setText(Integer.toString(e.getDuree()));
    entreeMaxParticipantsEvt
        .setText(Integer.toString(e.getNbParticipantsMax()));
  }
  
  /**
   * Afficher tous les événements futurs de l’association : idem mais avec les
   * événements à venir de l’association.
   * 
   * @param event
   */
  @FXML
  void actionBoutonEvenementsFutursAssociation(ActionEvent event) {
    listeEvenements.getItems().clear();
    
    for (Evenement e : association.gestionnaireEvenements()
        .ensembleEvenementAvenir()) {
      listeEvenements.getItems().add(e);
    }
  }
  
  /**
   * Evénements futurs : idem mais en affichant uniquement les événements à
   * venir pour le membre
   * 
   * @param event
   */
  @FXML
  void actionBoutonEvenementsFutursMembre(ActionEvent event) {
    listeEvenements.getItems().clear();
    
    Membre m = getMembreFromFields();
    for (Evenement e : m.ensembleEvenementsAvenir()) {
      listeEvenements.getItems().add(e);
    }
  }
  
  /**
   * Evénements membre : affiche dans la liste de droite tous les événements du
   * membre dont les informations sont dans les champs au-dessus.
   * 
   * @param event
   */
  @FXML
  void actionBoutonEvenementsMembreMembre(ActionEvent event) {
    listeEvenements.getItems().clear();
    
    Membre m = getMembreFromFields();
    for (Evenement e : m.ensembleEvenements()) {
      listeEvenements.getItems().add(e);
    }
  }
  
  /**
   * Désinscrire membre à événement : si un membre est sélectionné dans la liste
   * de gauche et un événement est sélectionné dans la liste de droite, le
   * membre est désinscrit à cet événement.
   */
  @FXML
  void actionBoutonDesinscrireMembreEvenement(ActionEvent event) {
    // TODO GESTION ERREUR GESTION ERREUR
    InterMembre m = listeMembres.getSelectionModel().getSelectedItem();
    Evenement e = listeEvenements.getSelectionModel().getSelectedItem();
    
    
    // TODO Encore de la gestion d'erreur
    m.ensembleEvenements().remove(e);
    e.enleverParticipant(m);
  }
  
  /**
   * Inscrire membre à événement : si un membre est sélectionné dans la liste de
   * gauche et un événement est sélectionné dans la liste de droite, le membre
   * est inscrit à cet événement (dans la limite des places disponibles).
   *
   * @param event
   */
  @FXML
  void actionBoutonInscrireMembreEvenement(ActionEvent event) {
    // TODO GESTION ERREUR GESTION ERREUR
    InterMembre m = listeMembres.getSelectionModel().getSelectedItem();
    Evenement e = listeEvenements.getSelectionModel().getSelectedItem();
    
    // TODO Encore de la gestion d'erreur
    association.gestionnaireEvenements().inscriptionEvenement(e, m);
  }
  
  /**
   * Nouveau : efface le contenu des champs d’un événement afin de rajouter un
   * nouvel événement.
   * 
   */
  @FXML
  void actionBoutonNouveauEvt(ActionEvent event) {
    entreeNomEvt.clear();
    entreeLieuEvt.clear();
    entreeDateEvt.clear();
    entreeHeureEvt.clear();
    entreeDureeEvt.clear();
    entreeMaxParticipantsEvt.clear();
    message.setText("Le contenu des champs d'un événement a bien été réinitialisé !");
  }
  
  /**
   * Bouton de création d'un nouveau membre. Efface les données inscrites
   * précédemment dans les zones de textes.Un message indique que le
   *
   * @param event l'objet récupéré par un clique sur le bouton "Nouveau".
   */
  
  @FXML
  void actionBoutonNouveauMembre(ActionEvent event) {
    entreePrenomMembre.clear();
    entreeNomMembre.clear();
    entreAdresseMembre.clear();
    entreAgeMembre.clear();
    message.setText("Zone de texte \"Membre\" effacée, les données.\nVeuillez insérer les données d'un nouveau membre.");
    
  }
  

  /**
   * Supprimer : efface de la liste des événements l’événement dont les
   * informations sont affichées.
   */
  @FXML
  void actionBoutonSupprimerEvt(ActionEvent event) {
    Evenement e = getEvenementFromFields();
    association.gestionnaireEvenements().supprimerEvenement(e);
    //supprimerEvenement étant une méthode void, impossible de vérifier ?
    message.setText("La suppresion a bien été prise en compte.");
  }
  
  /**
   * Supprimer : efface de la liste des membres, le membre dont les informations
   * sont affichées.
   */
  @FXML
  void actionBoutonSupprimerMembre(ActionEvent event) {
    Membre m = new Membre(new InformationPersonnelle(entreeNomMembre.getText(),
        entreePrenomMembre.getText()));
    association.gestionnaireMembre().supprimerMembre(m);
    // TODO Affichage réussi ou pas
  }
  
  /**
   * Afficher tous les événements de l’association : afficher dans la liste tous
   * les événements de l’association.
   * 
   * @param event
   */
  @FXML
  void actionBoutonTousEvenementsAssociationEvt(ActionEvent event) {
    listeEvenements.getItems().clear();
    
    for (Evenement e : association.gestionnaireEvenements()
        .ensembleEvenements()) {
      listeEvenements.getItems().add(e);
    }
  }
  
  /**
   * Valider : lit les champs d’un événement. Si l’événement existait déjà, ses
   * informations sont mises à jour, sinon, un nouvel événement est créé.
   */
  @FXML
  void actionBoutonValiderEvt(ActionEvent event) {
    Evenement e = getEvenementFromFields();
    // Approche horrible a changer
    // Actuellement je crée un évenement a partir des champs, puis je recrée un
    // autre en appelant gestionEvenements
    // Au lieu de direct appeler gestionEvenement
    // J'écrirais probablement une deuxième méthode dans GestionEvenements pour
    // faire ça
    LocalDateTime date = e.getDate();
    Evenement cree = association.gestionnaireEvenements().creerEvenement(e.getNom(), e.getLieu(),
        date.getDayOfMonth(), date.getMonth(), date.getYear(), date.getHour(),
        date.getMinute(), e.getDuree(), e.getNbParticipantsMax());
  }
  
  /**
   * Bouton d'action pour valider les informations d'un membre. Les informations
   * prennent en compte, un nom, un prénom, une adresse et un age.
   *
   * @param event l'objet récupéré par un clique sur le bouton "Valider Membre".
   */
  
  @FXML
  void actionBoutonValiderMembre(ActionEvent event) {
    Membre m = getMembreFromFields();
    // lire les input,
    
    
    // Gérer
    association.gestionnaireMembre().ajouterMembre(m);
  }
  
  /**
   * A propos : affiche quelques informations sur votre application.
   * 
   * @param event
   */
  @FXML
  void actionMenuApropos(ActionEvent event) {
    Alert alerte = new Alert(AlertType.INFORMATION);
    alerte.setTitle("A Propos");
    String content = "Application réalisée par Jean-André, Enzo, Nicolas & Romain\n" 
        + "Tutoriel : \n"
        + "Vous avez deux fenêtres. La fênetre de gauche permet la gestion des membres "
        + "et leur importation dans l'association.\n "
        + "La fênetre de droite permet la gestion des évènements.\n";
    alerte.setContentText(content);
    alerte.showAndWait();
  }
  
  /**
   * Charger : charge les membres et les événements de l’association à partir
   * d’un fichier (dont on pourra optionnellement choisir l’emplacement). Une
   * fois chargé, les deux listes affichent tous les membres et tous les
   * événements.
   */
  @FXML
  void actionMenuCharger(ActionEvent event) {
    // TODO Choisir le fichier
    try {
      association.chargerDonnees("sauvegarde");
      message.setText("Fichier de sauvegarde chargé");
    } catch (IOException e) {
      message.setText("Impossible de charger le fichier dans \"sauvegarde\".");
    }
  }
  
  /**
   * Réinitialise l’association (efface tous les événements et membres chargés
   * en mémoire).
   */
  @FXML
  void actionMenuNouveau(ActionEvent event) {
    // On recrée une nouvelle association
    association = new GestionAssociation();
    
    // On reset les champs
    actionBoutonNouveauEvt(event);
    actionBoutonNouveauMembre(event);
    
    // On vide les listes
    listeEvenements.getItems().clear();
    listeMembres.getItems().clear();
    
    message.setText("Nouvelle association créée.");
  }
  
  /**
   * Quitter : ferme l’application.
   */
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    Alert quitter = new Alert(AlertType.CONFIRMATION);
    quitter.setTitle("Quitter l'application ?");
    quitter.setHeaderText("Voulez vous quitter l'application ?");

    // Retourne le type de bouton séléctionné ou null si un bouton n'a 
    // pas été séléctionné ( = il a séléctionné la croix)
    ButtonType result = quitter.showAndWait().orElse(null);

    // On quitte seulement si l'utilisateur valide
    if (result == ButtonType.OK) {
      Platform.exit();
    }
  }
  
  /**
   * Sauvegarder : sauvegarde les membres et les événements de l’association
   * dans un fichier (dont on pourra optionnellement choisir l’emplacement).
   */
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    try {
      association.sauvegarderDonnees("sauvegarde");
      message.setText("Données sauvegardées");
    } catch (IOException e) {
      message.setText("Erreur lors de la sauvegarde du fichier dans \"sauvegarde\"");
    }
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Initialisation de l'interface");
    association = new GestionAssociation();

  }


  /**
   * Essaye de créer un object membre a partir des text field 
   * TODO A commenter et probablement faire de la gestion d'erreur
   */
  private Membre getMembreFromFields() {
    // TODO Gerer erreurs
    int age = Integer.parseInt(entreAgeMembre.getText());
    Membre m =
        new Membre(new InformationPersonnelle(this.entreeNomMembre.getText(),
            this.entreePrenomMembre.getText(),
            this.entreAdresseMembre.getText(), age));
    return m;
  }
  
  private Evenement getEvenementFromFields() {
    String nom = entreeNomEvt.getText();
    String lieu = entreeLieuEvt.getText();
    String dateSansHeures = entreeDateEvt.getText();
    String heures = entreeHeureEvt.getText();
    int duree = Integer.parseInt(entreeDureeEvt.getText());
    int participants = Integer.parseInt(entreeMaxParticipantsEvt.getText());
    
    // TODO Gestion d'erreur, encore
    // La fonction essaye de générer un objet LocalDateTime a partir d'un
    // string. On combine les deux et on les donne a la fonction
    LocalDateTime date = LocalDateTime.parse(dateSansHeures + "T" + heures);
    Evenement e = new Evenement(nom, lieu, date, duree, participants);
    return e;
  }
}

