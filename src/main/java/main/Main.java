package main;

import org.pcap4j.core.*;
import org.pcap4j.util.MacAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        try {
            PcapNetworkInterface networkInterface = Pcaps.findAllDevs().get(0);
            ArpRequest.send(networkInterface,
                    networkInterface.getAddresses().get(0).getAddress(),
                    MacAddress.getByAddress(networkInterface.getLinkLayerAddresses().get(0).getAddress()),
                    InetAddress.getByName("192.168.1.1")
            );
        } catch (PcapNativeException | UnknownHostException e) {
            e.printStackTrace();
        }

//        try {
//            PcapNetworkInterface networkInterface = Pcaps.findAllDevs().get(0);
//            ArpPacket arpPacket = new ArpPacket.Builder()
//                    .srcProtocolAddr(networkInterface.getAddresses().get(0).getAddress())
//                    .srcHardwareAddr(MacAddress.getByAddress(networkInterface.getLinkLayerAddresses().get(0).getAddress()))
//                    .dstProtocolAddr(InetAddress.getByName("192.168.1.1"))
//                    .dstHardwareAddr(MacAddress.ETHER_BROADCAST_ADDRESS)
//                    .hardwareType(ArpHardwareType.ETHERNET)
//                    .operation(ArpOperation.REQUEST)
//                    .protocolType(EtherType.IPV4)
//                    .build();
//            new PcapHandle.Builder(networkInterface.getName()).build().sendPacket(arpPacket);
//        } catch (PcapNativeException | UnknownHostException | NotOpenException e) {
//            e.printStackTrace();
//        }
    }
}
