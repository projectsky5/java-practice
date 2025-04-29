package com.wgxnl;

import com.wgxnl._abstract.Transport;
import com.wgxnl.entity.Car;
import com.wgxnl.entity.ElectricScooter;
import com.wgxnl.interfaces.EcoFriendly;

import java.util.Arrays;
import java.util.List;

public class ApplicationRunner {
    public static void main(String[] args) {
        List<Transport> transports = List.of(
                new Car("BMW", 170, 25, 40),
                new Car("Mercedes", 150, 15, 45),
                new Car("Audi", 130, 20, 35),
                new ElectricScooter("Scooter1", 25, 1, 10),
                new ElectricScooter("Scooter2", 30, 2, 14),
                new ElectricScooter("Scooter3", 35, 3, 15)
        );

        printCars(transports);
        printElectricScooters(transports);
        printEcologic(transports);


    }

    private static void printCars(List<Transport> transports) {
        System.out.println("Машины: ");
        for (Transport transport : transports) {
            if(transport instanceof Car){
                System.out.println(transport);
            }
        }
    }
    private static void printElectricScooters(List<Transport> transports) {
        System.out.println("Электрические скутеры: ");
        for (Transport transport : transports) {
            if(transport.getClass()==ElectricScooter.class){
                System.out.println(transport);
            }
        }
    }
    private static void printEcologic(List<Transport> transports) {
        System.out.println("Eco friendly:");
        for (Transport transport : transports) {
            if(transport instanceof EcoFriendly)
                System.out.println(transport);
            }
    }
}

