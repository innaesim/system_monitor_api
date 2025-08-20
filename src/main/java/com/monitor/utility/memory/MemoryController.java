package com.monitor.utility.memory;


import com.monitor.utility.commons.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pu/memory")
@Slf4j
public class MemoryController {
    private final MemoryService memoryService;

    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @GetMapping("/get")
    public ApiResponse<Memory> getMemoryStatistics() {
        var memory = memoryService.getMemoryStats();
        return new ApiResponse<>(200,
                "MEM-200",
                "Successfully Retrieved Memory Stats",
                memory);
    }
}
