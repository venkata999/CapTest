package com.ven;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ven.exception.PolishNotationException;

public class PolishNotationCalculator {
    static Map<String, CalcOperator> symbolOperatorMap = new HashMap<String, CalcOperator>();
    static final String SQRT = "sqrt";
    static final String COS = "cos";
    static final String SIN = "sin";
    static final String MOD = "mod";
    static final String AVG = "avg";
    static final String PLUS = "+";
    static final String MINUS = "-";
    static final String MUTIPLY = "*";
    static final String DIVIDE = "/";
    static {
        symbolOperatorMap.put(PLUS, new PlusOperator());
        symbolOperatorMap.put(MINUS, new MinusOperator());
        symbolOperatorMap.put(MUTIPLY, new MultiplyOperator());
        symbolOperatorMap.put(DIVIDE, new DivideOperator());
        symbolOperatorMap.put(SQRT, new SqrtOperator());
        symbolOperatorMap.put(SIN, new SinOperator());
        symbolOperatorMap.put(COS, new CosOperator());
        symbolOperatorMap.put(MOD, new ModOperator());
        symbolOperatorMap.put(AVG, new AvgOperator());
    }

    public static void main(String[] args) throws PolishNotationException {
        /* Input from user */
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println("You entered string " + str);
        sc.close();
        rpnCalculate(str);
    }

    /**
     * <p>
     * This method calculate elements in the string based on instructions
     * </p>
     * 
     * @param a string of numbers and instruction Operators
     * @return total number in Double
     * @throws PolishNotationException
     */

    public static double rpnCalculate(String rpnStr) throws PolishNotationException {

        /* convert string into list of strings of numbers and operators */
        List<String> ls = Arrays.asList(rpnStr.split(" "));

        double total = 0;
        for (int i = 0; i < ls.size(); i++) {
            /* skip all except sqrt,cos,sin */
            if (symbolOperatorMap.containsKey(ls.get(i))
                    && !(ls.get(i).equals(SQRT) || ls.get(i).equals(SIN) || ls.get(i).equals(COS))) {
                continue;
            }

            /* check operator at position 3 */
            if (i == 0) {
                if (!symbolOperatorMap.containsKey(ls.get(i + 2))) {
                    break;
                }
                total = calculate(ls.get(i + 2), toDouble(ls.get(i)), toDouble(ls.get(i + 1)));
                i++;
            }
            /* check operator at last position for sqrt,cos,sin */
            else if (ls.get(i).equals(SQRT) || ls.get(i).equals(COS) || ls.get(i).equals(SIN)) {
                total = calculate(ls.get(i), total, 0);
            } else {
                total = calculate(ls.get(i + 1), total, toDouble(ls.get(i)));
            }
        }
        System.out.println("Total is: " + total);
        return total;
    }

    /**
     * <p>
     * This method calculate value of parametrs based on given operator
     * </p>
     * 
     * @param a string operators
     * @param a double number
     * @param a double number
     * @return calculated number in Double
     */

    public static double calculate(String operator, double first, double second) {
        return symbolOperatorMap.get(operator).performCalc(first, second);
    }

    /**
     * <p>
     * This method calculate value of double
     * </p>
     * 
     * @param a string of double
     * @return convert string to Double
     * @throws PolishNotationException
     */

    public static double toDouble(String doubleStr) throws PolishNotationException {
        double dbl = 0;
        try {
            dbl = Double.valueOf(doubleStr);
        } catch (NumberFormatException ne) {
            throw new PolishNotationException("User entered incorrect number");
        }
        return dbl;
    }
}