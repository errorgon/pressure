package com.errorgon.pressure;

import com.errorgon.pressure.enums.ExplosivesType;
import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.explosives.Explosive;
import com.errorgon.pressure.explosives.TNT;

public class Pressure {

    Explosive explosive;
    Units units;
    double netExplosiveWeight = 0;

    public Pressure() {
        this(Units.ENGLISH, new TNT());
    }

    public Pressure(Units units) {
        this.units = units;
    }

    public Pressure(Units units, Explosive explosive) {
        this.units = units;
        this.explosive = explosive;
    }

    public Pressure(Units units, Explosive explosive, double netExplosiveWeight) {
        this.units = units;
        this.explosive = explosive;
        this.netExplosiveWeight = netExplosiveWeight;
    }

    public void getImpulse() {

    }

    // Z = R/W^(1/3)
    public double getScaledDistance(double distance) {
        return (distance / Math.pow(netExplosiveWeight, (1.0 / 3.0)));
    }


}
