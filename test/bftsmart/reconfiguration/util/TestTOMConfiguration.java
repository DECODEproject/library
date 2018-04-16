package bftsmart.reconfiguration.util;

import org.junit.Test;

import java.net.InetSocketAddress;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestTOMConfiguration {

    @Test
    public void default_config_has_socks_disabled() {
        TOMConfiguration config = new TOMConfiguration(33, "config");

        assertThat(config.isUseSocksProxy(), is(false));

        InetSocketAddress socketAddress = (InetSocketAddress) config.getSocksProxy();
        assertThat(socketAddress, is(nullValue()));
    }

    @Test
    public void can_enable_socks_proxy() {
        TOMConfiguration config = new TOMConfiguration(33, "test/config");

        assertThat(config.isUseSocksProxy(), is(true));

        InetSocketAddress socketAddress = (InetSocketAddress) config.getSocksProxy();
        assertThat(socketAddress, is(not(nullValue())));
        assertThat(socketAddress.getHostName(), is("localhost"));
        assertThat(socketAddress.getPort(), is(3456));
    }

    @Test
    public void server_ip_default() {
        TOMConfiguration config = new TOMConfiguration(33, "config");
        assertThat(config.getServerSocketIp(), is("127.0.0.1"));
    }

    @Test
    public void can_set_server_ip() {
        TOMConfiguration config = new TOMConfiguration(33, "test/config");
        assertThat(config.getServerSocketIp(), is("0.0.0.0"));
    }
}
