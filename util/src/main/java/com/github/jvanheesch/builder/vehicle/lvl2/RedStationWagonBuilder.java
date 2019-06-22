package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.AudiRedStationWagonBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.BmwRedStationWagonBuilder;

public class RedStationWagonBuilder {
    public AudiRedStationWagonBuilder audi() {
        return new AudiRedStationWagonBuilder();
    }

    public BmwRedStationWagonBuilder bmw() {
        return new BmwRedStationWagonBuilder();
    }
}
