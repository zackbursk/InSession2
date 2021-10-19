package org.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SumTestsFinal {

    private FileSum fileSum;
    private FileSumService FileSumService;

    @Before
    public void setUp() throws IOException {
        fileSum = new FileSum();
        FileSumService = mock(FileSumService.class);
    }

    //#spy
    @Test
    public void verifyReadSpy() throws IOException {
        FileSumService test = spy(new FileSumService());
        assertEquals("1",test.readFile("testData/testFile2.txt"));
    }

    //#mock
    @Test
    public void verifyReadMock() throws IOException {
        FileSum mocked = mock(FileSum.class);
        mocked.readFile("testData/testFile.txt");
        Mockito.verify(mocked).readFile("testData/testFile.txt");
    }

    //#spy
    @Test
    public void verifyAddSpy() throws IOException {
        FileSumService test = spy(new FileSumService());
        assertEquals(2,test.addStuff("1\n1"));
    }

    //#mock
    @Test
    public void verifyAddMock() throws IOException {
        FileSum mocked = mock(FileSum.class);
        mocked.addStuff("1\n1");
        Mockito.verify(mocked).addStuff("1\n1");
    }

    //#spy
    @Test
    public void verifyWriteSpy() throws IOException {
        FileSumService test = spy(new FileSumService());
        String file = "testData/testFile.txt";
        test.sumFile(file);
        BufferedReader input = new BufferedReader(new FileReader("testData/testFile.txt"));
        String last = null, line;

        while ((line = input.readLine()) != null) {
            last = line;
        }
        Assertions.assertEquals("Sum: 2", last);
    }

    //#mock
    @Test
    public void verifyWriteMock() throws IOException {
        FileSum mocked = mock(FileSum.class);
        mocked.writeSum(2, "testData/testFile.txt");
        Mockito.verify(mocked).writeSum(2, "testData/testFile.txt");
    }

    //#spy
    @Test
    public void verifyAllSpy() throws IOException {
        FileSumService test = spy(new FileSumService());
        test.sumFile("testData/numbers.txt");
        Mockito.verify(test).sumFile("testData/numbers.txt");
    }

    //#mock
    @Test
    public void verifyAllMock() throws IOException {
        FileSum mocked = mock(FileSum.class);
        mocked.sumFile("testData/testFile.txt");
        Mockito.verify(mocked).sumFile("testData/testFile.txt");
    }

    //#stub
    @Test
    public void inCorrectFileName(){
        FileSumService mocked = mock(FileSumService.class);
        Assertions.assertThrows(NoSuchFileException.class, () -> {
            mocked.readFile("testData/notFound.txt");
        });
    }

    //#stub
    @Test
    public void EmptyFile(){
        FileSumService mocked = mock(FileSumService.class);
        Assertions.assertThrows(IOException.class, () -> {
            mocked.readFile("testData/emptyFile.txt");
        });
    }
}