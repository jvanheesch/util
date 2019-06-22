package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.AudiBeigeSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.AudiBeigeStationWagonBuilder;

public class AudiBeigeBuilder {
    public AudiBeigeSportsBuilder sports() {
        return new AudiBeigeSportsBuilder();
    }

    public AudiBeigeStationWagonBuilder station() {
        return new AudiBeigeStationWagonBuilder();
    }
}
