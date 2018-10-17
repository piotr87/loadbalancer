package com.loadbalancer.variants;

import com.loadbalancer.api.external.*;
import com.loadbalancer.core.*;
import static com.loadbalancer.core.VariantType.*;
import com.loadbalancer.core.variants.*;
import static java.lang.Float.*;
import java.util.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;
import org.junit.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.mockito.Mockito.*;


public class LoadRotationVariantTest {

    @DisplayName("Ensure That Load Rotation Work Correctly")
    @ParameterizedTest(name = "For hosts with  [ {0} ; {1} ; {2} ] should be {3}")
    @CsvSource({
            "0.01F, 0.02F, 0.03F, 0.01F",
            "0.03F, 0.02F, 0.03F, 0.03F",
            "0.75F, 0.76F, 0.03F, 0.03F",
            "0.85F, 0.76F, 0.03F, 0.03F",
            "0.85F, 0.76F, 0.83F, 0.76F",
            "0.75F, 0.76F, 0.83F, 0.75F"
    })
    public void ensureThatGetHostWorkCorrectlyWhenHostsAreDefined(Float load1, Float load2, Float load3, Float expectedLoad) {
        //given
        List<Host> hosts = asList(host(load1), host(load2), host(load3));

        //when
        Host host = new LoadRotationVariant(hosts).getHost();

        //then
        assertEquals(expectedLoad, valueOf(host.getLoad()));
    }

    @Test
    public void ensureThatThrowRuntimeExceptionWhenHostsAreNotDefined() {
        //when
        Throwable exception = assertThrows(RuntimeException.class,
                ()->new LoadRotationVariant(emptyList()).getHost());

        //then
        assertEquals("Hosts are not defined", exception.getMessage());
    }

    private Host host(Float load) {
        Host host = mock(Host.class);
        when(host.getLoad()).thenReturn(load);
        return host;
    }
}
