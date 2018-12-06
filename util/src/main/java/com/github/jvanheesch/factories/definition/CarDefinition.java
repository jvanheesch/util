package com.github.jvanheesch.factories.definition;

import com.github.jvanheesch.factories.domain.Car;
import com.github.jvanheesch.factories.factory.VehicleFactory;

import java.util.Optional;

public class CarDefinition implements VehicleDefinition<Car> {
    @Override
    public Optional<Car> acceptFactory(VehicleFactory<Car> vehicleFactory) {
        return vehicleFactory.construct(this);
    }
}
