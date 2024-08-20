package com.example.affectation;

import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.geometry.Insets;
//import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.example.affectation.DBConnect.connect;


public class Main extends Application {

    //main
    public static void main(String[] args) {


        launch(args);

    }

    //mon stage
    @Override
    public void start(Stage arg0) {


        Choice info = new Choice("info");
        Choice gtr = new Choice("gtr");
        Choice proc = new Choice("proc");
        Choice ind = new Choice("ind");

        //tableau dynamique pour tester le remplissage du tableview
        ArrayList<Student> list_students = new ArrayList<>();
        list_students.add(new Student(15, "Studentne 0", info, gtr, ind, proc));
        list_students.add(new Student(17.31, "Studentne 1", ind, info, gtr, proc));
        list_students.add(new Student(18.4, "Studentne 2", info, gtr, proc, ind));
        list_students.add(new Student(14.1, "Studentne 3", ind, info, proc, gtr));
        list_students.add(new Student(15.5, "Studentne 4", info, ind, gtr, proc));
        list_students.add(new Student(10.2, "Studentne 5", info, ind, gtr, proc));
        list_students.add(new Student(12.1, "Studentne 6", ind, info, proc, gtr));
        list_students.add(new Student(11.12, "Studentne 7", info, ind, proc, gtr));
        list_students.add(new Student(13.21, "Studentne 8", ind, info, gtr, proc));
        list_students.add(new Student(16.5, "Studentne 9", info, gtr, ind, proc));
        list_students.add(new Student(13.12, "Studentne 10", info, proc, ind, gtr));

        //trier les étudiants par rapport à leurs notes
        Collections.sort(list_students, new SortbyNote().reversed());


       /* ici affecter une filière par rapport à la note et les places restant disponibles, pas encore implémenté au tableview
        mais il marche en testant avec la méthode printdetailsaffectation */

        for (Student s : list_students) {

            if (s.getChoice1().getMax_capacity() > 0) {
                s.setAffectation(s.getChoice1());
                s.getChoice1().dec_max();
            } else if (s.getChoice2().getMax_capacity() > 0) {
                s.setAffectation(s.getChoice2());
                s.getChoice2().dec_max();
            } else if (s.getChoice3().getMax_capacity() > 0) {
                s.setAffectation(s.getChoice3());
                s.getChoice3().dec_max();

            } else if (s.getChoice4().getMax_capacity() > 0) {
                s.setAffectation(s.getChoice4());
                s.getChoice4().dec_max();

            }

        }

        //construire notre stage
        arg0.setTitle("Gestion des choix de filière");

        HBox HBox1 = new HBox();
        HBox1.setPadding(new Insets(10));
        HBox1.setSpacing(10);
        Label labelNom = new Label("Étudiant:");
        labelNom.setPadding(new Insets(5));
        TextField textNote = new TextField();
        textNote.setPromptText("Note");

        TextField textNom = new TextField();
        textNom.setPromptText("Nom");

        TextField textchoix1 = new TextField();
        textchoix1.setPromptText("choix1");

        TextField textchoix2 = new TextField();
        textchoix2.setPromptText("choix2");

        TextField textchoix3 = new TextField();
        textchoix3.setPromptText("choix3");

        TextField textchoix4 = new TextField();
        textchoix4.setPromptText("choix4");


        Button ButtonAdd = new Button("Ajouter");
        HBox1.getChildren().addAll(labelNom, textNote, textNom, textchoix1, textchoix2, textchoix3, textchoix4, ButtonAdd);
        BorderPane borderPaneRoot = new BorderPane();
        Scene scene = new Scene(borderPaneRoot, 800, 600);
        borderPaneRoot.setTop(HBox1);
        arg0.setScene(scene);

        //tableview
        TableView<Student> table = new TableView<>();
        table.setEditable(true);
        TableColumn<Student, Integer> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        TableColumn<Student, String> fullNameColumn = new TableColumn<>("Full Name");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Choice> choice1Column = new TableColumn<>("Choice 1");
        choice1Column.setCellValueFactory(new PropertyValueFactory<>("choice1"));

        TableColumn<Student, Choice> choice2Column = new TableColumn<>("Choice 2");
        choice2Column.setCellValueFactory(new PropertyValueFactory<>("choice2"));

        TableColumn<Student, Choice> choice3Column = new TableColumn<>("Choice 3");
        choice3Column.setCellValueFactory(new PropertyValueFactory<>("choice3"));

        TableColumn<Student, Choice> choice4Column = new TableColumn<>("Choice 4");
        choice4Column.setCellValueFactory(new PropertyValueFactory<>("choice4"));

        TableColumn<Student, Choice> affectColumn = new TableColumn<>("Affectation");
        choice4Column.setCellValueFactory(new PropertyValueFactory<>("affectation"));

/*
        table.setItems(ObservableList);
*/
        table.getColumns().add(noteColumn);
        table.getColumns().add(fullNameColumn);
        table.getColumns().add(choice1Column);
        table.getColumns().add(choice2Column);
        table.getColumns().add(choice3Column);
        table.getColumns().add(choice4Column);
        table.getColumns().add(affectColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //insérer mon tableau dynamique au tableView
        for (Student a : list_students) {
            table.getItems().addAll(a);

        }

        //Boutton ajouter pour ajouter les informations d'un étudiant
        ButtonAdd.setOnAction(actionEvent -> {

            //--------------------------------

            /*ici mon problème setOnAction n'arrive pas à remplir le tableview avec les données provenant des TextFields
            saisie par l'utilisateur, à savoir (textNote,textNom,textchoix1....) le résultats ajoutant juste avec des zeros au niveau de noteColumn
            probablement une question de type de données*/

            int nextIndex = table.getSelectionModel().getSelectedIndex() + 1;
            table.getItems().add(nextIndex, new Student(Double.parseDouble(textNote.getText()),
                    textNom.getText(),
                    textchoix1.getText(),
                    textchoix2.getText(),
                    textchoix3.getText(),
                    textchoix4.getText()));
            table.getSelectionModel().select(nextIndex);


            //----------------------------------------------

            /*ici les données provenant des textFields en cliquant le boutton ajouter arrivent à accéder avec succès
            à la base de données qui s'appelle university et students le nom de mon tableau(base de données)*/


            String sql = "INSERT INTO university.students(Note, `Full Name`, `Choice 1`, `Choice 2`, `Choice 3`, `Choice 4`) VALUES (?,?,?,?,?,?) ";
            PreparedStatement pst;
            try {
                pst = connect().prepareStatement(sql);
                pst.setDouble(1, Double.parseDouble(textNote.getText()));
                pst.setString(2, textNom.getText());
                pst.setString(3, textchoix1.getText());
                pst.setString(4, textchoix2.getText());
                pst.setString(5, textchoix3.getText());
                pst.setString(6, textchoix4.getText());
                pst.execute();
                pst.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        borderPaneRoot.setCenter(table);

        //c'est pour éliminer OnFocus dès l'exécution
        Platform.runLater(HBox1::requestFocus);

        arg0.show();
        //l'icône
        arg0.getIcons().add(new Image("file:icon.png"));


    }


}
                                                                    //BAOUSSOUS Reda