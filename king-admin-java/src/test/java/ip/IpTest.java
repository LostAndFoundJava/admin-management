package ip;

import org.junit.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by chenjian on 2018/5/8.
 */
public class IpTest {
    @Test
    public void getHostIp() throws UnknownHostException, SocketException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        System.out.println(networkInterfaces);
    }
}
