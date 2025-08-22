package com.monitor.utility.commons;

import java.util.List;

import com.monitor.utility.cpu.Cpu;
import com.monitor.utility.disk.Disk;
import com.monitor.utility.memory.Memory;
import com.monitor.utility.network.Network;
import com.monitor.utility.process.Process;

import lombok.Data;

@Data
public class Statistics {
    private Cpu cpu;
    private List<Disk> disks;
    private Memory memory;
    private List<Network> interfaces;
    private List<Process> processes; 
}
