package com.wgxnl.entity;

import com.wgxnl._abstract.Transport;
import com.wgxnl.interfaces.EcoFriendly;

public class ElectricScooter extends Transport implements EcoFriendly {
    double batteryCapacity;

    public ElectricScooter(String model, double speed, double fuelConsumption, double batteryCapacity) {
        super(model, speed, fuelConsumption);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public double calculateRange() {
        return batteryCapacity * 5;
    }

    @Override
    public String toString() {
        return "ElectricScooter{" +
                "model='" + model + '\'' +
                ", speed=" + speed +
                ", fuelConsumption=" + fuelConsumption +
                ", batteryCapacity=" + batteryCapacity +
                '}';
    }


    @Override
    public void isEcoFriendly() {

    }
}
