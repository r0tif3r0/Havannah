package main.java;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class TailTests {

    private void assertFileContent(String file1, String file2) throws IOException {
        String content1 = (String.valueOf(Files.readAllLines(Paths.get(String.valueOf(file1)))));
        String content2 = (String.valueOf(Files.readAllLines(Paths.get(String.valueOf(file2)))));
        assertEquals(content1,content2);
    }

    @Test //1 argument
    public void test1(){
        assertEquals(Arrays.asList("file1.txt","acb abc abc acb abc abc","acb abc","acb abc abc abc",
                "acb abc abc acb abc abc acb abc abc", "abc", "abc acb abc abc acb abc abc",
                "acb abc abc", "acb abc abc", "acb abc abc acb abc abc",
                "acb abc abc acb abc abc acb abc abc acb abc abc"),new TailLauncher().launch("input/file1.txt"));
    }

    @Test //2 arguments
    public void test2(){
        assertEquals(Arrays.asList("file1.txt","acb abc abc acb abc abc","acb abc","acb abc abc abc",
                "acb abc abc acb abc abc acb abc abc", "abc", "abc acb abc abc acb abc abc",
                "acb abc abc", "acb abc abc", "acb abc abc acb abc abc",
                "acb abc abc acb abc abc acb abc abc acb abc abc", "file0.txt",
                "xyz xyz xyz", "xyz xyz xyz xyz xyz xyz", "xyz xyz xyz",
                "xyz xyz xyz xyz", "xyz", "xyz", "xyz xyz"),
                new TailLauncher().launch("input/file1.txt input/file0.txt"));
    }

    @Test //flag -c
    public void test3(){
        assertEquals(Arrays.asList("file1.txt","acb abc abc", "acb abc abc", "acb abc abc acb abc abc",
                "acb abc abc acb abc abc acb abc abc acb abc abc"),
                new TailLauncher().launch("-c","4","input/file1.txt"));
    }

    @Test //flag -n
    public void test4(){
        assertEquals(Arrays.asList( "file0.txt","xyz xyz xyz",
                "xyz xyz xyz xyz", "xyz", "xyz", "xyz xyz"),
                new TailLauncher().launch("-n","5","input/file0.txt"));
    }

    @Test //flag -o
    public void test5() throws IOException {
        new TailLauncher().launch("-o","output/output.txt","input/file1.txt");
        assertFileContent("output/output.txt","output/expectedOutput.txt");
    }

    @Test //flags -n and -c {SYNTAX ERROR}
    public void test6(){
        assertEquals(Collections.singletonList("SYNTAX ERROR"),
                new TailLauncher().launch("-c","8","-n","5","input/file0.txt"));
    }

    @Test //non-existent input file
    public void test7(){
        assertEquals(Collections.singletonList("ERROR"),
                new TailLauncher().launch("input/FILE.txt"));
    }

    @Test //non-existent output file
    public void test8(){
        assertEquals(Collections.singletonList("ERROR"),
                new TailLauncher().launch("-o","FILE1/output.txt", "input/file1.txt"));
    }
}
