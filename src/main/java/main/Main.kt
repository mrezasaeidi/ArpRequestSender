package main

import org.pcap4j.core.NotOpenException
import org.pcap4j.core.PcapHandle
import org.pcap4j.core.PcapNativeException
import org.pcap4j.core.Pcaps
import org.pcap4j.packet.ArpPacket
import org.pcap4j.packet.namednumber.ArpHardwareType
import org.pcap4j.packet.namednumber.ArpOperation
import org.pcap4j.packet.namednumber.EtherType
import org.pcap4j.util.MacAddress
import java.net.InetAddress
import java.net.UnknownHostException

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val networkInterface = Pcaps.findAllDevs()[0]
            val arpPacket = ArpPacket.Builder()
                    .srcProtocolAddr(networkInterface.addresses[0].address)
                    .srcHardwareAddr(MacAddress.getByAddress(networkInterface.linkLayerAddresses[0].address))
                    .dstProtocolAddr(InetAddress.getByName("192.168.1.1"))
                    .dstHardwareAddr(MacAddress.ETHER_BROADCAST_ADDRESS)
                    .hardwareType(ArpHardwareType.ETHERNET)
                    .operation(ArpOperation.REQUEST)
                    .protocolType(EtherType.IPV4)
                    .build()
            val pcapHandle = PcapHandle.Builder(networkInterface.name).build()
            pcapHandle.sendPacket(arpPacket)
        } catch (e: PcapNativeException) {
            e.printStackTrace()
        } catch (e: UnknownHostException) {
            e.printStackTrace()
        } catch (e: NotOpenException) {
            e.printStackTrace()
        }
    }
}