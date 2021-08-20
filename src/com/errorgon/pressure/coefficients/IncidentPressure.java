package com.errorgon.pressure.coefficients;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.exceptions.OutOfRangeException;

// Table 2-4
public class IncidentPressure {

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

    private IncidentPressure() {

    }


    private static void setMetricCoefficients(double scaledDistance) {
        if (scaledDistance < 0.2) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 2.9) {
            A = 7.21060;
            B = -2.10690;
            C = -0.32290;
            D = 0.11170;
            E = 0.06850;
            F = 0.0;
            G = 0.0;
        } else if (scaledDistance < 23.8) {
            A = 7.59345;
            B = -3.05133;
            C = 0.40908;
            D = 0.02633;
            E = -0.01270;
            F = 0.0;
            G = 0.0;
        } else if (scaledDistance < 198.5) {
            A = 6.05360;
            B = -1.40660;
            C = 0.0;
            D = 0.0;
            E = 0.0;
            F = 0.0;
            G = 0.0;
        } else {
            throw new OutOfRangeException();
        }
    }

    private static void setEnglishCoefficients(double scaledDistance) {
        if (scaledDistance < 0.5) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 7.25) {
            A = 6.91370;
            B = -1.43980;
            C = -0.28150;
            D = -0.14160;
            E = 0.06850;
            F = 0.0;
            G = 0.0;
        } else if (scaledDistance < 60) {
            A = 8.80350;
            B = -3.70010;
            C = 0.27090;
            D = 0.07330;
            E = -0.01270;
            F = 0.0;
            G = 0.0;
        } else if (scaledDistance < 500) {
            A = 5.42330;
            B = -1.40660;
            C = 0.0;
            D = 0.0;
            E = 0.0;
            F = 0.0;
            G = 0.0;
        } else {
            throw new OutOfRangeException();
        }
    }


}
