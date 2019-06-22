package com.github.jvanheesch.builder.vehicle.lvl1;

import com.github.jvanheesch.builder.vehicle.lvl2.BmwSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.AudiSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.BeigeSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl2.RedSportsBuilder;

public class SportsBuilder {
    public BmwSportsBuilder bmw() {
        return new BmwSportsBuilder();
    }

    public AudiSportsBuilder audi() {
        return new AudiSportsBuilder();
    }

    public RedSportsBuilder red() {
        return new RedSportsBuilder();
    }

    public BeigeSportsBuilder beige() {
        return new BeigeSportsBuilder();
    }
}
