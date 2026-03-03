package com.example.lab1;

import javafx.fxml.FXML;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        HelloApplication.setRoot("primary");
    }
}


