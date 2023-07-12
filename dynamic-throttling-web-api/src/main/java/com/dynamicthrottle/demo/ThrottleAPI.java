package com.dynamicthrottle.demo;

import com.dynamicthrottle.demo.services.ExecuteACommand;
import com.dynamicthrottle.demo.services.WriteToFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class ThrottleAPI {


    @Autowired
    private ExecuteACommand executeACommand;

    @PostMapping("/throttle")
    // TODO: This should be ratelimited, so a button calling this multiple times should not cause issues
    // Imaging a button to rate limit and the user presses twice
    // A test should be added to test multi threading
    public String throttle(@RequestParam Integer requestPerMinute) throws IOException, InterruptedException {
        log.info("Throttling request to {} request per minute", requestPerMinute);

        String backupMove = executeACommand.executeCommand("mv", "/etc/nginx/nginx.conf", "/etc/nginx/nginx.backup.conf");

        log.info("Backup move: {}", backupMove);

        String highMove = executeACommand.executeCommand("mv", "/etc/nginx/nginx.high.conf", "/etc/nginx/nginx.conf");
        log.info("Move to High: {}", highMove);


        String reload = executeACommand.executeCommand("nginx", "-s", "reload");
        log.info("Nginx reload: {}", reload);

        return "done ";
    }
}
