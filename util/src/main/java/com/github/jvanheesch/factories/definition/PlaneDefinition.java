package com.github.jvanheesch.factories.definition;

import com.github.jvanheesch.factories.domain.Plane;
import com.github.jvanheesch.factories.factory.VehicleFactory;

import java.util.Optional;

public class PlaneDefinition implements VehicleDefinition<Plane> {
    @Override
    public Optional<Plane> acceptFactory(VehicleFactory<Plane> vehicleFactory) {
        return vehicleFactory.construct(this);
    }
}
