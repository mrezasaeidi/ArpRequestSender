package main

import org.pcap4j.core.Pcaps
import org.pcap4j.util.MacAddress
import java.net.InetAddress

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val networkInterface = Pcaps.findAllDevs()[0]
        ArpRequest.send(networkInterface,
                networkInterface.addresses[0].address,
                MacAddress.getByAddress(networkInterface.linkLayerAddresses[0].address),
                InetAddress.getByName("192.168.1.1")
        )

//        try {
//            val arpPacket = ArpPacket.Builder()
//                    .srcProtocolAddr(networkInterface.addresses[0].address)
//                    .srcHardwareAddr(MacAddress.getByAddress(networkInterface.linkLayerAddresses[0].address))
//                    .dstProtocolAddr(InetAddress.getByName("192.168.1.1"))
//                    .dstHardwareAddr(MacAddress.ETHER_BROADCAST_ADDRESS)
//                    .hardwareType(ArpHardwareType.ETHERNET)
//                    .operation(ArpOperation.REQUEST)
//                    .protocolType(EtherType.IPV4)
//                    .build()
//            val pcapHandle = PcapHandle.Builder(networkInterface.name).build()
//            pcapHandle.sendPacket(arpPacket)
//        } catch (e: PcapNativeException) {
//            e.printStackTrace()
//        } catch (e: UnknownHostException) {
//            e.printStackTrace()
//        } catch (e: NotOpenException) {
//            e.printStackTrace()
//        }
    }
}