package com.monitor.utility.disk;


import com.monitor.utility.commons.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/disk")
@Slf4j
public class DiskController {
    private final DiskService diskService;

    public DiskController(DiskService diskService) {
        this.diskService = diskService;
    }

    @GetMapping("/get")
    public ApiResponse<List<Disk>> getDiskStatistics() {
        var disks = diskService.getDiskStats();
        return new ApiResponse<>(200,
                "DISK-200",
                "Successfully Retrieved Disk Stats",
                disks);
    }
}
