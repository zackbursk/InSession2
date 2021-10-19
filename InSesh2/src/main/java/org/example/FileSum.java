package org.example;

import java.io.IOException;

public class FileSum {
    private FileSumService fileService;

    public void setFileService (FileSumService fileService){
        this.fileService = fileService;
    }

    public String readFile(String file) throws IOException {
        return fileService.readFile(file);
    }

    public int addStuff(String nums) throws IOException {
        return fileService.addStuff(nums);
    }

    public void writeSum(int sum, String file) throws IOException {
        fileService.writeSum(sum, file);
    }

    public void sumFile(String name) throws IOException {
        fileService.sumFile(name);
    }
}

