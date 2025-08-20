package com.monitor.utility.memory;

import lombok.Data;

@Data
public class Memory {
    private long totalMemory;               
    private long availableMemory;         
    private long usedMemory;             
    private double usagePercent; 
}
