package com.dynamicthrottle.demo;

import com.dynamicthrottle.demo.services.ExecuteACommand;
import com.dynamicthrottle.demo.services.NginxDynamicThrottler;
import com.dynamicthrottle.demo.services.WriteToFile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@AllArgsConstructor
public class ThrottleAPI {
    private NginxDynamicThrottler nginxDynamicThrottler;
    @PostMapping("/throttle")
    // TODO: This should be ratelimited, so a button calling this multiple times should not cause issues
    // Imaging a button to rate limit and the user presses twice
    // A test should be added to test multi threading
    public String throttle(@RequestParam Integer requestPerMinute) throws IOException, InterruptedException {
        log.info("Throttling {} request per minute", requestPerMinute);
        nginxDynamicThrottler.throttle(requestPerMinute);
        log.info("Done");
        return "done ";
    }
}
