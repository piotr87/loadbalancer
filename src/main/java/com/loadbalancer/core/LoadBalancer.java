package com.loadbalancer.core;

import com.loadbalancer.api.external.Host;
import com.loadbalancer.api.external.Request;

import java.util.List;


public class LoadBalancer {

    private List<Host> hosts;
    private Variant variant;

    public LoadBalancer(List<Host> hosts, VariantType variantType) {
        this.hosts = hosts;
        this.variant = variantType.apply(hosts);
    }

    public void handleRequest(Request request) {
        variant.getHost().handleRequest(request);
    }

}
