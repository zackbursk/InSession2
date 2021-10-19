package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

class SumTests {

//    private Path testFile;
//    FileSum fileSum;
//
//    @BeforeEach
//    void setUp() throws IOException{
//        fileSum = new FileSum();
//        testFile = Files.createTempFile("junit5", ".tmp");
//    }
//
//    @AfterEach
//    void cleanup() {
//        testFile.toFile().delete();
//    }
//
//    @Test
//    public void inCorrectFileName(){
//        Assertions.assertThrows(NoSuchFileException.class, () -> {
//            FileSum.readFile("notFound.txt");
//        });
//    }
//
//    @Test
//    public void testAdd() throws IOException {
//        Assertions.assertEquals(2, FileSum.addStuff("1\n1"));
//    }
//
//    @Test
//    public void testRead() throws IOException {
//        Assertions.assertEquals("1", FileSum.readFile("testFile.txt"));
//    }
//
//    @Test
//    public void testWrite() throws IOException {
//        FileSum.writeSum(2);
//        BufferedReader input = new BufferedReader(new FileReader("numbers.txt"));
//        String last = null, line;
//
//        while ((line = input.readLine()) != null) {
//            last = line;
//        }
//        Assertions.assertEquals("Sum: 2", last);
//    }
}
