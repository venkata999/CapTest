package com.ven;

public class ModOperator implements CalcOperator {
  public double performCalc(double firstNumber, double secondNumber) {
    return (firstNumber % secondNumber);
  }
}