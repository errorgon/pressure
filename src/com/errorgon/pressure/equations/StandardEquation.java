package com.errorgon.pressure.equations;

// Equation 2-22 S(x) = exp[A+B*ln(x) + C*ln2(x) + D*ln3(x) + E*ln4(x) + F*ln5(x) + G*ln6(x)]
public class StandardEquation {

    public static double calculate(double A, double B, double C, double D, double E, double F, double G, double x) {
        return Math.exp(A +
                B * Math.log(x) +
                C * Math.pow(Math.log(x), 2) +
                D * Math.pow(Math.log(x), 3) +
                E * Math.pow(Math.log(x), 4) +
                F * Math.pow(Math.log(x), 5) +
                G * Math.pow(Math.log(x), 6));
    }

}
