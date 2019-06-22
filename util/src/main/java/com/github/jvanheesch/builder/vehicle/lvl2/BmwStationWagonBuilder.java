package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.BmwBeigeStationWagonBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.BmwRedStationWagonBuilder;

public class BmwStationWagonBuilder {
    public BmwBeigeStationWagonBuilder beige() {
        return new BmwBeigeStationWagonBuilder();
    }

    public BmwRedStationWagonBuilder red() {
        return new BmwRedStationWagonBuilder();
    }
}
