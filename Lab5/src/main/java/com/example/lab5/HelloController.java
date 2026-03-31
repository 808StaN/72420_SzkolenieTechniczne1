package com.example.lab5;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HelloController {
    @FXML
    private TextArea outputArea;

    @FXML
    protected void onRunTasksClick() {
        outputArea.setText(Zadania.buildReport());
    }
}