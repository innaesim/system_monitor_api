package com.monitor.utility.disk;

import lombok.Data;

@Data
public class Disk {

    private String name;               
    private String mountPoint;         
    private String filesystemType;     
    private long totalSpace;           
    private long usedSpace;            
    private long freeSpace;            
    private double usagePercent;       
    private long readBytes;            
    private long writeBytes;           
    private long readCount;            
    private long writeCount;   
}
