package com.dynamicthrottle.demo.api;

import com.dynamicthrottle.demo.services.ExecuteACommand;
import com.dynamicthrottle.demo.services.WriteToFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ThrottleAPITest {


    @Value(value = "${local.server.port}")
    private int port;

    @MockBean
    private ExecuteACommand executeACommand;

    @MockBean
    private WriteToFile writeToFile;

    @Value("${nginxTemplate}")
    private String nginxTemplate;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void throttle() throws IOException, InterruptedException {

        assertThat(
                restTemplate.postForObject(
                "http://localhost:" + port + "/throttle?requestPerMinute=100",
                null,
                String.class
        )).contains("done");

        verify(writeToFile).write(nginxTemplate.replace("$THROTTLING_RATE", "100"),
                Path.of("/etc/nginx/nginx.conf"));

        verify(executeACommand).executeCommand("nginx", "-s", "reload");
    }
}
