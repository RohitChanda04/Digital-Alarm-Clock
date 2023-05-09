package com.example.digitalalarmclock;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    Time time = new Time(new CurrentTime().currentTime());
    //Time time = new Time("17:45:15");

    @FXML
    private Text timer;
    @FXML
    private TextField alarm;
    //@FXML
    //private Text banner;
    @FXML
    private Button off;
    private List<String> alarmTones = Arrays.asList("MorningAlarm", "CalmAlarmSound", "MakeItBig", "MotivationalAlarm", "SoftAlarm", "StillDreInstrumental");
    @FXML
    private ComboBox<String> alarmToneComboBox;
    private MediaPlayer mediaPlayer;
    private boolean flag = false;

    public void setOffButton(Button off) {
        this.off = off;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void playAlarmTone(String selectedPreviousTone) {
        System.out.println("**************************");
        System.out.println("value from button: "+selectedPreviousTone);
        //String tonePath = "src/main/resources/com.example.digitalalarmclock/MorningAlarm.mp3";
        switch (selectedPreviousTone) {
            case "CalmAlarmSound":
                selectedPreviousTone = "src/main/resources/com.example.digitalalarmclock/CalmAlarmSound.mp3";
                break;
            case "MakeItBig":
                selectedPreviousTone = "src/main/resources/com.example.digitalalarmclock/MakeItBig.mp3";
                break;
            case "MotivationalAlarm":
                selectedPreviousTone = "src/main/resources/com.example.digitalalarmclock/MotivationalAlarm.mp3";
                break;
            case "SoftAlarm":
                selectedPreviousTone = "src/main/resources/com.example.digitalalarmclock/SoftAlarm.mp3";
                break;
            case "StillDreInstrumental":
                selectedPreviousTone = "src/main/resources/com.example.digitalalarmclock/StillDreInstrumental.mp3";
                break;
        }
        System.out.println("12345 ^^^ 000 ----- "+selectedPreviousTone);
        Media tone = new Media(new File(selectedPreviousTone).toURI().toString());
        stopMediaPlayer();
        this.mediaPlayer = new MediaPlayer(tone);
    }

    private void stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //alarm.setPromptText("Set an alarm");

        alarmToneComboBox.getItems().addAll(alarmTones);

        Media tone = new Media(new File("src/main/resources/com.example.digitalalarmclock/MorningAlarm.mp3").toURI().toString());
        stopMediaPlayer();
        this.mediaPlayer = new MediaPlayer(tone);

        // Adding a listener to the ComboBox
        alarmToneComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Stopping the currently playing audio
            mediaPlayer.stop();

            // Loading and playing the selected audio file
            mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/com.example.digitalalarmclock/"+newValue+".mp3").toURI().toString()));
            mediaPlayer.play();
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            if (time.getCurrentTime().equals(alarm.getText())) {
                                System.out.println("ALARM!!!");
                                mediaPlayer.play();
                            }
                            time.oneSecondPassed();
                            timer.setText(time.getCurrentTime());
                        }
                )
        );
        off.setOnAction(e -> {
            mediaPlayer.stop();
            off.setText("Stop Alarm");
        });
        timer.setText(time.getCurrentTime());
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}