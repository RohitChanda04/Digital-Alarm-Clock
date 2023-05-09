package com.example.digitalalarmclock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private MediaPlayer mediaPlayer;
    private String defaultAlarmTone = "MorningAlarm.mp3";
    @Override
    public void start(Stage stage) throws Exception {

        File fxmlFile = new File("src/main/resources/com.example.digitalalarmclock/hello-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlFile.toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Digital Alarm Clock");
        stage.setScene(scene);
        stage.show();

        Button off = new Button("Stop Alarm");
        off.setPrefWidth(70);
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/com.example.digitalalarmclock/MorningAlarm.mp3").toURI().toString()));

        Controller controller = fxmlLoader.getController();
        controller.setOffButton(off);
        controller.setMediaPlayer(mediaPlayer);

    }

    public static void main(String[] args) {
        launch();
    }

}