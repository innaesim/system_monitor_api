package com.monitor.utility.cpu;


import com.monitor.utility.commons.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cpu")
@Slf4j
public class CpuController {
    private final CpuService cpuService;

    public CpuController(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @GetMapping("/get")
    public ApiResponse<Cpu> getCpuStatistics() {
        var cpuUsage = cpuService.getCpuStats();
        return new ApiResponse<>(200,
                "CPU-200",
                "Successfully Retrieved CPU Stats",
                cpuUsage);
    }
}
