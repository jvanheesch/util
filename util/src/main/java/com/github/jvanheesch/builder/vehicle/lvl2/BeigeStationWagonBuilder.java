package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.AudiBeigeStationWagonBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.BmwBeigeStationWagonBuilder;

public class BeigeStationWagonBuilder {
    public AudiBeigeStationWagonBuilder audi() {
        return new AudiBeigeStationWagonBuilder();
    }

    public BmwBeigeStationWagonBuilder bmw() {
        return new BmwBeigeStationWagonBuilder();
    }
}
