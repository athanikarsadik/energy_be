package com.energy.exception;

public class EnergyConsumptionNotFoundException extends RuntimeException {
    public EnergyConsumptionNotFoundException(String message) {
        super(message);
    }
}