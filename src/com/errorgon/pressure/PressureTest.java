package com.errorgon.pressure;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.explosives.TNT;
import org.junit.jupiter.api.Test;

class PressureTest {

    //
    @Test
    public void incidentOverpressureTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new TNT(), 10.0);
        System.out.println(pressure.getIncidentOverpressure(pressure.getScaledDistance(100)));
    }

}