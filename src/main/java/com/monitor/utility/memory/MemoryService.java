package com.monitor.utility.memory;

import org.springframework.stereotype.Service;

import com.monitor.utility.commons.exceptions.BusinessRuntimeException;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

@Service
@Slf4j
public class MemoryService {
    private final GlobalMemory memory;

    public MemoryService(){
        SystemInfo si = new SystemInfo();
        this.memory = si.getHardware().getMemory();
    }
    
    public Memory getMemoryStats(){
        try {
            Memory mem = new Memory();
            mem.setTotalMemory(memory.getTotal());
            mem.setAvailableMemory(memory.getAvailable());
            mem.setUsedMemory(mem.getTotalMemory() - mem.getAvailableMemory());
            mem.setUsagePercent((mem.getUsedMemory() * 100d) / mem.getTotalMemory());
            return mem;
        } catch (Exception e) {
            log.error("Error Retrieving Memory Stats {}", e.getMessage());
            throw new BusinessRuntimeException("Failed to retrieve Memory Stats");
        }
    }
}
