package com.wgxnl._abstract;

public abstract class Transport {
    protected String model;
    protected double speed;
    protected double fuelConsumption;

    public abstract double calculateRange();

    public Transport(String model, double speed, double fuelConsumption) {
        this.model = model;
        this.speed = speed;
        this.fuelConsumption = fuelConsumption;  // Это поле доступно в дочернем классе
    }
}

