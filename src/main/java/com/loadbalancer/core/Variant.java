package com.loadbalancer.core;

import com.loadbalancer.api.external.Host;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public abstract class Variant {

    protected final List<Host> hosts;

    public Variant(List<Host> hosts) {
        if(hosts == null || hosts.isEmpty()) {
            throw new RuntimeException("Hosts are not defined");
        }
        this.hosts = unmodifiableList(hosts);
    }

    public abstract Host getHost();
}
