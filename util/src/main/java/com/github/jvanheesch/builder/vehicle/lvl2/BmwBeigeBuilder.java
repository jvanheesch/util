package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.BmwBeigeSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.BmwBeigeStationWagonBuilder;

public class BmwBeigeBuilder {
    public BmwBeigeSportsBuilder sports() {
        return new BmwBeigeSportsBuilder();
    }

    public BmwBeigeStationWagonBuilder station() {
        return new BmwBeigeStationWagonBuilder();
    }

}
