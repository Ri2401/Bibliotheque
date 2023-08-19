/**
 * author RANDRIANARIJAONA RINASOA FELICIE
 * COPYRIGHT AUGUST, 2023
 */

package riri.sg.com.bibliotheque;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainClass extends Application {
    @Override
    public void start(Stage stage) {
        //Initialisation de l'Arbre
        Arbre arbre=new Arbre();
        //Ajout de quelque livre pour le test
        arbre.ajouterLivre(new Livre("Java","Victor Pixel","Informatique"));
        arbre.ajouterLivre(new Livre("La règle des 5 secondes","Mel Robbins","Developpement personnel"));
        arbre.ajouterLivre(new Livre("Le germinal","Emile Zola","Roman"));
        arbre.ajouterLivre(new Livre("Les secrets du code java","Lisa Developer","Manuel de programmation"));
        arbre.ajouterLivre(new Livre("L'algorithme","Max Compiler","Informatique"));
        arbre.ajouterLivre(new Livre("Eclipse","Laura","Bande dessinée"));
        //Initialisation du MenuBar
        MenuBar bar=new MenuBar();
        //Initialisation du Menu
        Menu actionMenu=new Menu("_Menu");
        actionMenu.setId("action");
        MenuItem ajouterItem=new MenuItem("_Ajouter");
        MenuItem rechercherItem=new MenuItem("_Rechercher");
        MenuItem visualiserItem=new MenuItem("_Visualiser");
            //Ajout d'icone au menuItem
            ImageView imgAjouter=new ImageView(new Image(getClass().getResource("/riri/sg/com/bibliotheque/ajouter.png").toExternalForm()));
            ImageView imgRechercher=new ImageView(new Image(getClass().getResource("/riri/sg/com/bibliotheque/rechercher.png").toExternalForm()));
            ImageView imgVisualiser=new ImageView(new Image(getClass().getResource("/riri/sg/com/bibliotheque/visualiser.png").toExternalForm()));
            ajouterItem.setGraphic(imgAjouter);
            rechercherItem.setGraphic(imgRechercher);
            visualiserItem.setGraphic(imgVisualiser);

        VBox pan=new VBox(50);
        pan.setId("pan");

        //Definition de la GrandTitre
        Label labGrandTitre=new Label("BIENVENU DANS NOTRE MINI-BIBLIOTHEQUE");
        pan.getChildren().add(new ImageView(new Image(getClass().getResource(
                "/riri/sg/com/bibliotheque/fond.png").toExternalForm(),250,250,false,true)));
        pan.setAlignment(Pos.CENTER);
        labGrandTitre.setId("grandTitre");
        HBox hboxGrandTitre=new HBox(labGrandTitre);
        hboxGrandTitre.setAlignment(Pos.CENTER);
        //Creation du Groupe numero 1 pour contenir toute les composants de l'option Ajouter
        Text labTitre=new Text("Titre  ");
        Text labAuteur=new Text("Auteur");
        Text labGenre =new Text("Genre  ");
        TextField fieldTitre=new TextField();
        TextField fieldAuteur=new TextField();
        TextField fieldGenre=new TextField();
            fieldTitre.setPromptText("Ex: La Programmation Java");
            fieldAuteur.setPromptText("Ex: Victor Pixel");
            fieldGenre.setPromptText("Ex: Informatique");
        Button btnEnregistrer=new Button("Enregistrer");
        ImageView imgBtnEnregistrer=new ImageView(new Image(getClass().getResource("/riri/sg/com/bibliotheque/enregistrer.png").toExternalForm()));
        btnEnregistrer.setGraphic(imgBtnEnregistrer);
        HBox hboxTitre=new HBox(40,labTitre,fieldTitre);
        HBox hboxAuteur=new HBox(40,labAuteur,fieldAuteur);
        HBox hboxGenre=new HBox(40,labGenre,fieldGenre);
        HBox hboxButton=new HBox(40,btnEnregistrer);
        hboxTitre.setAlignment(Pos.CENTER);
        hboxAuteur.setAlignment(Pos.CENTER);
        hboxGenre.setAlignment(Pos.CENTER);
        hboxButton.setAlignment(Pos.CENTER);

        VBox gr1=new VBox(50,hboxTitre,hboxAuteur,hboxGenre,hboxButton);
        gr1.setId("gr1");
        gr1.setAlignment(Pos.BASELINE_CENTER);

        //Creation du Groupe numeros 2 pour contenir toute les composants de l'option Rechercher
        Text labRecherche=new Text("Titre");
        Button btnRechercher=new Button("Rechercher");
        ImageView imgBtnRech=new ImageView(new Image(getClass().getResource("/riri/sg/com/bibliotheque/loupe.png").toExternalForm()));
        btnRechercher.setGraphic(imgBtnRech);
        TextField fieldRechercher=new TextField();
            fieldRechercher.setPromptText("Ex: Java");
        HBox hboxRecherche=new HBox(40,labRecherche,fieldRechercher,btnRechercher);
        hboxRecherche.setAlignment(Pos.CENTER);
        //vbox
        VBox vBoxtextAreaResultat=new VBox(10);
            Text labTitreResultat=new Text("Résultat de la recherche ");
                Button btnSupprimer =new Button("Supprimer");
                btnSupprimer.setId("btnSupprimer");
                Button btnModifier=new Button("Mettre à jour");
                ImageView imgBtnSupprimer=new ImageView(new Image(getClass().getResource("/riri/sg/com/bibliotheque/sup1.png").toExternalForm()));
                btnSupprimer.setGraphic(imgBtnSupprimer);
                ImageView imgBtnModifier=new ImageView(new Image(getClass().getResource("/riri/sg/com/bibliotheque/modifierFichier.png").toExternalForm()));
                btnModifier.setGraphic(imgBtnModifier);
                //Desactivation des bouton
                disabledButton(true,btnModifier,btnSupprimer);
            HBox hboxButtonSM=new HBox(40,btnSupprimer,btnModifier);
            hboxButtonSM.setAlignment(Pos.CENTER);
            TextArea textAreaResultat=new TextArea();
            textAreaResultat.setEditable(false);
            textAreaResultat.setMaxWidth(500);
            textAreaResultat.setMaxHeight(150);
        vBoxtextAreaResultat.getChildren().addAll(labTitreResultat,textAreaResultat,hboxButtonSM);
        vBoxtextAreaResultat.setAlignment(Pos.CENTER);
        VBox gr2=new VBox(20,hboxRecherche,vBoxtextAreaResultat);

        //Creation du Groupe numero 1 pour contenir toute les composants de l'option Visualisation
        Text labTitreVisualisationArbre=new Text("Visualisation livre sous forme d' Arbre");
        Text labTitreTableVisualisation=new Text("Livre dans la bibliothèque");
        TextArea textAreaArbre=new TextArea();
            textAreaArbre.setEditable(false);
            textAreaArbre.setMaxWidth(500);
        TableView<Livre> tableView =new TableView<>();
        TableColumn<Livre,String> colTitre=new TableColumn<>("Titre");
            colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            colTitre.setMinWidth(200);
        TableColumn<Livre,String> colAuteur=new TableColumn<>("Auteur");
            colAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
            colAuteur.setMinWidth(155);
        TableColumn<Livre,String> colGenre=new TableColumn<>("Genre");
            colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            colGenre.setMinWidth(190);
        tableView.getColumns().addAll(colTitre,colAuteur,colGenre);
        tableView.setMaxWidth(540);
        tableView.setMaxHeight(200);
        VBox vbox3=new VBox(15,labTitreTableVisualisation,tableView,labTitreVisualisationArbre,textAreaArbre);
        vbox3.setId("vbox3");
        vbox3.setAlignment(Pos.CENTER);
        ScrollPane gr3=new ScrollPane(vbox3);
        gr3.setFitToWidth(true);
        actionMenu.getItems().addAll(ajouterItem,rechercherItem,visualiserItem);
        bar.getMenus().addAll(actionMenu);

        //Initialisation de la conteneur de base
        VBox root=new VBox();
        root.setAlignment(Pos.TOP_LEFT);
        root.setId("root");
        root.getChildren().addAll(bar,hboxGrandTitre,pan);

        //Initialisation de la fenetre principale
        Scene scene = new Scene(root, 640, 480, Color.WHITE);
        scene.getStylesheets().add(getClass().getResource("/riri/sg/com/bibliotheque/style.css").toExternalForm());
        stage.setTitle("Bibliothèque");
        stage.getIcons().add(new Image(getClass().getResource("/riri/sg/com/bibliotheque/icon.png").toExternalForm()));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        //GESTION DES EVENEMENT
        //Evenement liés au Menu
        //interseption de l'evenement click a l'option ajouter
        ajouterItem.setOnAction(e->
        {
            pan.getChildren().clear();
            labGrandTitre.setText("AJOUTER UN LIVRE");
            pan.getChildren().add(gr1);
        });
        //interseption de l'evenement click a l'option rechercher
        rechercherItem.setOnAction(e->
        {
            pan.getChildren().clear();
            labGrandTitre.setText("RECHERCHER UN LIVRE");
            pan.getChildren().add(gr2);
        });
        //interseption de l'evenement click a l'option visualiser
        visualiserItem.setOnAction(e->
        {
            pan.getChildren().clear();
            //Modification de la grand titre
            labGrandTitre.setText("VISUALISATION DES LIVRES ENREGISTRES");
            //Mise a jour de l'affichage
            pan.getChildren().add(gr3);
            textAreaArbre.clear();
            tableView.getItems().clear();
            arbre.afficherArbre(tableView,textAreaArbre);
        });
        //interseption de l'evenement click au bouton enregistrer
        btnEnregistrer.setOnAction(e->
        {
            //Verification de toute les champs
            if (!fieldTitre.getText().trim().equals("") || !fieldAuteur.getText().trim().equals("") || !fieldGenre.getText().trim().equals(""))
            {
                arbre.ajouterLivre(new Livre(fieldTitre.getText(),fieldAuteur.getText(),fieldGenre.getText()));
                //Initialisation des champs
                initialisationTextField(fieldTitre,fieldAuteur,fieldGenre);
                createAndShowInformationDialog("Ajout du livre avec succès");
            }
            else
            {
                createAndShowErrorDialog("Tout les champs sont obligatoire !");
            }
        });
        //interseption de l'evenement click au bouton rechercher
        btnRechercher.setOnAction(e->
        {
            if(!fieldRechercher.getText().trim().equals(""))
            {
                Livre livreARechercher=arbre.rechercherLivre(fieldRechercher.getText().trim());
                String resultats;
                //Verification du resultat de la recherche s'il existe
                //on reactive les boutons de Mise a jour et suppression
                if(livreARechercher!=null)
                {
                    //Creation de la resultat
                    resultats="\t\tLivre Trouvé ;)\n\t\t---------------------";
                    resultats+="\n\n\t\t1-Titre  :"+livreARechercher.titre;
                    resultats+="\n\t\t2-Auteur :"+livreARechercher.auteur;
                    resultats+="\n\t\t3-Genre  :"+livreARechercher.genre;
                    resultats+="\n\n\t\t___________^-^____________";
                    //Reactiver les boutons pour supprimer et modifier
                    disabledButton(false,btnModifier,btnSupprimer);
                    btnSupprimer.setOnAction(event->
                    {
                        Alert alertSuppression=new Alert(Alert.AlertType.CONFIRMATION,
                                "Vous-êtez sûre de vouloir supprimer le livre intitulé `"+livreARechercher.titre+"` ?");
                        alertSuppression.showAndWait().ifPresent(
                             buttonType -> {
                                 if(buttonType==ButtonType.OK){
                                     arbre.supprimerLivre(livreARechercher.titre);
                                     //Desactivation des bouton
                                     disabledButton(true,btnModifier,btnSupprimer);
                                     textAreaResultat.setText("\t\t\n\n=> Le livre a été supprimer");
                                 }
                             }
                        );
                    });
                    btnModifier.setOnAction(event->
                    {
                        //CREATION DE LA BOITE DE DIALOG POUR LA MODIFICATION
                        Text txtModifAuteur=new Text("Auteur");
                        Text txtModifGenre=new Text("Genre ");
                        //TextField pour le champ auteur et genre
                        TextField fieldModifAuteur=new TextField();
                        TextField fieldModifGenre=new TextField();
                        fieldModifAuteur.setPromptText(livreARechercher.auteur);
                        fieldModifGenre.setPromptText(livreARechercher.genre);
                        //Creation de nouvelle conteneur pour contenir les label et textfield
                        HBox hboxModifAuteur=new HBox(30,txtModifAuteur,fieldModifAuteur);
                        HBox hboxModifGenre=new HBox(30,txtModifGenre,fieldModifGenre);
                        VBox vBoxModification=new VBox(20,hboxModifAuteur,hboxModifGenre);
                        //Creation d'un nouveau type de bouton pour le boite de dialogue personnaliser
                        ButtonType btnCancel=new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                        ButtonType btnConfirm=new ButtonType("Enregistrer",ButtonBar.ButtonData.APPLY);
                        //Creation de la boite de dialogue personnaliser
                        Dialog dialModification=new Dialog();
                        dialModification.setTitle("Modification du livre");
                        dialModification.setHeaderText("** "+livreARechercher.titre+" **");
                        dialModification.getDialogPane().setContent(vBoxModification);
                        dialModification.getDialogPane().getButtonTypes().addAll(btnConfirm,btnCancel);
                        //Affichage de la boite de dialogue et attend de l'action retourné
                        dialModification.showAndWait().ifPresent(
                                ButtonType->
                                {
                                    if(ButtonType==btnConfirm)
                                    {
                                        if(!fieldModifAuteur.getText().trim().equals("")||!fieldModifGenre.getText().trim().equals(""))
                                        {
                                           arbre.modifierLivre(livreARechercher.titre, fieldModifAuteur.getText(),fieldModifGenre.getText());
                                           textAreaResultat.setText("\t\t\n\n=> Modification des détails du livre `"+livreARechercher.titre+"`");
                                            //Desactivation des bouton
                                            disabledButton(true,btnModifier,btnSupprimer);
                                        }
                                        else{
                                            createAndShowErrorDialog("Echèc de la modification! (Tout les champs sont obligatoire)");
                                        }
                                    }
                                }
                        );
                    });
                }
                else
                {
                    resultats="\t\tLivre non-trouvé \n\t\t-----------------------";
                    //Desactivation des bouton
                    disabledButton(true,btnModifier,btnSupprimer);
                }
                textAreaResultat.setText(resultats);
            }
            else
            {
                createAndShowErrorDialog("Le titre du livre doit être entrer !");
            }
        });
    }
    /**
     *  INITIALISE LES ZONES DE TEXTE ENTRER
     *  EN PARAMETRE C-A-D EFFACE SES CONTENUS
     * @param textField
     */
    private void initialisationTextField(TextInputControl... textField)
    {
        for (int i=0; i< textField.length;i++)
        {
            textField[i].clear();
        }
    }

    /**
     * CREER ET AFFICHE UNE BOITE DE DIALOG POUR UNE ERREUR
     * @param content
     */
    private  void createAndShowErrorDialog(String content)
    {
        Alert alertError=new Alert(Alert.AlertType.ERROR,content,ButtonType.OK);
        alertError.showAndWait();
    }

    /**
     * CREER ET AFFICHE UNE BOITE DE DIALOG POUR UNE INFORMATION
     * @param content
     */
    private  void createAndShowInformationDialog(String content)
    {
        Alert alertError=new Alert(Alert.AlertType.INFORMATION,content,ButtonType.OK);
        alertError.showAndWait();
    }

    /**
     * ACTIVE OU DESACTIVE LES BOUTONS ENTRER EN PARAMETRE
     * @param val
     * @param button
     */
    private void disabledButton(boolean val,Button...button)
    {
        for (int i=0;i<button.length;i++)
        {
            button[i].setDisable(val);
        }
    }
    /**
     * FONCTION PRINCIPALE QUI INITIALISE
     * TOUT LES PARAMETRES DE L'INTERFACE GRAPHIQUES
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}