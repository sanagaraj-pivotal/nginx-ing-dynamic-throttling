package com.dynamicthrottle.demo.services;

import com.dynamicthrottle.demo.exception.CommandFailedException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Service
public class ExecuteACommand {
    public String executeCommand(
            String ...commandToExecute
    ) throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder(
                commandToExecute
        );

        Process process = processBuilder.start();
        process.waitFor(10, TimeUnit.SECONDS);


        String error = new String(process.getErrorStream().readAllBytes());

        if(!error.isEmpty()) {

            throw new CommandFailedException(error);
        }

        return new String(process.getInputStream().readAllBytes());
    }
}
