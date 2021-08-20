package com.errorgon.pressure;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.explosives.TNT;
import org.junit.jupiter.api.Test;

class PressureTest {

    @Test
    public void scaledDistanceTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new TNT(), 100.0);
        System.out.println(pressure.getScaledDistance(100));
    }

}