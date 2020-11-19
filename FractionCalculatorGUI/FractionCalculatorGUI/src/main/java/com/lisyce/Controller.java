package com.lisyce;

import javafx.stage.Stage;

public class Controller {
    private final Stage window = new Stage();
    private Fraction frac1;
    private Fraction frac2;

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

    public Fraction getFrac1() {
        return frac1;
    }

    public void setFrac1(Fraction frac1) {
        this.frac1 = frac1;
    }

    public Fraction getFrac2() {
        return frac2;
    }

    public void setFrac2(Fraction frac2) {
        this.frac2 = frac2;
    }
}
