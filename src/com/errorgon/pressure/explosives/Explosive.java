package com.errorgon.pressure.explosives;

public interface Explosive {
    public double getDensity();
    public double getEquivWeightPressure();
    public double getEquivWeightImpulse();
    public Double[] pressureRange();
}
