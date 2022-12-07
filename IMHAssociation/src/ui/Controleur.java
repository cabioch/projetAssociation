package ui;

import association.Evenement;
import association.GestionAssociation;
import association.InformationPersonnelle;
import association.InterGestionAssociation;
import association.InterMembre;
import association.Membre;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Classe publique de contrôle de l'interface Utilisateur.
 */

public class Controleur implements Initializable {
  
  @FXML
  private InterGestionAssociation association;
  
  /**
   * L'input correspondant à l'adresse d'un Membre.
   */
  @FXML
  private TextField entreAdresseMembre;
  
  /**
   * L'input correspondant à l'age d'un Membre.
   */
  @FXML
  private TextField entreAgeMembre;
  
  /**
   * L'input correspondant à la date d'un Evènement.
   */
  @FXML
  private TextField entreeDateEvt;
  
  /**
   * L'input correspondant à la durée d'un Evènement.
   */
  @FXML
  private TextField entreeDureeEvt;
  
  /**
   * L'input correspondant à l'heure de début d'un Evènement.
   */
  @FXML
  private TextField entreeHeureEvt;
  
  /**
   * L'input correspondant au lieu où se déroule d'un Evènement.
   */
  @FXML
  private TextField entreeLieuEvt;
  
  /**
   * L'input correspondant au nombre maximum de participants à un Evènement.
   */
  @FXML
  private TextField entreeMaxParticipantsEvt;
  
  /**
   * L'input correspondant au nom d'un Evènement.
   */
  @FXML
  private TextField entreeNomEvt;
  
  /**
   * L'input correspondant au nom d'un Membre.
   */
  @FXML
  private TextField entreeNomMembre;
  
  /**
   * L'input correspondant au prénom d'un Membre.
   */
  @FXML
  private TextField entreePrenomMembre;
  
  /**
   * Le label d'affichage d'une liste d'Evenèment.
   */
  @FXML
  private Label labelListeAfficheeEvt;
  
  /**
   * Le label d'affichage de la liste des Membres.
   */
  @FXML
  private Label labelListeAfficheeMembre;
  
  /**
   * La liste des Evenèments.
   */
  @FXML
  private ListView<Evenement> listeEvenements;
  
  /**
   * La liste des Membres.
   */
  @FXML
  private ListView<Membre> listeMembres;
  
  /**
   * La boite de dialogue qui réceptionne les "messages" de retour des actions
   * de l'utilisateur.
   */
  @FXML
  private TextArea message;
  
  /**
   * La police de rendu à l'écran.
   */
  @FXML
  private Font x1;
  
  /**
   * L'attribut de contrôle des couleurs de rendu à l'écran.
   */
  @FXML
  private Color x2;
  
  
  /**
   * Afficher le membre sélectionné : si un membre est sélectionné dans la
   * liste, affiche ses informations personnelles dans les quatres champs en
   * haut de la fenêtre.
   *
   * @param event est l'action d'afficher un membre selectionné
   */
  @FXML
  void actionBoutonAfficherMembreSelectionneMembre(ActionEvent event) {
    InterMembre m = listeMembres.getSelectionModel().getSelectedItem();
    if (m == null) {
      message.setText("Aucun membre sélectionné.");
      return;
    }
    InformationPersonnelle info = m.getInformationPersonnelle();
    entreeNomMembre.setText(info.getNom());
    entreePrenomMembre.setText(info.getPrenom());
    entreAgeMembre.setText(Integer.toString(info.getAge()));
    entreAdresseMembre.setText(info.getAdresse());
    if (association.gestionnaireMembre().president() != null && association
        .gestionnaireMembre().president().getInformationPersonnelle().getNom()
        .equals(m.getInformationPersonnelle().getNom())) {
      labelListeAfficheeMembre
          .setText("Le membre selectionné est le président.");
    } else {
      labelListeAfficheeMembre.setText("Le membre selectionné.");
    }
    message.setText("Affichage du membre sélectionné.");
  }
  
  /**
   * Bouton d'affichage participant: Affiche dans la liste de gauche, les
   * participants inscrits à l’événement dont les informations sont affichées
   * dans les champs.
   *
   * @param event est l'action d'afficher un participant
   */
  @FXML
  void actionBoutonAfficherParticipantsEvt(ActionEvent event) {
    Evenement e = getEvenementFromFields();
    if (e == null) {
      message.setText("L'événement n'est pas un élément valide.");
      return;
    }
    
    // Si l'événement sélectionné ne fait pas parti de l'association
    if (!association.gestionnaireEvenements().ensembleEvenements()
        .contains(e)) {
      message.setText("L'évenement ne fait pas parti de l'association.");
      return;
    }
    listeMembres.getItems().clear();
    for (InterMembre m : e.getParticipants()) {
      listeMembres.getItems().add((Membre) m);
    }
    labelListeAfficheeMembre
        .setText(" Tous les participants de l'événement " + e.getNom());
    message.setText("Affichage des participants de l'événement " + e.getNom()
        + " a été effectué.");
  }
  
  /**
   * Bouton d'affichage de tous les membres : affiche dans la liste de droite
   * tous les membres de l’association.
   *
   * @param event est l'action d'afficher tous les membres de l'association
   */
  @FXML
  void actionBoutonAfficherTousMembresMembre() {
    listeMembres.getItems().clear();
    for (InterMembre m : association.gestionnaireMembre().ensembleMembres()) {
      listeMembres.getItems().add((Membre) m);
    }
    labelListeAfficheeMembre.setText("Tous les membres.");
    message.setText("Affichage des membres de l'association.");
    
  }
  
  /**
   * Affiche l’événement sélectionné : si un événement est sélectionné dans la
   * liste, affiche ses informations dans les champs en haut de la fenêtre.
   *
   * @param event est l'action d'afficher l'évènement sélectionné
   */
  @FXML
  void actionBoutonEvenementSelectionneEvt(ActionEvent event) {
    final DateTimeFormatter dtformatDate =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");
    final DateTimeFormatter dtformatHeure =
        DateTimeFormatter.ofPattern("HH:mm");
    
    Evenement e = listeEvenements.getSelectionModel().getSelectedItem();
    if (e == null) {
      message.setText("Aucun événement sélectionné.");
      return;
    }
    entreeNomEvt.setText(e.getNom());
    entreeLieuEvt.setText(e.getLieu());
    // Récupère la date au format DD-MM-YYYY (comme l'entrée nécessaire)
    entreeDateEvt.setText(dtformatDate.format(e.getDate()));
    // Récupère l'heure au format HH:MM (comme l'entrée nécessaire)
    entreeHeureEvt.setText(dtformatHeure.format(e.getDate()));
    entreeDureeEvt.setText(Integer.toString(e.getDuree()));
    entreeMaxParticipantsEvt
        .setText(Integer.toString(e.getNbParticipantsMax()));
  }
  
  /**
   * Affiche tous les événements futurs de l’association. Les évènements sont
   * listés dans la zone de texte.
   *
   * @param event est l'action d'afficher tous les évènements futurs de
   *        l'association
   */
  @FXML
  void actionBoutonEvenementsFutursAssociation(ActionEvent event) {
    listeEvenements.getItems().clear();
    for (Evenement e : association.gestionnaireEvenements()
        .ensembleEvenementAvenir()) {
      listeEvenements.getItems().add(e);
    }
    
    labelListeAfficheeEvt
        .setText("Tous les événements futurs de l'association. ");
    message.setText(
        "L'affichage de tous les événements futurs de l'association a bien été effectué.");
  }
  
  /**
   * Evénements futurs membre : Affiche tous les événements futurs pour le
   * membre.
   *
   * @param event est l'action d'afficher les évènements futurs du membre
   */
  @FXML
  void actionBoutonEvenementsFutursMembre(ActionEvent event) {
    listeEvenements.getItems().clear();
    Membre m = getMembreFromFields();
    for (Evenement e : m.ensembleEvenementsAvenir()) {
      listeEvenements.getItems().add(e);
    }
    if (m.ensembleEvenementsAvenir().isEmpty()) {
      message.setText("Aucun évènements futurs à afficher.");
    } else {
      message.setText("Liste générée.");
    }
  }
  
  /**
   * Evénements membre : affiche dans la liste de droite tous les événements du
   * membre dont les informations sont dans les champs au-dessus.
   *
   * @param event est l'action d'afficher les évènements ou le membre est
   *        inscrit
   */
  @FXML
  void actionBoutonEvenementsMembreMembre(ActionEvent event) {
    listeEvenements.getItems().clear();
    Membre m = getMembreFromFields();
    if (m == null) {
      message.setText(
          "Impossible de trouver le membre dans l'ensemble des membres");
    }
    for (Evenement e : m.ensembleEvenements()) {
      listeEvenements.getItems().add(e);
    }
    if (m.ensembleEvenements().isEmpty()) {
      message.setText("Aucune participation à des évènements.");
    } else {
      message.setText("Liste des évènements générée.");
    }
  }
  
  /**
   * Désinscrire membre à événement : si un membre est sélectionné dans la liste
   * de gauche et un événement est sélectionné dans la liste de droite, le
   * membre est désinscrit à cet événement.
   *
   * @param event est l'action de désinscrire le membre a un évènement
   *        selectionné
   */
  @FXML
  void actionBoutonDesinscrireMembreEvenement(ActionEvent event) {
    InterMembre m = listeMembres.getSelectionModel().getSelectedItem();
    Evenement e = listeEvenements.getSelectionModel().getSelectedItem();
    if (m == null) {
      message.setText("Aucun membre sélectionné.");
      return;
    }
    if (e == null) {
      message.setText("Aucun événement sélectionné.");
      return;
    }
    m.ensembleEvenements().remove(e);
    e.enleverParticipant(m);
    message.setText("Le participant a bien été enlevé.");
  }
  
  /**
   * Inscrire membre à un événement : si un membre est sélectionné dans la liste
   * de gauche et un événement est sélectionné dans la liste de droite, le
   * membre est inscrit à cet événement (dans la limite des places disponibles).
   *
   * @param event est l'action d'inscrire un membre à un évènement
   */
  @FXML
  void actionBoutonInscrireMembreEvenement(ActionEvent event) {
    InterMembre m = listeMembres.getSelectionModel().getSelectedItem();
    Evenement e = listeEvenements.getSelectionModel().getSelectedItem();
    if (m == null) {
      message.setText("Aucun membre sélectionné.");
      return;
    }
    if (e == null) {
      message.setText("Aucun événement sélectionné.");
      return;
    }
    if (e.getDate().isBefore(LocalDateTime.now())) {
      message.setText("L'événement est déjà passé");
      return;
    }
    if (association.gestionnaireEvenements().inscriptionEvenement(e, m)) {
      message.setText("Le membre a bien été inscrit à l'évenement.");
      return;
    }
    message.setText(
        "Il y a eu une erreur lors de l'ajout du membre à l'événement.");
  }
  
  /**
   * Bouton de réinitialisation d'un nouvel évènement. Efface le contenu des
   * champs d’un événement afin de rajouter un nouvel événement.
   *
   * @param event est l'action de réinitialisation d'un nouvel évènement
   */
  @FXML
  void actionBoutonNouveauEvt(ActionEvent event) {
    entreeNomEvt.clear();
    entreeLieuEvt.clear();
    entreeDateEvt.clear();
    entreeHeureEvt.clear();
    entreeDureeEvt.clear();
    entreeMaxParticipantsEvt.clear();
    message.setText(
        "Le contenu des champs d'un événement a bien été réinitialisé !");
  }
  
  /**
   * Bouton de création d'un nouveau membre. Efface les données inscrites
   * précédemment dans les zones de textes.Un message indique qu'un nouveau
   * membre peut-être créé.
   *
   * @param event est l'action de création d'un nouveau membre
   */
  
  @FXML
  void actionBoutonNouveauMembre(ActionEvent event) {
    entreePrenomMembre.clear();
    entreeNomMembre.clear();
    entreAdresseMembre.clear();
    entreAgeMembre.clear();
    message.setText("Le contenu des champs a été réinitialisé.\n"
        + "Veuillez entrer de nouvelles données créer un nouveau membre.");
  }
  
  /**
   * Supprimer : efface de la liste des événements l’événement dont les
   * informations sont affichées.
   *
   * @param event est l'action de supprimer un évènement
   */
  @FXML
  void actionBoutonSupprimerEvt(ActionEvent event) {
    // On prend les valeurs des entrées et on les associe à un événement
    Evenement e = getEvenementFromFields();
    
    if (e == null) {
      message.setText("Aucun événement sélectionné.");
      return;
    }
    // On supprime tous les membres inscrits à l'evènement.
    for (InterMembre m : association.gestionnaireMembre().ensembleMembres()) {
      if (m.ensembleEvenements().contains(e)) {
        e.enleverParticipant(m);
        m.ensembleEvenements().remove(e);
      }
    }
    // On supprime cet événement dans le gestionnaire
    association.gestionnaireEvenements().supprimerEvenement(e);
    // On clear les liste des événements pour l'affichage de la liste mise à
    // jour
    listeEvenements.getItems().clear();
    // on affiche la liste mise à jour
    for (Evenement ei : association.gestionnaireEvenements()
        .ensembleEvenements()) {
      listeEvenements.getItems().add(ei);
    }
    
    // On oublie pas de supprimer les entrées
    entreeNomEvt.clear();
    entreeLieuEvt.clear();
    entreeDateEvt.clear();
    entreeHeureEvt.clear();
    entreeDureeEvt.clear();
    entreeMaxParticipantsEvt.clear();
    
    // gérer la suppression en cas d'erreur.
    // Si l'evenement existe et a bien été supprimer comme les membre sinon un
    // aute message.
    message.setText("La suppression a bien été prise en compte.");
  }
  
  /**
   * Efface de la liste des membres, le membre dont les informations sont
   * affichées dans les champs. Si le membre était président, un message de
   * place vacante est affiché pour le rôle de président. Le membre est retiré
   * des évènements ou il était inscrit.
   *
   * @param event l'action de supprimer un membre
   *
   */
  @FXML
  void actionBoutonSupprimerMembre(ActionEvent event) {
    if (entreeNomMembre.getText() == "" && entreePrenomMembre.getText() == "") {
      message.setText("Erreur, aucun membre sélectionné.");
    } else {
      int age;
      try {
        age = Integer.parseInt(entreAgeMembre.getText());
      } catch (NumberFormatException e) {
        age = 0;
      }
      Membre m =
          new Membre(new InformationPersonnelle(entreeNomMembre.getText(),
              entreePrenomMembre.getText(), entreAdresseMembre.getText(), age));
      if (association.gestionnaireMembre().president() != null
          && association.gestionnaireMembre().president().equals(m)) {
        message.setText(
            "La suppression du membre a été faite.\n La place de président est vacante.");
      } else {
        message.setText("La suppression du membre a été faite.");
      }
      association.gestionnaireMembre().supprimerMembre(m);
      listeMembres.getItems().clear();
      // On retire le membre des evènements ou il a été inscrits en automatique.
      for (Evenement e : association.gestionnaireEvenements()
          .ensembleEvenements()) {
        e.enleverParticipant(m);
      }
      // On créée de nouveau la liste des membres.
      for (InterMembre ei : association.gestionnaireMembre()
          .ensembleMembres()) {
        listeMembres.getItems().add((Membre) ei);
      }
      // On nettoie les champs
      entreePrenomMembre.clear();
      entreeNomMembre.clear();
      entreAdresseMembre.clear();
      entreAgeMembre.clear();
    }
  }
  
  /**
   * Afficher tous les événements de l’association : afficher dans la liste tous
   * les événements de l’association.
   *
   * @param event est l'action d'afficher tous les évènements de l'association".
   */
  @FXML
  void actionBoutonTousEvenementsAssociationEvt(ActionEvent event) {
    listeEvenements.getItems().clear();
    
    for (Evenement e : association.gestionnaireEvenements()
        .ensembleEvenements()) {
      listeEvenements.getItems().add(e);
    }
    
    labelListeAfficheeEvt.setText(" Tous les événements de l'association.");
    message.setText(
        "L'affichage de tous les événements de l'association a bien été effectué.");
  }
  
  /**
   * Valider évènement : lit les champs d’un événement. Si l’événement existait
   * déjà, ses informations sont mises à jour, sinon, un nouvel événement est
   * créé.
   *
   * @param et l'action de valider un évènement
   */
  @FXML
  void actionBoutonValiderEvt(ActionEvent event) {
    final String nom = entreeNomEvt.getText();
    final String lieu = entreeLieuEvt.getText();
    String dateStr = entreeDateEvt.getText();
    String heureStr = entreeHeureEvt.getText();
    int duree;
    int participants;
    // On vérifie les paramètres de durée et de participants
    try {
      duree = Integer.parseInt(entreeDureeEvt.getText());
      participants = Integer.parseInt(entreeMaxParticipantsEvt.getText());
    } catch (NumberFormatException e) {
      message.setText(
          "Merci de définir une durée en minutes et un nombre de participants supérieur à zéro.");
      return;
    }
    // On vérifie que la date correspond bien au format attendu
    if (!dateStr.matches("\\d{1,2}-\\d{1,2}-\\d{4}")
        || !heureStr.matches("\\d{2}:\\d{2}")) {
      message.setText("Merci de respecter le format des dates :\n"
          + "JJ-MM-AAAA\n" + "HH:MM");
      return;
    }
    // On sépare et récupère les données de date & d'heure
    // [0] -> Jour; [1] -> Mois; [2] -> Année
    String[] dateStrArray = dateStr.split("-");
    // [0] -> Heure; [1] -> Minutes
    String[] heureStrArray = heureStr.split(":");
    // On ne fait pas de gestion d'erreur sur les parseInt
    // car on sait déjà qu'ils sont conformes grâce Au test avec regex d'avant
    int annee = Integer.parseInt(dateStrArray[2]);
    int indexMois = Integer.parseInt(dateStrArray[1]) - 1;
    // On vérifie que le mois est bien entre 1 et 12
    if (indexMois > 11 || indexMois < 0) {
      message.setText("Le mois doit être compris entre 1 et 12.");
      return;
    }
    Month mois = Month.values()[indexMois];
    int jour = Integer.parseInt(dateStrArray[0]);
    int heure = Integer.parseInt(heureStrArray[0]);
    int minute = Integer.parseInt(heureStrArray[1]);
    Evenement cree = association.gestionnaireEvenements().creerEvenement(nom,
        lieu, jour, mois, annee, heure, minute, duree, participants);
    if (cree != null) {
      message.setText("L'évenement " + cree.getNom() + " a bien été créé.");
      return;
    }
    message
        .setText("L'événement n'as pas pu être créé.\n Vérifiez vos entrées.");
  }
  
  /**
   * Méthode de création d'un membre via les champs de l'interface.
   */
  private Membre getMembreFromFields() {
    int age;
    try {
      age = Integer.parseInt(entreAgeMembre.getText());
    } catch (NumberFormatException e) {
      age = 0;
    }
    association.InformationPersonnelle info = new InformationPersonnelle(
        this.entreeNomMembre.getText(), this.entreePrenomMembre.getText(),
        this.entreAdresseMembre.getText(), age);
    // Essaye de récupérer le membre dans l'ensemble de membres avec les
    // événements
    // Correspondants
    Membre m = new Membre(info);
    for (InterMembre mbr : association.gestionnaireMembre().ensembleMembres()) {
      if (mbr.equals(m)) {
        return (Membre) mbr;
      }
    }
    return null;
  }
  
  /**
   * Bouton d'action pour valider les informations d'un membre. Les informations
   * prennent en compte un nom, un prénom, une adresse et un age. Si le membre
   * existe déjà, ses informations sont mises à jour.
   *
   * @param event est l'action de valider les champs de texte pour Membre.
   */
  
  @FXML
  void actionBoutonValiderMembre(ActionEvent event) {
    int age;
    try {
      age = Integer.parseInt(entreAgeMembre.getText());
    } catch (NumberFormatException e) {
      age = 0;
    }
    association.InformationPersonnelle info = new InformationPersonnelle(
        this.entreeNomMembre.getText(), this.entreePrenomMembre.getText(),
        this.entreAdresseMembre.getText(), age);
    // Essaye de récupérer le membre dans l'ensemble de membres avec les
    // événements
    // Correspondants
    Membre m = new Membre(info);
    
    // nouveau membre
    if (association.gestionnaireMembre().ajouterMembre(m)) {
      message.setText(
          "" + this.entreePrenomMembre.getText().substring(0, 1).toUpperCase()
              + this.entreePrenomMembre.getText()
                  .substring(1, entreePrenomMembre.getLength()).toLowerCase()
              + " " + this.entreeNomMembre.getText().toUpperCase()
              + " à bien été ajouté comme nouveau membre.");
      // on efface les champs automatiquement.
      entreePrenomMembre.clear();
      entreeNomMembre.clear();
      entreAdresseMembre.clear();
      entreAgeMembre.clear();
      return;
    }
    // si pas d'ajout, verif si membre existe puis maj sinon erreur.
    for (InterMembre me : association.gestionnaireMembre().ensembleMembres()) {
      if (m.getInformationPersonnelle().getNom()
          .equals(me.getInformationPersonnelle().getNom())
          && m.getInformationPersonnelle().getPrenom()
              .equals(me.getInformationPersonnelle().getPrenom())) {
        message.setText("Le membre "
            + this.entreePrenomMembre.getText().substring(0, 1).toUpperCase()
            + this.entreePrenomMembre.getText()
                .substring(1, entreePrenomMembre.getLength()).toLowerCase()
            + " " + this.entreeNomMembre.getText().toUpperCase()
            + " n'est pas créé.\n Ses informations personnelles ont été mise à jour.");
        association.gestionnaireMembre().supprimerMembre(me);
        me.definirInformationPersonnnelle(m.getInformationPersonnelle());
        association.gestionnaireMembre().ajouterMembre(me);
        entreePrenomMembre.clear();
        entreeNomMembre.clear();
        entreAdresseMembre.clear();
        entreAgeMembre.clear();
        return;
      } else {
        message.setText(
            "Erreur sur la création du membre.\n Veuillez vérifier les valeurs des champs.");
      }
    }
  }
  
  /**
   * A propos : affiche quelques informations sur votre application.
   *
   * @param event est l'action pour lire les informations générales de
   *        l'application.
   */
  @FXML
  void actionMenuApropos(ActionEvent event) {
    Alert alerte = new Alert(AlertType.INFORMATION);
    alerte.setTitle("A Propos");
    String content = "Application  de Gestion d'une Association.\n\n"
        + "Réalisation par Jean-André, Enzo, Nicolas & Romain\n\n"
        + "Tutoriel : \n"
        + "Vous avez deux encarts.\n La fênetre de gauche permet la gestion des membres "
        + "et leur importation dans l'association.\n "
        + "La fenêtre de droite permet la gestion des évènements de l'association.\n";
    alerte.setContentText(content);
    alerte.showAndWait();
  }
  
  /**
   * Menu Charger : charge les membres et les événements de l’association à
   * partir d’un fichier (dont on pourra optionnellement choisir l’emplacement).
   * Une fois chargé, les deux listes affichent tous les membres et tous les
   * événements.
   *
   * @param est l'action de charger un fichier de sauvegarde d'une association.
   */
  @FXML
  void actionMenuCharger(ActionEvent event) {
    // Option de choisir le fichier ?
    try {
      association.chargerDonnees("sauvegarde");
      message
          .setText("Fichier des données de l'Association chargé avec succès");
    } catch (IOException e) {
      message.setText("Impossible de charger le fichier.");
    }
  }
  
  /**
   * Menu Nouveau : Réinitialise l’association (efface tous les événements et
   * membres chargés en mémoire).
   *
   * @param event Est l'action de réinitialiser le programme en effaçant toutes
   *        les données.
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
   * Menu Quitter : Execute la fermeture de l'application.
   *
   * @param event est l'action de quitter l'application.
   */
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    Alert quitter = new Alert(AlertType.CONFIRMATION);
    quitter.setTitle("Quitter l'application ?");
    
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
   *
   * @param event est l'action de sauvegarder les évènements et les membres de
   *        l'association
   */
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    try {
      association.sauvegarderDonnees("sauvegarde");
      message.setText("Données de l'Association sauvegardées");
    } catch (IOException e) {
      message.setText(
          "Erreur lors de la sauvegarde du fichier dans \"sauvegarde\"");
    }
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Initialisation de l'interface");
    association = new GestionAssociation();
  }
  
  /**
   * Fonction intermédiaire qui permet de récupérer un objet Evenement à partir
   * des données entrées dans les champs.
   *
   * @return Un Evenement à partir des champs ou bien <code>null</code> si il
   *         n'est pas valide.
   */
  private Evenement getEvenementFromFields() {
    final String nom = entreeNomEvt.getText();
    final String lieu = entreeLieuEvt.getText();
    String dateStr = entreeDateEvt.getText();
    String heureStr = entreeHeureEvt.getText();
    int duree;
    int participants;
    
    // On vérifie les paramètres de durée et de participants
    try {
      duree = Integer.parseInt(entreeDureeEvt.getText());
      participants = Integer.parseInt(entreeMaxParticipantsEvt.getText());
    } catch (NumberFormatException e) {
      return null;
    }
    
    // On vérifie que la date correspond bien au format attendu
    if (!dateStr.matches("\\d{1,2}-\\d{1,2}-\\d{4}")
        || !heureStr.matches("\\d{2}:\\d{2}")) {
      return null;
    }
    
    // On sépare et récupère les données de date & d'heure
    // [0] -> Jour; [1] -> Mois; [2] -> Année
    String[] dateStrArray = dateStr.split("-");
    // [0] -> Heure; [1] -> Minutes
    String[] heureStrArray = heureStr.split(":");
    
    // On ne fait pas de gestion d'erreur sur les parseInt
    // car on sait déjà qu'ils sont conformes grâce Au test avec regex d'avant
    int annee = Integer.parseInt(dateStrArray[2]);
    int indexMois = Integer.parseInt(dateStrArray[1]) - 1;
    
    // On vérifie que le mois est bien entre 1 et 12
    if (indexMois > 11 || indexMois < 0) {
      return null;
    }
    
    Month mois = Month.values()[indexMois];
    int jour = Integer.parseInt(dateStrArray[0]);
    int heure = Integer.parseInt(heureStrArray[0]);
    int minute = Integer.parseInt(heureStrArray[1]);
    
    // Retourne l'événement avec les participants nécessaires
    Evenement evTmp = new Evenement(nom, lieu, annee, mois, jour, heure, minute,
        duree, participants);
    for (Evenement e : association.gestionnaireEvenements()
        .ensembleEvenements()) {
      if (e.equals(evTmp)) {
        return e;
      }
    }
    
    return null;
  }
}
