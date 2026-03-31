package com.example.lab6;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class HelloController {
    @SuppressWarnings("unused")
    @FXML
    private Label welcomeText;

    @SuppressWarnings("unused")
    @FXML
    private Button statusButton;

    @SuppressWarnings("unused")
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Witam!");
    }

    @SuppressWarnings("unused")
    @FXML
    protected void onResetButtonClick() {
        welcomeText.setText("Kliknij przycisk, aby zmienic tekst i uruchomic animacje.");
        welcomeText.setTranslateX(0);
        welcomeText.setRotate(0);
    }

    @SuppressWarnings("unused")
    @FXML
    protected void onAnimateButtonClick() {
        TranslateTransition move = new TranslateTransition(Duration.seconds(1.2), welcomeText);
        move.setByX(120);
        move.setAutoReverse(true);
        move.setCycleCount(2);
        move.setInterpolator(Interpolator.EASE_BOTH);

        RotateTransition rotate = new RotateTransition(Duration.seconds(1.2), welcomeText);
        rotate.setByAngle(360);
        rotate.setAutoReverse(true);
        rotate.setCycleCount(2);
        rotate.setInterpolator(Interpolator.EASE_BOTH);

        new ParallelTransition(move, rotate).play();
        statusButton.setText("Animacja uruchomiona");
    }
}