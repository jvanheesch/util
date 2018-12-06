package com.github.jvanheesch.factories.factory;

import com.github.jvanheesch.factories.definition.CarDefinition;
import com.github.jvanheesch.factories.definition.PlaneDefinition;
import com.github.jvanheesch.factories.domain.Vehicle;

import java.util.Optional;

/**
 * To allow for double dispatch, this interface should define a method
 * Optional<V> construct(SomeVehicleDefinition)
 * for each SomeVehicleDefinition that implements VehicleDefinition.
 * This breaks the open-closed principle, which is a known consequence of using the visitor pattern.
 * With default methods, the opening is limited to this interface,
 * its implementing classes remain unaffected.
 */
public interface VehicleFactory<V extends Vehicle> {
    default Optional<V> construct(CarDefinition carDefinition) {
        return Optional.empty();
    }

    default Optional<V> construct(PlaneDefinition planeDefinition) {
        return Optional.empty();
    }
}
