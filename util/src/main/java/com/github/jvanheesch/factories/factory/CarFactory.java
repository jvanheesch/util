package com.github.jvanheesch.factories.factory;

import com.github.jvanheesch.factories.definition.CarDefinition;
import com.github.jvanheesch.factories.domain.Car;

import java.util.Optional;

public class CarFactory implements VehicleFactory<Car> {
    @Override
    public Optional<Car> construct(CarDefinition carDefinition) {
        return Optional.of(new Car(carDefinition));
    }
}
