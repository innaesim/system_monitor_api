package com.monitor.utility.network;

import lombok.Data;

@Data
public class Network {
    private String interfaceName;
    private String macAddress;
    private String ipv4;
    private String ipv6;
    private long mtu;
    private long speed;
    private long bytesReceived;
    private long bytesSent;
    private long packetsReceived;
    private long packetsSent;
    private long inErros;
    private long outErrors;
}
