package com.github.jvanheesch.factories;

import com.github.jvanheesch.factories.definition.CarDefinition;
import com.github.jvanheesch.factories.definition.PlaneDefinition;
import com.github.jvanheesch.factories.domain.Car;
import com.github.jvanheesch.factories.domain.Plane;
import com.github.jvanheesch.factories.factory.CarFactory;
import com.github.jvanheesch.factories.factory.PlaneFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoriesTest {
    @Test
    void simpleTestThatVerifiesAllFactories() {
        CarDefinition carDefinition = new CarDefinition();
        PlaneDefinition planeDefinition = new PlaneDefinition();

        CarFactory carFactory = new CarFactory();
        PlaneFactory planeFactory = new PlaneFactory();

        assertThat(carFactory.construct(carDefinition))
                .isNotEmpty()
                .containsInstanceOf(Car.class);
        assertThat(carFactory.construct(planeDefinition))
                .isEmpty();
        assertThat(planeFactory.construct(carDefinition))
                .isEmpty();
        assertThat(planeFactory.construct(planeDefinition))
                .isNotEmpty()
                .containsInstanceOf(Plane.class);
    }
}
