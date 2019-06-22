package com.github.jvanheesch.builder.vehicle.lvl1;

import com.github.jvanheesch.builder.vehicle.lvl2.AudiBeigeBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.BeigeSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.BeigeStationWagonBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.BmwBeigeBuilder;

public class BeigeBuilder {
    public BmwBeigeBuilder bmw() {
        return new BmwBeigeBuilder();
    }

    public AudiBeigeBuilder audi() {
        return new AudiBeigeBuilder();
    }

    public BeigeSportsBuilder sports() {
        return new BeigeSportsBuilder();
    }

    public BeigeStationWagonBuilder stationWagon() {
        return new BeigeStationWagonBuilder();
    }
}
