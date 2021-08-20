package com.errorgon.pressure.explosives;

public class TNT implements Explosive{

    @Override
    public double getDensity() {
        return 0.0588919;
    }

    @Override
    public double getEquivWeightPressure() {
        return 1.0;
    }

    @Override
    public double getEquivWeightImpulse() {
        return 1.0;
    }

    @Override
    public Double[] pressureRange() {
        return null;
    }


}
