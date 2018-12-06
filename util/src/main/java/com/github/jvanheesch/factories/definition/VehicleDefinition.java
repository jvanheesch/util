package com.github.jvanheesch.factories.definition;

import com.github.jvanheesch.factories.domain.Vehicle;
import com.github.jvanheesch.factories.factory.VehicleFactory;

import java.util.Optional;

public interface VehicleDefinition<V extends Vehicle> {
    Optional<V> acceptFactory(VehicleFactory<V> vehicleFactory);
}
