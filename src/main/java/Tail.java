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
            return output(getTailLines());
        else return output(getTailChars());
    }

    private List<String> output(List<String> getTail) throws IOException {
        PrintStream ps;
        if (output != null){
            ps = new PrintStream(output);
        } else ps = System.out;
        for (String str : getTail) {
            ps.println(str);
        }
        return getTail;
    }

    private List<String> getTailChars() throws IOException {
        List<String> tail = new ArrayList<>();
        if (input != null) {
            for (File file : input) {
                if (input.size() > 1) tail.add(file.getName());
                tail.add(lastChars(new Scanner(file)));
            }
        } else tail.add(lastChars(new Scanner(System.in)));
        return tail;
    }

    private String lastChars (Scanner sc){
        Deque<String> res = new ArrayDeque<>();
        try (Scanner reader = sc) {
            reader.useDelimiter("");
            for (int i = 0; i < tailNum; i++) {
                if (reader.hasNext()) {
                    String ch = reader.next();
                    if (!ch.equals("\r")) {
                        res.add(ch);
                        if (ch.equals("\n"))
                            i--;
                    }
                    else i--;
                } else break;
            }
            while (reader.hasNext()) {
                String ch = reader.next();
                if (!ch.equals("\r")) {
                    if (!ch.equals("\n"))
                        res.removeFirst();
                    if (res.getFirst().equals("\n"))
                        res.removeFirst();
                    res.add(ch);
                }
            }
        }
        StringBuilder str = new StringBuilder();
        for (String ch : res){
            str.append(ch);
        }
        return str.toString();
    }

    private List<String> getTailLines() throws IOException {
        List<String> tail = new ArrayList<>();
        if (input != null) {
            for (File file : input) {
                if (input.size() > 1) tail.add(file.getName());
                tail.addAll(lastLines(new Scanner(file)));
            }
        } else {
            tail.addAll(lastLines(new Scanner(System.in)));
        }
        return tail;
    }

    private Deque<String> lastLines(Scanner sc){
        Deque<String> res = new ArrayDeque<>();
        try (Scanner reader = sc) {
            for (int i = 0; i < tailNum; i++) {
                if (reader.hasNextLine()) {
                    res.add(reader.nextLine());
                } else break;
            }
            while (reader.hasNextLine()) {
                res.removeFirst();
                res.add(reader.nextLine());
            }
        }
        return res;
    }
}
