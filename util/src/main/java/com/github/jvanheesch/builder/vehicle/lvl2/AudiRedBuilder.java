package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.AudiRedSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.AudiRedStationWagonBuilder;

public class AudiRedBuilder {
    public AudiRedSportsBuilder sports() {
        return new AudiRedSportsBuilder();
    }

    public AudiRedStationWagonBuilder station() {
        return new AudiRedStationWagonBuilder();
    }
}
