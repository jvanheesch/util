package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.AudiBeigeStationWagonBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.AudiRedStationWagonBuilder;

public class AudiStationWagonBuilder {
    public AudiBeigeStationWagonBuilder beige() {
        return new AudiBeigeStationWagonBuilder();
    }

    public AudiRedStationWagonBuilder red() {
        return new AudiRedStationWagonBuilder();
    }
}
