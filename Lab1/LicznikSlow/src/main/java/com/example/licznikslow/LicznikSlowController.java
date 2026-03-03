package com.example.licznikslow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class LicznikSlowController {
    @FXML
    private TextField textField;

    @FXML
    private Label resultLabel;

    @FXML
    public void initialize() {
        // Enter też działa jak OK
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onOkButtonClick();
            }
        });
    }

    @FXML
    protected void onOkButtonClick() {
        String text = textField.getText();

        // Zliczamy znaki
        int charCount = text.length();

        // Zliczamy słowa (rozdzielamy po białych znakach)
        int wordCount = 0;
        if (!text.trim().isEmpty()) {
            String[] words = text.trim().split("\\s+");
            wordCount = words.length;
        }

        // Pokazujemy wynik
        resultLabel.setText("Liczba znaków: " + charCount + "\nLiczba słów: " + wordCount);
    }
}

