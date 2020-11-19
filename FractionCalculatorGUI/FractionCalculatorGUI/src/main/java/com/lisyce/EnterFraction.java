package com.lisyce;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class EnterFraction {
    private final int BUTTON_SPACING = 5;
    private final int NUM_BUTTON_SIZE = 25;
    private String displayFraction;

    public EnterFraction() {

    }

    public VBox fractionEnterVBox(String bottomLabel) {
        VBox fractionEnterVBox = new VBox();
        fractionEnterVBox.setSpacing(BUTTON_SPACING);

        //create the grid of numbers 1-9
        int currentLoopNum = 7;
        HBox buttonHBox = new HBox();
        buttonHBox.setSpacing(BUTTON_SPACING);
        for(int i=3; i>0; i--) {
            VBox buttonVBox = new VBox();
            buttonVBox.setSpacing(BUTTON_SPACING);
            for(int j=3; j>0; j--) {
                Button numButton = new Button(Integer.toString(currentLoopNum));
                numButton.setPrefSize(NUM_BUTTON_SIZE, NUM_BUTTON_SIZE);
                buttonVBox.getChildren().add(numButton);
                currentLoopNum -= 3;
            }
            buttonHBox.getChildren().addAll(buttonVBox);
            currentLoopNum += 10;
        }

        //add the row of special buttons
        HBox specialButtons = new HBox();
        Button divideButton = new Button("/");
        Button zeroButton = new Button("0");
        Button enterButton = new Button("enter");
        Button[] specialButtonsArray = new Button[] {divideButton, zeroButton, enterButton};
        for (Button button : specialButtonsArray) {
            if (!button.getText().equals(enterButton.getText())){
                button.setPrefSize(NUM_BUTTON_SIZE, NUM_BUTTON_SIZE);
            }
            specialButtons.getChildren().add(button);
        }
        specialButtons.setSpacing(BUTTON_SPACING);
        specialButtons.setPrefWidth((NUM_BUTTON_SIZE + BUTTON_SPACING) * 3 + 20);

        //label that displays the entered fraction once entered is clicked
        Label displayFractionLabel = new Label(displayFraction);
        displayFractionLabel.setStyle("-fx-border-color: black");
        displayFractionLabel.setPrefSize(specialButtons.getPrefWidth(), 30);
        displayFractionLabel.setPadding(new Insets(5, 10, 5, 10));
        displayFractionLabel.setAlignment(Pos.CENTER_RIGHT);

        //label that displays which fraction the buttons refer to
        Label footer = new Label(bottomLabel);
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("-fx-font-size: 15");
        footer.setPrefWidth(specialButtons.getPrefWidth());

        //add all elements to the final vbox
        fractionEnterVBox.getChildren().addAll(displayFractionLabel, buttonHBox, specialButtons, footer);

        return fractionEnterVBox;
    }

}
