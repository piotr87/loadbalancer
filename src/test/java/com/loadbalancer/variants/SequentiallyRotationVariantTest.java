package com.loadbalancer.variants;

import com.loadbalancer.api.external.*;
import com.loadbalancer.core.*;
import com.loadbalancer.core.variants.*;
import java.util.*;
import static java.util.Arrays.*;
import static java.util.Collections.emptyList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SequentiallyRotationVariantTest {

    @Test
    public void ensureThatWorkCorrectlyWhenHostsAreDefined() {
        //given
        List<Host> hosts = asList(host(), host(), host(), host(), host(), host());
        List<Host> result = new ArrayList<>();
        Variant sequentiallyVariant = new SequentiallyRotationVariant(hosts);
        int hostSize = hosts.size();

        //when
        for(int i=0; i< 100; i++){
            result.add(sequentiallyVariant.getHost());
        }

        //then
        assertEquals(hosts, result.subList(0, hostSize));
        assertEquals(hosts, result.subList(hostSize, hostSize*2));
        assertEquals(hosts, result.subList(hostSize*4, hostSize*5));
    }

    @Test
    public void ensureThatThrowRuntimeExceptionWhenHostsAreNotDefined() {
        //when
        Throwable exception = assertThrows(RuntimeException.class,
                ()->new SequentiallyRotationVariant(emptyList()).getHost());

        //then
        assertEquals("Hosts are not defined", exception.getMessage());
    }

    private Host host(){
        return mock(Host.class);
    }

}
