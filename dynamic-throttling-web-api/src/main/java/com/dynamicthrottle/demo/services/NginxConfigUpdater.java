package com.dynamicthrottle.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class NginxConfigUpdater {

    @Value("${nginxHostFilePath:/etc/nginx}")
    private String nginxConfigFilePath;

    @Value("${nginxTemplate}")
    private String nginxTemplate;

    private final WriteToFile writeToFile;

    public NginxConfigUpdater(WriteToFile writeToFile) {
        this.writeToFile = writeToFile;
    }

    public void updateRateLimitInMinutesTo(int rateLimit) throws IOException {

        writeToFile.write(
                nginxTemplate.replace("$THROTTLING_RATE", rateLimit + "")
                , Path.of(nginxConfigFilePath).resolve("nginx.conf"));
    }
}
