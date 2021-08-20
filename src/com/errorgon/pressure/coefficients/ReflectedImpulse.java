package com.errorgon.pressure.coefficients;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.exceptions.OutOfRangeException;


// Table 2-8
// Used to calculate Reflected Impulse
public class ReflectedImpulse {

    private static double A = 0;
    private static double B = 0;
    private static double C = 0;
    private static double D = 0;
    private static double E = 0;
    private static double F = 0;
    private static double G = 0;

    public static double [] getCoefficients(Units units, double scaledDistance) {
        if (units.equals(Units.ENGLISH)) {
            setEnglishCoefficients(scaledDistance);
        } else if (units.equals(Units.METRIC)) {
            setMetricCoefficients(scaledDistance);
        }

        return new double[]{A, B, C, D, E, F, G};
    }

    private ReflectedImpulse() {

    }


    private static void setMetricCoefficients(double scaledDistance) {
        if (scaledDistance < 0.06) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 238) {
            A = 6.78536;
            B = -1.34654;
            C = 0.10105;
            D = -0.01123;
            E = 0.00000;
            F = 0.00000;
            G = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }

    private static void setEnglishCoefficients(double scaledDistance) {
        if (scaledDistance < 0.2) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 600) {
            A = 5.93130;
            B = -1.56220;
            C = 0.13220;
            D = -0.01123;
            E = 0.00000;
            F = 0.00000;
            G = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }


}

