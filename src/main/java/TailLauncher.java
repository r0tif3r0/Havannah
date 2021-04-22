/*Вариант 9 — tail
Выделение из текстового файла его конца некоторого размера:

fileN задаёт имя входного файла. Если параметр отсутствует, следует
считывать входные данные с консольного ввода. Если параметров несколько,
то перед выводом для каждого файла следует вывести его имя в отдельной строке.

Флаг -o ofile задаёт имя выходного файла (в данном случае ofile).
Если параметр отсутствует, следует выводить результат на консольный вывод.

Флаг -с num, где num это целое число, говорит о том, что из файла нужно
извлечь последние num символов.

Флаг -n num, где num это целое число, говорит о том,
что из файла нужно извлечь последние num строк.

Command line: tail [-c num|-n num] [-o ofile] file0 file1 file2 …

В случае, когда какое-нибудь из имён файлов неверно или указаны одновременно
флаги -c и -n, следует выдать ошибку. Если ни один из этих флагов не указан,
следует вывести последние 10 строк.

Кроме самой программы, следует написать автоматические тесты к ней.
*/
package main.java;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TailLauncher {
    @Option(name = "-c", forbids = {"-n"})
    private Integer chNum;

    @Option(name = "-n", forbids = {"-c"})
    private Integer strNum;

    @Option(name = "-o", metaVar = "ofile")
    private File outputFile;

    @Argument(metaVar = "file/files")
    private List<File> inputFiles;


    public static void main(String[] args) {
        List<String> res = new TailLauncher().launch(args);
        System.out.println(res);
    }

    public List<String> launch(String... args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar tail.jar [-c num|-n num] [-o ofile] file0 file1 file2 …");
            parser.printUsage(System.err);
            return Collections.singletonList("SYNTAX ERROR");
        }

        Tail tail = new Tail(chNum, strNum,outputFile,inputFiles);
        try {
            return tail.main();
        } catch (IOException e) {
            return Collections.singletonList("ERROR");
        }
    }
}
