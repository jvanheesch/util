package com.github.jvanheesch.builder.vehicle.lvl1;

import com.github.jvanheesch.builder.vehicle.lvl2.BmwSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.BmwBeigeBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.BmwRedBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.BmwStationWagonBuilder;

public class BmwBuilder {
    public BmwSportsBuilder sports() {
        return new BmwSportsBuilder();
    }

    public BmwStationWagonBuilder stationWagon() {
        return new BmwStationWagonBuilder();
    }

    public BmwRedBuilder red() {
        return new BmwRedBuilder();
    }

    public BmwBeigeBuilder beige() {
        return new BmwBeigeBuilder();
    }
}
