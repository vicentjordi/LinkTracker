package com.jordivicent.linktracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LinkTracker extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LinkTracker.class.getResource("FXMLMainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 575, 450);
        stage.setTitle("LinkTracker!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}