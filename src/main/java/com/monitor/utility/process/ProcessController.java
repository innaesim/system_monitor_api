package com.monitor.utility.process;


import com.monitor.utility.commons.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/process")
@Slf4j
public class ProcessController {
    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/get")
    public ApiResponse<List<Process>> getProcessStatistics() {
        var process = processService.getProcessStats();
        return new ApiResponse<>(200,
                "PROC-200",
                "Successfully Retrieved Process Stats",
                process);
    }
}
