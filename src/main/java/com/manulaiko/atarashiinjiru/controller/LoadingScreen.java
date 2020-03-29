package com.manulaiko.atarashiinjiru.controller;

import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class LoadingScreen {
    @FXML
    private void initialize() {
        System.out.println("Loaded!");
    }
}
