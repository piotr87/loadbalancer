package com.loadbalancer;

import com.loadbalancer.api.external.*;
import com.loadbalancer.core.*;
import java.util.*;
import static java.util.Arrays.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

public class LoadBalancerTest {

    @Test
    public void ensureThatHostHandleRequest() {
        //given
        List<Host> hosts = asList(mock(Host.class), mock(Host.class));
        LoadBalancer loadBalancer = new LoadBalancer(hosts, VariantType.SEQUENTIALLY_ROTATION);
        List<Request> requests = asList(mock(Request.class), mock(Request.class), mock(Request.class),mock(Request.class));

        //when
        requests.forEach(request -> loadBalancer.handleRequest(request));

        //then
        verify(hosts.get(0)).handleRequest(requests.get(0));
        verify(hosts.get(1)).handleRequest(requests.get(1));
        verify(hosts.get(0)).handleRequest(requests.get(2));
        verify(hosts.get(1)).handleRequest(requests.get(3));
    }

}
