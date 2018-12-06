package com.github.jvanheesch.factories.factory;

import com.github.jvanheesch.factories.definition.PlaneDefinition;
import com.github.jvanheesch.factories.domain.Plane;

import java.util.Optional;

public class PlaneFactory implements VehicleFactory<Plane> {
    @Override
    public Optional<Plane> construct(PlaneDefinition planeDefinition) {
        return Optional.of(new Plane(planeDefinition));
    }
}
