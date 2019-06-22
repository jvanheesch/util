package com.github.jvanheesch.builder.vehicle;

import com.github.jvanheesch.builder.vehicle.lvl1.*;

public class CarBuilder {
    public BmwBuilder bmw() {
        return new BmwBuilder();
    }

    public AudiBuilder audi() {
        return new AudiBuilder();
    }

    public SportsBuilder sports() {
        return new SportsBuilder();
    }

    public StationWagonBuilder stationWagon() {
        return new StationWagonBuilder();
    }

    public RedBuilder red() {
        return new RedBuilder();
    }

    public BeigeBuilder beige() {
        return new BeigeBuilder();
    }
}
