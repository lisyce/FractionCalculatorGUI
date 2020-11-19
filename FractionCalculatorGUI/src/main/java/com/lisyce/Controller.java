package com.lisyce;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {
    private final Stage window = new Stage();

    public Controller() {
        window.setTitle("Fraction Calculator");

    }

    public Stage getWindow() {
        return window;
    }

    public int getWINDOW_WIDTH() {
        return 600;
    }

    public int getWINDOW_HEIGHT() {
        return 600;
    }
}
