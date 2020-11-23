package com.lisyce;

public class Fraction{
    private int numerator;
    private int denominator;
    private String stringFraction;

    public Fraction(String stringFraction) {
        this.stringFraction = stringFraction;
        setValues();

    }

    private void setValues() {
        try {
            this.numerator = numeratorFromString(stringFraction);
            this.denominator = denomFromString(stringFraction);
        } catch(Exception ignored){}
        if(this.stringFraction.equals("")) {
            this.numerator = 0;
            this.denominator = 1;
        }
    }

    public String getStringFraction() {
        return stringFraction;
    }

    public void setStringFraction(String stringFraction) {
        this.stringFraction = stringFraction;
        setValues();
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

    private int denomFromString(String fraction){
        int slashIndex = fraction.indexOf('/');
        if (slashIndex == -1 || slashIndex == fraction.length() - 1) {
            return 1;
        } else {
            return Integer.parseInt(fraction.substring(slashIndex + 1));
        }
    }

    private int numeratorFromString(String fraction){
        int slashIndex = fraction.indexOf('/');
        if(slashIndex == -1){
            return Integer.parseInt(fraction);
        }
        return Integer.parseInt(fraction.substring(0, slashIndex));
    }

}