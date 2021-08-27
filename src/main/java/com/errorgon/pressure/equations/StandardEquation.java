package com.errorgon.pressure.equations;

// Equation 2-22 S(x) = exp[A+B*ln(x) + C*ln2(x) + D*ln3(x) + E*ln4(x) + F*ln5(x) + G*ln6(x)]
public class StandardEquation {

    public static double solve(double[] m, double x) {
        return Math.exp(m[0] +
                m[1] * Math.log(x) +
                m[2] * Math.pow(Math.log(x), 2.0) +
                m[3] * Math.pow(Math.log(x), 3.0) +
                m[4] * Math.pow(Math.log(x), 4.0) +
                m[5] * Math.pow(Math.log(x), 5.0) +
                m[6] * Math.pow(Math.log(x), 6.0) +
                m[7] * Math.pow(Math.log(x), 7.0));
    }

}
