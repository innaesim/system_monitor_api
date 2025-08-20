package com.monitor.utility.cpu;

import java.util.List;
import lombok.Data;

@Data
public class Cpu {

    private double systemLoad;         
    private double processLoad;        
    private int availableProcessors;   
    private double cpuUsagePercent;    
    private double userUsagePercent;   
    private double systemUsagePercent; 
    private double idlePercent;        
    private List<Double> perCoreUsage;
}
