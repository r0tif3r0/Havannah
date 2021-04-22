package main.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Tail {
    private int tailNum = 10;
    private final File output;
    private final List<File> input;
    private boolean isTailLines = true;

    public Tail(Integer chNum, Integer strNum, File outputFile, List<File> inputFiles) {
        if (chNum != null) {
            tailNum = chNum;
            isTailLines = false;
        }
        if (strNum != null) {
            tailNum = strNum;
            isTailLines = true;
        }
        output = outputFile;
        input = inputFiles;
    }

    public List<String> main() throws IOException {
        if (isTailLines)
            if (input != null)
                return output(getTailLines());
            else {
                Scanner in = new Scanner(System.in);
                String content = in.nextLine();
                return output(Collections.singletonList(content));
            }
        else {
            if (input != null)
                return output(getTailChars());
            else {
                Scanner in = new Scanner(System.in);
                String content = in.nextLine();
                if (content.length() - tailNum > 0)
                    return output(Collections.singletonList(content.substring(content.length() - tailNum)));
                else return output(Collections.singletonList(content));
            }
        }
    }

    private List<String> output(List<String> getTail) throws IOException {
        if (output != null){
            FileWriter writer = new FileWriter(output);
            for (String str : getTail){
                writer.write(str + "\n");
            }
            writer.close();
            return Collections.emptyList();
        } else {
            return getTail;
        }
    }

    private List<String> getTailChars() throws IOException {
        List<String> tail = new ArrayList<>();
        for (File file : input) {
            if (input.size()>1) tail.add(file.getName());
            String content = Files.lines(Paths.get(String.valueOf(file))).reduce("", String::concat);
            if (content.length() - tailNum > 0)
                tail.add(content.substring(content.length() - tailNum));
            else tail.add(content);
        }
        return tail;
    }

    private List<String> getTailLines() throws IOException {
        List<String> tail = new ArrayList<>();
        for (File file : input) {
            if (input.size()>1) tail.add(file.getName());
            LinkedList<String> res = new LinkedList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                for (int i = 0; i < tailNum; i++) {
                    String str = reader.readLine();
                    if (str != null) {
                        res.add(str);
                    } else break;
                }
                String s;
                while ((s = reader.readLine()) != null) {
                    res.removeFirst();
                    res.add(s);
                }
                reader.close();
                tail.addAll(res);
            }
        }
        return tail;
    }
}
