/*
 * Thread‑Safe Concurrent Line Analyzer (ExecutorService)
 * Difficulty: Medium
 * Focus: Multithreading, thread safety, concurrent aggregation
 * 
 * Problem:
 *   You are given n lines of text. Process each line in parallel 
 * using a fixed thread pool of size numThreads. For each line,
 * compute:
 * 
 *     - * Unique word count (case-insensitive) 
 *     - Most frequent word (case-insensitive). If there’s a tie, choose the lexicographically smallest word.
 *     - * Return a map lineNumber -> LineAnalysis.
 *     - * Tokenization rule: split on non-letters: [^A-Za-z]+
 *     - * Ignore empty tokens.
 *  
 * Requirements
 * 
 *  - Must use ExecutorService (fixed thread pool).
 *  - Must store results in a thread-safe structure (or safely collect futures).
 *  - Must shut down executor properly.
 *  - Must be correct under concurrency.
 * 
 * Example
 * Input lines: 
 *   1 → "Hello world hello"
 *   2 → "code compile test"
 *   3 → "error ERROR Error"
 * 
 * Output: 
 *   1 → (unique=2, mostFrequent="hello")
 *   2 → (unique=3, mostFrequent="code" (all frequency 1, lexicographically smallest) 
 *   3 → (unique=1, mostFrequent="error")
 * 
 * This question mirrors the multithreaded unique-word style used in your internal PDF assessments (ExecutorService + concurrency)
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.lang.model.type.NullType;

class LineAnalysis {
    Integer unique;
    String mostFrequent;

    public LineAnalysis() {
    }

    public LineAnalysis(Integer unique, String mostFrequent) {
        this.unique = unique;
        this.mostFrequent = mostFrequent;
    }

    public Integer getUnique() {
        return unique;
    }

    public void setUnique(Integer unique) {
        this.unique = unique;
    }

    public String getMostFrequent() {
        return mostFrequent;
    }

    public void setMostFrequent(String mostFrequent) {
        this.mostFrequent = mostFrequent;
    }

    public String toString() {
        if (unique != null) {
            return String.format("unique=%d, mostFrequent=%s", unique, mostFrequent);
        } else {
            return "Empty line";
        }
    }
}

class LineAnalyzerService implements Runnable {
    Integer lineNumber;
    String lineContent;
    Map<Integer, LineAnalysis> resultsBucket;

    public LineAnalyzerService(
            Integer lineNumber,
            String lineContent,
            Map<Integer, LineAnalysis> resultsBucket) {
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
        this.resultsBucket = resultsBucket;
    }

    public static Integer lineCompator(Entry<String, Integer> e1, Entry<String, Integer> e2) {
        Integer comp = e2.getValue() - e1.getValue();
        if (comp == 0)
            return e1.getKey().compareTo(e2.getKey());
        return comp;
    }

    public LineAnalysis doAnalysis(String line) {
        Map<String, Integer> counter = new HashMap<>();
        Stream
                .of(line.trim().toLowerCase().split("[^A-Za-z]+"))
                .filter(t -> !"".equals(t))
                .forEach(t -> counter.put(t, counter.getOrDefault(t, 0) + 1));

        Optional<Entry<String, Integer>> result = counter
                .entrySet()
                .stream()
                .sorted(LineAnalyzerService::lineCompator)
                .findFirst();

        return result.isPresent()
                ? new LineAnalysis(result.get().getValue(), result.get().getKey())
                : new LineAnalysis();
    }

    @Override
    public synchronized void run() {
        resultsBucket.put(lineNumber, doAnalysis(lineContent));
    }
}

class FileReaderService {
    private String fileName = "";
    private BiConsumer<Integer, String> doItForEachLine;
    private Consumer<NullType> doItFinally;

    public FileReaderService(String fileName) {
        this.fileName = fileName;
        this.doItForEachLine = (a, b) -> {
        };
        this.doItFinally = (p) -> {
        };
    }

    public FileReaderService forEachLine(BiConsumer<Integer, String> doItForEachLine) {
        this.doItForEachLine = doItForEachLine;
        return this;
    }

    public FileReaderService finalize(Consumer<NullType> doItFinally) {
        this.doItFinally = doItFinally;
        return this;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            Integer lineNumber = 0;
            String lineContent;
            while ((lineContent = br.readLine()) != null) {
                doItForEachLine.accept(lineNumber, lineContent);
                lineNumber++;
            }
            doItFinally.accept(null);
        } catch (Exception e) {
            System.out.print("Error reading the file");
        }
    }
}

class ConcurrentExecutionService {
    ExecutorService executor;

    public ConcurrentExecutionService(Integer threadPoolSize) {
        this.executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    public ConcurrentExecutionService add(Runnable task) {
        executor.submit(task);
        return this;
    }

    public void close() {
        try {
            executor.shutdown();
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Timeout error");
        }
    }
}

public class FileAnalizer {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, LineAnalysis> results = new ConcurrentHashMap<>();
        ConcurrentExecutionService exec = new ConcurrentExecutionService(Integer.parseInt(args[1]));

        new FileReaderService(args[0])
                .forEachLine((ln, lc) -> exec.add(new LineAnalyzerService(ln, lc, results)))
                .finalize(p -> exec.close())
                .run();

        results.forEach((ln, analysis) -> System.out.printf("%d -> %s\n", ln, analysis));
    }
}