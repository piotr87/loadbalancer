package com.loadbalancer.core;


import com.loadbalancer.api.external.Host;
import com.loadbalancer.core.variants.LoadRotationVariant;
import com.loadbalancer.core.variants.SequentiallyRotationVariant;

import java.util.List;
import java.util.function.Function;

public enum VariantType{
    SEQUENTIALLY_ROTATION(hosts -> new SequentiallyRotationVariant(hosts)),
    LOAD_ROTATION(hosts -> new LoadRotationVariant(hosts));

    private final Function<List<Host>, Variant> function;

    VariantType(Function<List<Host>, Variant> function) {
        this.function = function;
    }

    public Variant apply(List<Host> hosts) {
        return function.apply(hosts);
    }
}
