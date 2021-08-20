package com.errorgon.pressure;

import com.errorgon.pressure.coefficients.IncidentOverpressure;
import com.errorgon.pressure.enums.ExplosivesType;
import com.errorgon.pressure.enums.MunitionType;
import com.errorgon.pressure.enums.PES;
import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.equations.StandardEquation;
import com.errorgon.pressure.explosives.Explosive;
import com.errorgon.pressure.explosives.TNT;

public class Pressure {

    // Inputs
    PES pes;
    MunitionType munitionType;
    Explosive explosive;
    Units units;
    double netExplosiveWeight = 0;
    double distance;
    double temperature;
    double altitude;



    double scaledDistance;

    // Default to English Units, 1lb, 1ft
    public Pressure(Explosive explosive) {
        this(Units.ENGLISH, explosive);
    }

    public Pressure(Units units, Explosive explosive) {
        this(units, explosive, 1.0);
    }

    public Pressure(Units units, Explosive explosive, double netExplosiveWeight) {
        this(units, explosive, 1.0, 1.0);
    }

    public Pressure(double netExplosiveWeight, double distance) {
        this(Units.ENGLISH, new TNT(), netExplosiveWeight, distance);
    }

    public Pressure(Units units, Explosive explosive, double netExplosiveWeight, double distance) {
        this(units, explosive, netExplosiveWeight, distance, 59.0, 0.0);
    }

    public Pressure(Units units, Explosive explosive, double netExplosiveWeight, double distance, double temperature, double altitude) {
        this.units = units;
        this.explosive = explosive;
        this.netExplosiveWeight = netExplosiveWeight;
        this.distance = distance;
        this.scaledDistance = getScaledDistance(distance);
        this.temperature = temperature;
        this.altitude = altitude;
    }

    public void setExplosive(Explosive explosive) { this.explosive = explosive; }
    public Explosive getExplosive() { return explosive; }

    // Z = R/W^(1/3)
    private double getScaledDistance(double distance) {
        return (distance / Math.pow(netExplosiveWeight, (1.0 / 3.0)));
    }

    public double getIncidentOverpressure(double scaledDistance) {
        IncidentOverpressure op = new IncidentOverpressure(Units.ENGLISH, scaledDistance);
        return  StandardEquation.calculate(op.A, op.B, op.C, op.D, op.E, op.F, op.G, scaledDistance);
    }

    public double getIncidentOverpressure() {
        IncidentOverpressure op = new IncidentOverpressure(units, scaledDistance);
        return StandardEquation.calculate(op.A, op.B, op.C, op.D, op.E, op.F, op.G, scaledDistance);
    }


}
