package com.wgxnl.entity;

import com.wgxnl._abstract.Transport;

public class Car extends Transport {
    int tankCapacity;

    public Car(String model, double speed, double fuelConsumption, int tankCapacity) {
        super(model, speed, fuelConsumption);
        this.tankCapacity = tankCapacity;
    }

    @Override
    public double calculateRange() {
        return tankCapacity * 100 / fuelConsumption;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", speed=" + speed +
                ", fuelConsumption=" + fuelConsumption +
                ", tankCapacity=" + tankCapacity +
                '}';
    }
}
