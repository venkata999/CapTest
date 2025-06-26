package com.ven;

public class SinOperator implements CalcOperator {
  public double performCalc(double firstNumber, double secondNumber) {
    return Math.sin(firstNumber + secondNumber);
  }
}