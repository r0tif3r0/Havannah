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

    public List<String> main() throws IOException {
        return output(getTail());
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

    private List<String> getTail() throws IOException {
        List<String> res = new ArrayList<>();
        if (input != null) {
            for (File file : input) {
                if (input.size() > 1) res.add(file.getName());
                res.addAll(tail(new Scanner(file)));
            }
        } else res.addAll(tail(new Scanner(System.in)));
        return res;
    }

    private Deque<String> tail(Scanner sc) {
        Deque<String> res = new ArrayDeque<>();
        try (Scanner reader = sc) {
            if (isTailLines)
                reader.useDelimiter("\r\n");
                else reader.useDelimiter("");
            for (int i = 0; i < tailNum; i++) {
                if (reader.hasNext()) {
                    res.add(reader.next());
                } else break;
            }
            while ((reader.hasNext())) {
                res.removeFirst();
                res.add((reader.next()));
            }
        }
        Deque<String> tail = new ArrayDeque<>();
        if (!isTailLines) {
            StringBuilder str = new StringBuilder();
            for (String ch : res){
                str.append(ch);
            }
            tail.add(str.toString());
        } else tail.addAll(res);
        return tail;
    }
}
