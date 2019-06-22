package com.github.jvanheesch.builder.vehicle.lvl1;

import com.github.jvanheesch.builder.vehicle.lvl2.BmwStationWagonBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.AudiStationWagonBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.BeigeStationWagonBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.RedStationWagonBuilder;

public class StationWagonBuilder {
    public BmwStationWagonBuilder bmw() {
        return new BmwStationWagonBuilder();
    }

    public AudiStationWagonBuilder audi() {
        return new AudiStationWagonBuilder();
    }

    public RedStationWagonBuilder red() {
        return new RedStationWagonBuilder();
    }

    public BeigeStationWagonBuilder beige() {
        return new BeigeStationWagonBuilder();
    }
}
