package com.loadbalancer.core.variants;


import com.loadbalancer.api.external.Host;
import com.loadbalancer.core.Variant;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class SequentiallyRotationVariant extends Variant {

    private final AtomicLong index = new AtomicLong(0);

    public SequentiallyRotationVariant(List<Host> hosts) {
        super(hosts);
    }

    @Override
    public Host getHost() {
        long index = getIndex();
        return hosts.get((int) index % hosts.size() );
    }

    private long getIndex() {
        while(true) {
            long existingValue = index.get();
            long newValue = existingValue == hosts.size() ? 1 : existingValue + 1;
            if(index.compareAndSet(existingValue, newValue)) {
                return newValue - 1 ;
            }
        }
    }
}
