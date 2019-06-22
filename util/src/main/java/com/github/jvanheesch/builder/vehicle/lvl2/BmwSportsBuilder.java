package com.github.jvanheesch.builder.vehicle.lvl2;

import com.github.jvanheesch.builder.vehicle.lvl3.BmwBeigeSportsBuilder;
import com.github.jvanheesch.builder.vehicle.lvl3.BmwRedSportsBuilder;

public class BmwSportsBuilder {
    public BmwBeigeSportsBuilder beige() {
        return new BmwBeigeSportsBuilder();
    }

    public BmwRedSportsBuilder red() {
        return new BmwRedSportsBuilder();
    }
}
