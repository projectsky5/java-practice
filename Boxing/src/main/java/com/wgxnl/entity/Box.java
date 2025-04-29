package com.wgxnl.entity;

import java.util.Objects;

public class Box {
    double weight;
    double volume;

    public Box(double weight, double volume) {
        this.weight = weight;
        this.volume = volume;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box box)) return false;
        return Double.compare(weight, box.weight) == 0 && Double.compare(volume, box.volume) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, volume);
    }

    @Override
    public String toString() {
        return "- Box"+
                "{вес=" + String.format("%.1f", weight) + " кг" +
                ", объем=" + String.format("%.1f", volume) + " л}";
    }
}
