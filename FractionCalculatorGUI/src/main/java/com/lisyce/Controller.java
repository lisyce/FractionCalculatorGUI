package com.lisyce;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//Singleton class
public class Controller {
    private final Stage window = new Stage();
    private Fraction frac1 = new Fraction("");
    private Fraction frac2 = new Fraction("");
    private Label output = new Label("");

    private final static Controller INSTANCE = new Controller();

    //private constructor
    private Controller() {
        window.setTitle("Fraction Calculator");
        this.output = new Label("");
        output.setAlignment(Pos.CENTER);
        output.setStyle("-fx-font-size: 15; -fx-border-color: black");
        output.setPadding(new Insets(5, 5, 5, 5));
    }

    //you can only get the single instance of the object
    public static Controller getSelf() {
        return INSTANCE;
    }

    public Label getOutput() {
        return this.output;
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
