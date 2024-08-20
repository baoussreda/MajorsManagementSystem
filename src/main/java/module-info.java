module com.example.affectation {
    requires javafx.controls;
/*
    requires javafx.fxml;
*/
    requires javafx.web;
    requires mysql.connector.java;
    requires java.sql;


    opens com.example.affectation to javafx.fxml;
    exports com.example.affectation;
}