package com.ven;

public class SqrtOperator implements CalcOperator {
    public double performCalc(double firstNumber, double secondNumber) {
        return Math.sqrt(firstNumber + secondNumber);
    }
}
