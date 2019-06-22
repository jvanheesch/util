package com.github.jvanheesch.builder.vehicle.lvl1;

import com.github.jvanheesch.builder.vehicle.lvl2.AudiRedBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.RedSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.RedStationWagonBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.BmwRedBuilder;

public class RedBuilder {
    public BmwRedBuilder bmw() {
        return new BmwRedBuilder();
    }

    public AudiRedBuilder audi() {
        return new AudiRedBuilder();
    }

    public RedSportsBuilder sports() {
        return new RedSportsBuilder();
    }

    public RedStationWagonBuilder stationWagon() {
        return new RedStationWagonBuilder();
    }
}
