package com.lisyce;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EnterFraction {
    private final int BUTTON_SPACING = 5;
    private final int NUM_BUTTON_SIZE = 30;
    private final Fraction correspondingFraction;

    public EnterFraction(Fraction correspondingFraction) {
        this.correspondingFraction = correspondingFraction;
    }

    public VBox fractionEnterVBox(String bottomLabel) {
        VBox fractionEnterVBox = new VBox();
        fractionEnterVBox.setSpacing(BUTTON_SPACING);

        //label that displays the entered fraction once entered is clicked
        Label displayFractionLabel = new Label("");
        displayFractionLabel.setStyle("-fx-border-color: black");
        displayFractionLabel.setPrefSize((NUM_BUTTON_SIZE + BUTTON_SPACING) * 2 + NUM_BUTTON_SIZE, 30);
        displayFractionLabel.setPadding(new Insets(5, 10, 5, 10));
        displayFractionLabel.setAlignment(Pos.CENTER_RIGHT);


        //create the grid of numbers 1-9
        int currentLoopNum = 7;
        HBox buttonHBox = new HBox();
        buttonHBox.setSpacing(BUTTON_SPACING);
        for(int i=3; i>0; i--) {
            VBox buttonVBox = new VBox();
            buttonVBox.setSpacing(BUTTON_SPACING);
            for(int j=3; j>0; j--) {
                Button numButton = new Button(Integer.toString(currentLoopNum));
                numButton.setOnAction(e -> {
                    String addedChar = numButton.getText();
                    correspondingFraction.setStringFraction(correspondingFraction.getStringFraction() + addedChar);
                    displayFractionLabel.setText(correspondingFraction.getStringFraction());
                });
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
        Button backspaceButton = new Button("<-");
        Button[] specialButtonsArray = new Button[] {divideButton, zeroButton, backspaceButton};
        for (Button button : specialButtonsArray) {
            button.setPrefSize(NUM_BUTTON_SIZE, NUM_BUTTON_SIZE);
            button.setOnAction(e -> {
                if(!button.getText().equals(backspaceButton.getText())) {
                    //add chars to the string fraction if it's the 0 or the divide key
                    String addedChar = button.getText();
                    correspondingFraction.setStringFraction(correspondingFraction.getStringFraction() + addedChar);
                } else {
                    //handle presses of the backspace button
                    if (correspondingFraction.getStringFraction().length() > 0) {
                        String newStringFraction = correspondingFraction.getStringFraction().substring(0, correspondingFraction.getStringFraction().length() - 1);
                        correspondingFraction.setStringFraction(newStringFraction);
                    }
                }
                displayFractionLabel.setText(correspondingFraction.getStringFraction());
            });
            specialButtons.getChildren().add(button);
        }
        specialButtons.setSpacing(BUTTON_SPACING);

        //checks to make sure that the stringfraction isn't more than 12 characters long so it fits in the box


        //label that displays which fraction the buttons refer to
        Label footer = new Label(bottomLabel);
        footer.setStyle("-fx-font-size: 15");
        footer.setPrefWidth(specialButtons.getPrefWidth());

        //add all elements to the final vbox
        fractionEnterVBox.getChildren().addAll(displayFractionLabel, buttonHBox, specialButtons, footer);
        fractionEnterVBox.setAlignment(Pos.CENTER);

        return fractionEnterVBox;
    }

}
