package com.monitor.utility.websocket;

import org.springframework.stereotype.Service;

import com.monitor.utility.commons.Statistics;
import com.monitor.utility.commons.exceptions.BusinessRuntimeException;
import com.monitor.utility.cpu.CpuService;
import com.monitor.utility.disk.DiskService;
import com.monitor.utility.memory.MemoryService;
import com.monitor.utility.network.NetworkService;
import com.monitor.utility.process.ProcessService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
public class SocketService {
    private final CpuService cpuService;
    private final DiskService diskService;
    private final MemoryService memoryService;
    private final NetworkService networkService;
    private final ProcessService processService;

    public Statistics getAllStatistics(){
        try {
            Statistics stats = new Statistics();
            stats.setCpu(cpuService.getCpuStats());
            stats.setDisks(diskService.getDiskStats());
            stats.setInterfaces(networkService.getNetworkStats());
            stats.setMemory(memoryService.getMemoryStats());
            stats.setProcesses(processService.getProcessStats());
            return stats;
        } catch (Exception e) {
            log.error("Error retrieving all stats {}", e.getMessage());
            throw new BusinessRuntimeException("Failed to retrieve Usage Statistics");
        }
    }
}
