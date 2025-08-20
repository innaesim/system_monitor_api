package com.monitor.utility.process;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monitor.utility.commons.exceptions.BusinessRuntimeException;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

@Service
@Slf4j
public class ProcessService {
    private final OperatingSystem os;
    private final SystemInfo si;

    public ProcessService(){
        this.si = new SystemInfo();
        this.os = si.getOperatingSystem();
    }
    
    public List<Process> getProcessStats(){
        try {
            return os.getProcesses().stream().map(proc -> {
                Process process = new Process();
                process.setPid(proc.getProcessID());
                process.setName(proc.getName());
                process.setCpu(100d * (proc.getKernelTime() + proc.getUserTime()) / proc.getUpTime());
                process.setMemory(100d * proc.getResidentSetSize() / si.getHardware().getMemory().getTotal());
                process.setThreads(proc.getThreadCount());
                process.setUser(proc.getUser());
                process.setState(proc.getState());
                process.setUpTime(proc.getUpTime());
                return process;
            }).toList();
        } catch (Exception e) {
            log.error("Error Retrieving Process Stats {}", e.getMessage());
            throw new BusinessRuntimeException("Failed to retrieve Process Stats");
        }
    }
}
