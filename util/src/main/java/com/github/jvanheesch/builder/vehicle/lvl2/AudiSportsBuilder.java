package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.AudiBeigeSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.AudiRedSportsBuilder;

public class AudiSportsBuilder {
    public AudiBeigeSportsBuilder beige() {
        return new AudiBeigeSportsBuilder();
    }

    public AudiRedSportsBuilder red() {
        return new AudiRedSportsBuilder();
    }
}
