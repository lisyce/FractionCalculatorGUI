package com.lisyce;

public class Fraction {
    private int numerator;
    private int denominator;
    private boolean valid;
    private String stringFraction = "";

    public Fraction(String stringFraction) {
        this.stringFraction = stringFraction;
        try {
            this.numerator = numeratorFromString(stringFraction);
            this.denominator = denomFromString(stringFraction);
            this.valid = true;
        } catch(Exception e){
            this.valid = false;
        }
    }

    public String getStringFraction() {
        return stringFraction;
    }

    public void setStringFraction(String stringFraction) {
        this.stringFraction = stringFraction;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int newNumerator) {
        numerator = newNumerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int newDenominator) {
        denominator = newDenominator;
    }

    public boolean getValid() {
        return valid;
    }

    private int denomFromString(String fraction){
        int slashIndex = fraction.indexOf('/');
        return Integer.parseInt(fraction.substring(slashIndex + 1));
    }

    private int numeratorFromString(String fraction){
        int slashIndex = fraction.indexOf('/');
        return Integer.parseInt(fraction.substring(0, slashIndex));
    }

}