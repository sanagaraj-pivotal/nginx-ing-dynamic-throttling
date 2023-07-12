package com.dynamicthrottle.demo.services;

import com.dynamicthrottle.demo.exception.CommandFailedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ExecuteACommandTest {

    @Autowired
    private ExecuteACommand target;

    @Test
    public void autoWires() {
        assertThat(target).isNotNull();
    }

    @Test
    public void executesCommand() throws IOException, InterruptedException {

        String output = target.executeCommand("echo", "hello", "world");

        assertThat(output).isEqualTo("hello world\n");
    }

    @Test
    public void throwsExceptionWithStandardError()  {

        CommandFailedException exception = assertThrows(CommandFailedException.class, () -> {
            target.executeCommand("ls", "fdskjhfjksdfh");
        });

        assertThat(exception.getMessage()).isEqualTo("ls: fdskjhfjksdfh: No such file or directory\n");
    }
}
