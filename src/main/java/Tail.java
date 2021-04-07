package main.java;

import java.io.*;
import java.util.*;

import org.apache.commons.io.input.ReversedLinesFileReader;

public class Tail {
    private Integer tailNum = 10;
    private final File output;
    private final List<File> input = new ArrayList<>();

    public Tail(Integer num1, Integer num2, File outputFile, String inputFiles) {
        if (num1 != null) tailNum = num1;
        if (num2 != null) tailNum = num2;
        output = outputFile;
        if (inputFiles!=null) {
            getArguments(input,inputFiles);
        } else {
            System.out.print("Enter file path: ");
            Scanner in = new Scanner(System.in);
            getArguments(input, in.nextLine());
        }
    }

    private void getArguments(List<File> input,String inputString) {
        String[] files = inputString.split(" ");
        for (String fileName : files) {
            input.add(new File(fileName));
        }
    }

    public List<String> main() throws IOException {
        if (output != null){
            FileWriter writer = new FileWriter(output);
            for (String str : getTail()){
                writer.write(str + "\n");
            }
            writer.close();
            return Collections.singletonList("COMPLETED");
        } else {
            return getTail();
        }
    }

    private List<String> getTail() throws IOException {
        List<String> tail = new ArrayList<>();
        for (File file : input){
            int strNum = tailNum;
            if (linesInFile(file) < tailNum)
                strNum = linesInFile(file);
            List<String> res = new ArrayList<>();
            int i = 0;
            ReversedLinesFileReader reader = new ReversedLinesFileReader(file);
            while (i<strNum){
                res.add(reader.readLine());
                i++;
            }
            res.add(file.getName());
            Collections.reverse(res);
            tail.addAll(res);
        }
        return tail;
    }

    private Integer linesInFile (File file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
}
