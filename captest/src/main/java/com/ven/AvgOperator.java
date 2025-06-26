package com.ven;

public class AvgOperator implements CalcOperator {
  public double performCalc(double firstNumber, double secondNumber) {
    return (firstNumber + secondNumber) / 2;
  }
}