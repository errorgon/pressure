package com.errorgon.pressure.coefficients;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.exceptions.OutOfRangeException;


// Table 2-10
// Used to calculate Dynamic Impulse
public class DynamicImpulse {

    private static double A = 0;
    private static double B = 0;
    private static double C = 0;
    private static double D = 0;
    private static double E = 0;
    private static double F = 0;
    private static double G = 0;
    private static double H = 0;

    public static double [] getCoefficients(Units units, double scaledDistance) {
        if (units.equals(Units.ENGLISH)) {
            setEnglishCoefficients(scaledDistance);
        } else if (units.equals(Units.METRIC)) {
            setMetricCoefficients(scaledDistance);
        }

        return new double[]{A, B, C, D, E, F, G, H};
    }

    private DynamicImpulse() {

    }


    private static void setMetricCoefficients(double scaledDistance) {
        if (scaledDistance < 0.2) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 2.4) {
            A = 5.22050;
            B = -2.22740;
            C = 1.54220;
            D = 1.31490;
            E = -2.24000;
            F = -1.63160;
            G = 0.85529;
            H = 0.58457;
        } else if (scaledDistance < 59.5) {
            A = 2.33461;
            B = 11.31770;
            C = -20.14010;
            D = 14.36423;
            E = -5.38957;
            F = 1.02839;
            G = -0.07877;
            H = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }

    private static void setEnglishCoefficients(double scaledDistance) {
        if (scaledDistance < 0.5) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 6) {
            A = 5.02669;
            B = -1.49770;
            C = 0.382073;
            D = -2.91667;
            E = 0.0986917;
            F = 4.11776;
            G = -2.92807;
            H = 0.584571;
        } else if (scaledDistance < 150) {
            A = -43.57590;
            B = 106.51300;
            C = -96.61740;
            D = 44.33270;
            E = -11.15370;
            F = 1.46535;
            G = -0.0787675;
            H = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }


}

