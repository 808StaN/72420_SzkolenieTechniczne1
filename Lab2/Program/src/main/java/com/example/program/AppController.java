package com.example.program;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class AppController {

    @FXML private Label lInfo;

    @FXML private RadioButton rbNiebieski;
    @FXML private RadioButton rbZielony;
    @FXML private RadioButton rbCzerwony;

    @FXML private RadioButton rb1;
    @FXML private RadioButton rb2;
    @FXML private RadioButton rb3;

    @FXML private RadioButton rbImg1;
    @FXML private RadioButton rbImg2;
    @FXML private RadioButton rbImg3;

    @FXML private ImageView imageView;

    @FXML
    public void initialize() {
        // Domyślny kolor etykiety
        lInfo.setTextFill(Color.BLUE);

        // ToggleGroup dla kolorów
        ToggleGroup tgKolor = new ToggleGroup();
        rbNiebieski.setToggleGroup(tgKolor);
        rbZielony.setToggleGroup(tgKolor);
        rbCzerwony.setToggleGroup(tgKolor);

        // ToggleGroup dla numerów
        ToggleGroup tgNumer = new ToggleGroup();
        rb1.setToggleGroup(tgNumer);
        rb2.setToggleGroup(tgNumer);
        rb3.setToggleGroup(tgNumer);

        // ToggleGroup dla obrazków
        ToggleGroup tgImg = new ToggleGroup();
        rbImg1.setToggleGroup(tgImg);
        rbImg2.setToggleGroup(tgImg);
        rbImg3.setToggleGroup(tgImg);
    }

    // --- Kolory ---
    @FXML
    private void onKolorNiebieski() {
        lInfo.setTextFill(Color.BLUE);
    }

    @FXML
    private void onKolorZielony() {
        lInfo.setTextFill(Color.GREEN);
    }

    @FXML
    private void onKolorCzerwony() {
        lInfo.setTextFill(Color.RED);
    }

    // --- Numery ---
    @FXML
    private void onNumer1() {
        lInfo.setText("=1");
    }

    @FXML
    private void onNumer2() {
        lInfo.setText("=2");
    }

    @FXML
    private void onNumer3() {
        lInfo.setText("=3");
    }

    // --- Obrazki (Tab2) ---
    @FXML
    private void showImage1() {
        loadImage("img1.jpg");
    }

    @FXML
    private void showImage2() {
        loadImage("img2.jpg");
    }

    @FXML
    private void showImage3() {
        loadImage("img3.jpg");
    }

    private void loadImage(String fileName) {
        var stream = getClass().getResourceAsStream(fileName);
        if (stream == null) {
            stream = getClass().getResourceAsStream("/com/example/program/" + fileName);
        }
        if (stream != null) {
            try {
                Image img = new Image(stream);
                imageView.setImage(img);
            } catch (Exception e) {
                System.err.println("Błąd ładowania obrazka: " + fileName + " -> " + e.getMessage());
            }
        } else {
            System.err.println("Nie znaleziono obrazka: " + fileName);
        }
    }

    // --- Menu ---
    @FXML
    private void onExit() {
        Platform.exit();
    }

    @FXML
    private void onItem1() {
        lInfo.setText("Item1 kliknięty");
    }

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O programie");
        alert.setHeaderText("Laboratorium 2");
        alert.setContentText("Aplikacja JavaFX - Szkolenie Techniczne 1");
        alert.showAndWait();
    }
}

