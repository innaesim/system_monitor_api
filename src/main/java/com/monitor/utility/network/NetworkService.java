package com.monitor.utility.network;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monitor.utility.commons.exceptions.BusinessRuntimeException;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

@Service
@Slf4j
public class NetworkService {
    List<NetworkIF> networkIFs;

    public NetworkService(){
        SystemInfo si = new SystemInfo();
        this.networkIFs = si.getHardware().getNetworkIFs();
    }
    
    public List<Network> getNetworkStats(){
        try {
            return networkIFs.stream().map(net -> {
                Network network = new Network();
                network.setInterfaceName(net.getName());
                network.setMacAddress(net.getMacaddr());
                network.setIpv4(String.join(", ", net.getIPv4addr()));
                network.setIpv6(String.join(", ", net.getIPv6addr()));
                network.setMtu(net.getMTU());
                network.setSpeed(net.getSpeed());
                network.setBytesReceived(net.getBytesRecv());
                network.setBytesSent(net.getBytesSent());
                network.setPacketsReceived(net.getPacketsRecv());
                network.setPacketsSent(net.getPacketsSent());
                network.setInErros(net.getInErrors());
                network.setOutErrors(net.getOutErrors());
                return network;
            }).toList();
        } catch (Exception e) {
            log.error("Error Retrieving Network Stats {}", e.getMessage());
            throw new BusinessRuntimeException("Failed to retrieve Network Stats");
        }
    }
}
