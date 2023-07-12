package com.dynamicthrottle.demo.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;


//TODO: This test will run only on unix/linux based environment
@SpringBootTest(properties = {"nginxHostFilePath=" + NginxConfigUpdaterTest.nginxConfigFolder})
class NginxConfigUpdaterTest {

    public static final String nginxConfigFolder = "/tmp";

    @Value("${nginxTemplate}")
    private String nginxTemplate;

    @Autowired
    private NginxConfigUpdater target;

    @Test
    public void autoWires() {
        assertThat(target).isNotNull();
    }

    @Test
    public void updatesThrottlingLimit() throws IOException {

        target.updateRateLimitInMinutesTo(100);

        assertThat(Files.readString(Path.of(nginxConfigFolder).resolve("nginx.conf")))
                .isEqualTo(nginxTemplate.replace("$THROTTLING_RATE", "100"));
    }
}
