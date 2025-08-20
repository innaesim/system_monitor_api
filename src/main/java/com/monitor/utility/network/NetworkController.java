package com.monitor.utility.network;


import com.monitor.utility.commons.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/network")
@Slf4j
public class NetworkController {
    private final NetworkService networkService;

    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @GetMapping("/get")
    public ApiResponse<List<Network>> getNetworkStatistics() {
        var networkIF = networkService.getNetworkStats();
        return new ApiResponse<>(200,
                "NET-200",
                "Successfully Retrieved Network Stats",
                networkIF);
    }
}
