package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileSumService {

    public static int sum;
    public static String nums;

    public static String readFile(String file) throws IOException {
        Path fileName = Path.of(file);
        nums = Files.readString(fileName);
        if (nums.length() == 0){
            throw new IOException("empty file");
        }
        return nums;
    }

    public static int addStuff(String nums) throws IOException {
        sum = 0;
        Scanner scanner = new Scanner(nums);
        while (scanner.hasNextLine()) {
            sum += Integer.parseInt(scanner.nextLine());
        }
        return sum;
    }

    public static void writeSum(int sum,String file) throws IOException {
        String sumToAdd = "Sum: " + sum;
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.newLine();
        writer.write(sumToAdd);
        writer.close();
    }

    public void sumFile(String file) throws IOException {
        readFile(file);
        addStuff(nums);
        writeSum(sum, file);
    }
}
