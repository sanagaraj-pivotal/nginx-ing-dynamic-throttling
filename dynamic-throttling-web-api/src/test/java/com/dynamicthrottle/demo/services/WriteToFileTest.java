package com.dynamicthrottle.demo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class WriteToFileTest {

    @Autowired
    private WriteToFile target;

    @Test
    public void autoWires() {
        assertThat(target).isNotNull();
    }

    @Test
    public void writesToFile(@TempDir Path tempDir) throws IOException {

        Path file = tempDir.resolve("temp");

        target.write("Hello World", file);

        String output = Files.readString(file);

        assertThat(output).isEqualTo("Hello World");
    }

    @Test
    public void writeFileWhereThereIsAlreadyContents(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("temp");
        target.write("Hello World", file);

        target.write("Hello Indeed", file);

        String output = Files.readString(file);
        assertThat(output).isEqualTo("Hello Indeed");
    }
}
