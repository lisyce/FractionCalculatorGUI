package com.lisyce;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application{

    Controller controller = new Controller();
    Scene mainScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage = controller.getWindow();

        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setSpacing(45);

        //set up the title at the top of the screen
        Label titleLabel = new Label("Fraction Calculator");
        titleLabel.setStyle("-fx-font-size: 25");

        HBox mainHBox = new HBox();
        mainHBox.setAlignment(Pos.CENTER);
        mainHBox.setSpacing(30);
        mainHBox.getChildren().addAll(new EnterFraction().fractionEnterVBox("Fraction 1"), new EnterFraction().fractionEnterVBox("Fraction 2"));

        mainVBox.getChildren().addAll(titleLabel, mainHBox);
        mainScene = new Scene(mainVBox, controller.getWINDOW_WIDTH(), controller.getWINDOW_HEIGHT());
        stage.setScene(mainScene);
        stage.show();
    }



}
