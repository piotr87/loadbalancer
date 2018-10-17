package com.loadbalancer.api.external;

public interface Host {

    float getLoad();

    void handleRequest(Request request);

}
