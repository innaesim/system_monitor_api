package com.monitor.utility.cpu;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.monitor.utility.commons.exceptions.BusinessRuntimeException;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

@Service
@Slf4j
public class CpuService {
    private final HardwareAbstractionLayer hal;
    private final CentralProcessor processor;

    public CpuService(){
        SystemInfo si = new SystemInfo();
        this.hal = si.getHardware();
        this.processor = hal.getProcessor();
    }
    
    public Cpu getCpuStats(){
        try {
            Cpu cpu = new Cpu();
            cpu.setAvailableProcessors(
                processor
                .getPhysicalProcessorCount());
            cpu.setSystemLoad(
                processor.getSystemLoadAverage(3)[0]
            );

            long[] prevTicks = processor.getSystemCpuLoadTicks();
            sleep();
            long[] ticks = processor.getSystemCpuLoadTicks();
            long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
            long system = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
            long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
            long total = user + system + idle; 
            cpu.setCpuUsagePercent(100d * user / total);
            cpu.setSystemUsagePercent(100d * system / total);
            cpu.setIdlePercent(100d * (total - idle) / total);

            double[] coreLoads = processor.getProcessorCpuLoad(1000);
            List<Double> perCore = Arrays.stream(coreLoads)
                .map(v -> v *100)
                .boxed()
                .toList();
            cpu.setPerCoreUsage(perCore);
            cpu.setProcessLoad(processor.getSystemCpuLoadBetweenTicks(ticks));

            return cpu;
        } catch (Exception e) {
            log.error("Error Retrieving Cpu Stats {}", e.getMessage());
            throw new BusinessRuntimeException("Failed to retrieve CPU Stats");
        }
    }

    public void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Process Interrupted {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
