package com.loadbalancer.core.variants;


import com.loadbalancer.api.external.Host;
import com.loadbalancer.core.Variant;

import java.util.List;

public class LoadRotationVariant extends Variant {

    private static final Float LOAD_ROTATION_BORDER = 0.75F;

    public LoadRotationVariant(List<Host> hosts) {
        super(hosts);
    }

    @Override
    public Host getHost() {
        return hosts.stream()
                .filter(h -> h.getLoad() < LOAD_ROTATION_BORDER)
                .findFirst()
                .orElse(
                        hosts.stream()
                                .min((h1, h2) -> Float.compare(h1.getLoad(), h2.getLoad()))
                                .get()
                );
    }
}
