package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.AudiBeigeSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.BmwBeigeSportsBuilder;

public class BeigeSportsBuilder {
    public AudiBeigeSportsBuilder audi() {
        return new AudiBeigeSportsBuilder();
    }

    public BmwBeigeSportsBuilder bmw() {
        return new BmwBeigeSportsBuilder();
    }
}
