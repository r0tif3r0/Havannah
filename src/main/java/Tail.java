package main.java;

import java.io.*;
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

    private String scan() throws IOException {
        StringBuilder res = new StringBuilder();
        BufferedInputStream in = new BufferedInputStream(System.in);
        int ch;
        while ((ch = in.read()) != -1){
            res.append((char)ch);
        }
        return res.toString();
    }

    public List<String> main() throws IOException {
        if (isTailLines)
            if (input != null)
                return output(getTailLines());
            else {
                String s = scan();
                List<String> res = new ArrayList<>();
                String[] lines = s.split("\n");
                if (lines.length - tailNum > 0){
                    res.addAll(Arrays.asList(lines).subList(lines.length - tailNum, lines.length));
                } else {
                    res.addAll(Arrays.asList(lines));
                }
                return output(res);
            }
        else {
            if (input != null)
                return output(getTailChars());
            else {
                String s = scan();
                List<String> res = new ArrayList<>();
                String[] lines = s.split("\n");
                if (s.length() - (tailNum + lines.length*2) > 0) { // adding num of chars "\n"
                    int chNum = 0;
                    int index = 0;
                    for (int i = lines.length - 1; chNum <= tailNum; i--){
                        index = i;
                        chNum += lines[i].trim().length();
                    }
                    chNum -= lines[index].trim().length();
                    if (chNum != tailNum)
                        res.add(lines[index].substring(lines[index].trim().length() - (tailNum - chNum)));
                    res.addAll(Arrays.asList(lines).subList(index + 1,lines.length));
                } else res.add(s);
                return output(res);
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
        } else {
            for (String str : getTail){
                System.out.println(str);
            }
        }
        return getTail;
    }

    private List<String> getTailChars() throws IOException {
        List<String> tail = new ArrayList<>();
        for (File file : input) {
            if (input.size()>1) tail.add(file.getName());
            Deque<Character> res = new ArrayDeque<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                for (int i = 0; i < tailNum; i++) {
                    int symbol = reader.read();
                    if (symbol != -1) {
                        res.add((char)symbol);
                    } else break;
                }
                int ch;
                while ((ch = reader.read()) != -1) {
                    res.removeFirst();
                    res.add((char)ch);
                }
            }
            StringBuilder str = new StringBuilder();
            for (char ch : res){
                str.append(ch);
            }
            tail.add(str.toString());
        }
        return tail;
    }

    private List<String> getTailLines() throws IOException {
        List<String> tail = new ArrayList<>();
        for (File file : input) {
            if (input.size()>1) tail.add(file.getName());
            Deque<String> res = new ArrayDeque<>();
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
                tail.addAll(res);
            }
        }
        return tail;
    }
}
