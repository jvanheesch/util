package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.AudiRedSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.BmwRedSportsBuilder;

public class RedSportsBuilder {
    public AudiRedSportsBuilder audi() {
        return new AudiRedSportsBuilder();
    }

    public BmwRedSportsBuilder bmw() {
        return new BmwRedSportsBuilder();
    }
}
