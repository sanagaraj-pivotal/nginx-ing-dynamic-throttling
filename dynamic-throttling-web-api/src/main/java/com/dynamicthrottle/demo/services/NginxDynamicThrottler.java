package com.dynamicthrottle.demo.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class NginxDynamicThrottler {
    private final ExecuteACommand executeACommand;

    private final NginxConfigUpdater nginxConfigUpdater;

    public void throttle(int requestRatePerMinute) throws IOException, InterruptedException {

        nginxConfigUpdater.updateRateLimitInMinutesTo(requestRatePerMinute);
        executeACommand.executeCommand("nginx", "-s", "reload");
    }
}
