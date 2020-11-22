package com.lisyce;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application{

    Controller controller = Controller.getSelf();
    Scene mainScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage = controller.getWindow();

        VBox mainVBox = new VBox(45);
        mainVBox.setAlignment(Pos.CENTER);

        //set up the title at the top of the screen
        Label titleLabel = new Label("Fraction Calculator");
        titleLabel.setStyle("-fx-font-size: 25");

        HBox mainHBox = new HBox(50);
        mainHBox.setAlignment(Pos.TOP_CENTER);

        //create the operator buttons and error message screen
        Button addBtn = new Button("+");
        Button subBtn = new Button("-");
        Button multBtn = new Button("*");
        Button divBtn = new Button("\u00F7");
        Button[] operatorBtns = new Button[] {divBtn, multBtn, subBtn, addBtn};
        GridPane operatorHolder = new GridPane();
        operatorHolder.setHgap(5);
        operatorHolder.setVgap(5);
        operatorHolder.setAlignment(Pos.CENTER);
        int looper = operatorBtns.length - 1;
        for (int i=1; i <= 2; i++) {
            for(int j=1; j<=2; j++) {
                operatorBtns[looper].setPrefSize(45, 45);
                operatorBtns[looper].setStyle("-fx-font-size: 20; -fx-font-weight: bold");
                operatorHolder.add(operatorBtns[looper], i, j);
                looper --;
            }
        }

        //main operator vbox
        VBox operatorBox = new VBox(5, operatorHolder);
        operatorBox.setAlignment(Pos.CENTER);
        operatorBox.setPrefWidth(150);

        //output label for answers and error messages
        Controller.getSelf().getOutput().setPrefSize(operatorBox.getPrefWidth(), 40);

        operatorBox.getChildren().add(Controller.getSelf().getOutput());

        //TODO make the operator buttons handle events
        addBtn.setOnAction(e -> {
            Controller.getSelf().getOutput().setText(add(Controller.getSelf().getFrac1(), Controller.getSelf().getFrac2()));
        });

        //add the two fraction button sets that corresponding to the fractions from the controller
        mainHBox.getChildren().addAll(new EnterFraction(Controller.getSelf().getFrac1()).fractionEnterVBox("Fraction 1"),
                new EnterFraction(Controller.getSelf().getFrac2()).fractionEnterVBox("Fraction 2"), operatorBox);
        mainVBox.getChildren().addAll(titleLabel, mainHBox);
        mainScene = new Scene(mainVBox, controller.getWINDOW_WIDTH(), controller.getWINDOW_HEIGHT());
        stage.setScene(mainScene);
        stage.show();
    }

    public static String divide(Fraction num1, Fraction num2) {

        if(num1.getDenominator() == 0 || num2.getDenominator() == 0) return "Cannot divide by 0";

        int num2Numerator = num2.getNumerator();
        num2.setNumerator(num2.getDenominator());
        num2.setDenominator(num2Numerator);
        return multiply(num1, num2);
    }

    public static String multiply(Fraction num1, Fraction num2) {
        Fraction copyNum1 = new Fraction(num1.getStringFraction());
        Fraction copyNum2 = new Fraction(num2.getStringFraction());

        if(copyNum1.getDenominator() == 0 || copyNum2.getDenominator() == 0) return "Cannot divide by 0";

        int totalNumerator = copyNum1.getNumerator() * copyNum2.getNumerator();
        int commonDenominator = leastCommonMultiple(copyNum1.getDenominator(), copyNum2.getDenominator());
        if(copyNum1.getDenominator() == copyNum2.getDenominator() && copyNum1.getDenominator() % totalNumerator != 0) commonDenominator = copyNum1.getDenominator() * copyNum2.getDenominator();

        return totalNumerator + "/" + commonDenominator;
    }

    public static String subtract(Fraction num1, Fraction num2) {

        if(num1.getDenominator() == 0 || num2.getDenominator() == 0) return "Cannot divide by 0";

        num2.setNumerator(num2.getNumerator() * -1);
        return add(num1, num2);
    }

    public static String add(Fraction num1, Fraction num2){
        Fraction copyNum1 = new Fraction(num1.getStringFraction());
        Fraction copyNum2 = new Fraction(num2.getStringFraction());

        if(copyNum1.getDenominator() == 0 || copyNum2.getDenominator() == 0) return "Cannot divide by 0";

        int commonDenominator = leastCommonMultiple(copyNum1.getDenominator(), copyNum2.getDenominator());

        copyNum1.setNumerator(copyNum1.getNumerator() * (commonDenominator/copyNum1.getDenominator()));
        copyNum2.setNumerator(copyNum2.getNumerator() * (commonDenominator/copyNum2.getDenominator()));
        int totalNumerator = copyNum1.getNumerator() + copyNum2.getNumerator();
        return (simplify(new Fraction(totalNumerator + "/" + commonDenominator)));
    }

    public static int leastCommonMultiple(int num1, int num2) {
        int lcd = num1 * num2;
        for (int i=lcd-1; i>1; i--){
            if(lcd % i == 0 && (num1 % i == 0 && num2 % i == 0)) {
                lcd = lcd / i;
                break;
            }
        }
        return lcd;
    }

    public static int simplifiedDenom(int num1, int num2) {
        double greatestNum = Math.max(num1, num2);
        for (double i = Math.ceil(greatestNum/2); i>1; i--) {
            if(num1 % i == 0 && num2 % i == 0) {
                return (int) i;
            }
        }
        return num1 * num2;
    }

    public static String simplify(Fraction fraction) {
        if(fraction.getNumerator() % fraction.getDenominator() == 0) {
            return Integer.toString(fraction.getNumerator() / fraction.getDenominator());
        } else if (fraction.getNumerator() > fraction.getDenominator()) {
            int remainder = fraction.getNumerator() % fraction.getDenominator();
            int wholeNum = (fraction.getNumerator() - remainder) / fraction.getDenominator();
            fraction.setNumerator(fraction.getNumerator() - (wholeNum * fraction.getDenominator()));
            return (wholeNum + " " + fraction.getNumerator() + "/" + fraction.getDenominator());
        } else if (!(simplifiedDenom(fraction.getNumerator(), fraction.getDenominator()) == fraction.getNumerator() * fraction.getDenominator())) {
            int denom = simplifiedDenom(fraction.getNumerator(), fraction.getDenominator());
            return(fraction.getNumerator()/denom + "/" + fraction.getDenominator()/denom);
        }

        return fraction.getStringFraction();
    }


}
