package com.errorgon.pressure.explosives;


import com.errorgon.pressure.enums.PES;

public class TNT implements Explosive{

    private static final double density = 0.0588919;
    private static final double equivPressure = 1.0;
    private static final double equivImpulse = 1.0;

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public double getEquivWeightPressure() {
        return equivPressure;
    }

    @Override
    public double getEquivWeightImpulse() {
        return equivImpulse;
    }

    @Override
    public double getTNTPressureEquivalent(PES pes, double weight) {
        if (pes.equals(PES.OPEN_STORAGE_STANDARD)) {
            return equivPressure * weight; // Equation 2-4
        } else {
            return weight; // Equation 2-6
        }
    }

    @Override
    public double getTNTImpulseEquivalent(PES pes, double weight) {
        if (pes.equals(PES.OPEN_STORAGE_STANDARD)) {
            return equivImpulse * weight; // Equation 2-5
        } else {
            return weight; // Equation 2-7
        }
    }

    @Override
    public Double[] pressureRange() {
        return null;
    }

}
