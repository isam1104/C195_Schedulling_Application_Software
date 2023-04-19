package com.example.c195;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Software II - Advanced Java Concepts - a GUI-based scheduling desktop application
 * This class creates an app that manages a database of customers and their appointments.
 * <p><b>
 * Lambdas found on line 184 of LogIn.java and on line 182 of AppointmentDaoImpl.java
 * </b></p>
 * After unzipping C195PROJECT, the Javadocs files can be found at /javadoc
 * @author Isam Elder
 */
public class Main extends Application {

    /**
     * Loads MainScreen.fxml to start the GUI and display the LogIn screen.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle(myBundle.getString("Title"));
        stage.setScene(scene);
        stage.show();
    }

    ResourceBundle myBundle = ResourceBundle.getBundle("/bundle/lang");

    /** This is the main method, which launches the database program.
     * @param args
     */
    public static void main(String[] args) {

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}