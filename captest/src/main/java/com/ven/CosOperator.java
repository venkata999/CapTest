package com.ven;

public class CosOperator implements CalcOperator {
  public double performCalc(double firstNumber, double secondNumber) {
    return Math.cos(firstNumber + secondNumber);
  }
}