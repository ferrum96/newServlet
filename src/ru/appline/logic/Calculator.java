package ru.appline.logic;

import java.io.Serializable;

public class Calculator implements Serializable {

    private double a;
    private double b;
    private String math;
    private double result;


    public Calculator(double a, double b, String math) {
        this.a = a;
        this.b = b;
        this.math = math;
    }

    public double getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public double getResult() {
        switch (this.math) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
        return result;
    }

    public String toString() {
        return "{ result: " + getResult() + " }";
    }


}
