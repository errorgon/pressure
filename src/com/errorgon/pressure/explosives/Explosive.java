package com.errorgon.pressure.explosives;


import com.errorgon.pressure.enums.PES;

public interface Explosive {
    double equivImpulse = 1.0;
    double equivPressure = 1.0;

    Double[] pressureRange();
    double getDensity();
    double getEquivWeightPressure();
    double getEquivWeightImpulse();

    // Equations 2-4 and 2-6
    double getTNTPressureEquivalent(PES pes, double weight);

    // Equations 2-5 and 2-7
    double getTNTImpulseEquivalent(PES pes, double weight);

}
