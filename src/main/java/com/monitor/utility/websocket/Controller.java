package com.monitor.utility.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.utility.commons.ApiResponse;
import com.monitor.utility.commons.Statistics;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/socket")
@Slf4j
@Data
public class Controller {
    private SocketService socketService;
    
    @MessageMapping("/send")
    @SendTo("/stats/all")
    public ApiResponse<Statistics> handleMessage(String message){
        var stats = socketService.getAllStatistics();
        return new ApiResponse<>(200,
                "US-200",
                "Successfully Retrieved Usage Statistics",
                stats);
    }
}
