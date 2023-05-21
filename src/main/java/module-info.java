module com.example.medbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires kernel;
    requires layout;
    requires io;


    opens com.example.medbd to javafx.fxml;
    exports com.example.medbd;
    exports com.example.medbd.controllers;
    opens com.example.medbd.controllers to javafx.fxml;
    opens com.example.medbd.models to javafx.base;


}