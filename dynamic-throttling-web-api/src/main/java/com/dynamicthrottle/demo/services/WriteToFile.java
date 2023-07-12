package com.dynamicthrottle.demo.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class WriteToFile {
    public void write(String fileContents, Path file) throws IOException {

        Files.write(file, fileContents.getBytes());
    }
}
