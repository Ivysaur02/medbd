module com.example.medbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires kernel;
    requires layout;
    requires io;


    exports com.example.medbd.PDFCreate;
    exports com.example.medbd.models;




    opens com.example.medbd to javafx.fxml;
    exports com.example.medbd;
    exports com.example.medbd.controllers;
    exports com.example.medbd.BdConnection;
    opens com.example.medbd.controllers to javafx.fxml;
    opens com.example.medbd.models to javafx.base;


}