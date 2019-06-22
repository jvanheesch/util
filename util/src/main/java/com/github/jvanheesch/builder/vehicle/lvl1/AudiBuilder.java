package com.github.jvanheesch.builder.vehicle.lvl1;

import com.github.jvanheesch.builder.vehicle.lvl2.AudiBeigeBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.AudiRedBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.AudiSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.AudiStationWagonBuilder;

public class AudiBuilder {
    public AudiSportsBuilder sports() {
        return new AudiSportsBuilder();
    }

    public AudiStationWagonBuilder stationWagon() {
        return new AudiStationWagonBuilder();
    }

    public AudiRedBuilder red() {
        return new AudiRedBuilder();
    }

    public AudiBeigeBuilder beige() {
        return new AudiBeigeBuilder();
    }
}
