package com.monitor.utility.process;

import lombok.Data;
import oshi.software.os.OSProcess.State;

@Data
public class Process {
    private int pid;
    private String name;
    private double cpu;
    private double memory;
    private long threads;
    private String user;
    private State state;
    private long upTime;
}
