package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.BmwRedSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.BmwRedStationWagonBuilder;

public class BmwRedBuilder {
    public BmwRedSportsBuilder sports() {
        return new BmwRedSportsBuilder();
    }

    public BmwRedStationWagonBuilder station() {
        return new BmwRedStationWagonBuilder();
    }
}
