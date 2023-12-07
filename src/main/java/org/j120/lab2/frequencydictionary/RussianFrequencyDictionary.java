package org.j120.lab2.frequencydictionary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RussianFrequencyDictionary {
    private static final RussianAlphabeticalOrder RAO; // a custom comparator was created to sort words containing letter "ё" correctly

    static {
        try {
            RAO = new RussianAlphabeticalOrder();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private final Map<String, Integer> wordFrequency;
    private final String reportDirectory;
    private final List<String> words = new ArrayList<>();
    private final Function<Map.Entry<String, Integer>, String> mapEntryToString = e -> String.format("%s (абсолютная частота: %d, относительная частота: %f)",
            e.getKey(), e.getValue(), (float) e.getValue() / words.size());

    public RussianFrequencyDictionary(String reportDirectory, String... textFilesPaths) {
        checkReportDirectory(reportDirectory);
        this.reportDirectory = reportDirectory;
        checkTextFilesPaths(textFilesPaths);
        try {
            for (String textFilePath : textFilesPaths) {
                Path path = Paths.get(textFilePath);
                List<String> lines = Files.readAllLines(path);
                String text = String.join(" ", lines).toLowerCase();
                words.addAll(Arrays.stream(text.split("[\\p{Punct}…«»—\\s]*-\\s+|[\\p{Punct}…«»—\\s&&[^-]]+")).toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        deleteWordsWithNotRussianLetters();
        wordFrequency = new TreeMap<>(RAO);
        countWordsFrequency();
    }

    private static void checkTextFilesPaths(String... textFilesPaths) {
        for (String path : textFilesPaths) {
            if (path == null) {
                throw new IllegalArgumentException("Path is null");
            }
            if (!path.endsWith(".txt")) {
                throw new IllegalArgumentException("Only .txt files are accepted");
            }
            if (!Files.isRegularFile(Path.of(path))) {
                throw new IllegalArgumentException("Invalid text file path: " + path);
            }
        }
    }

    private static void checkReportDirectory(String reportDirectory) {
        if (!Files.isDirectory(Path.of(reportDirectory))) {
            throw new IllegalArgumentException("Invalid report directory");
        }
    }

    private static boolean doesConsistOnlyOfRussianLettersAndHyphen(String word) {
        if (word == null || word.isBlank()) {
            return false;
        }
        return word.chars().allMatch(c -> (c >= 0x0430 && c <= 0x044F) || c == 0x0451 || c == 0x002D);
    }

    public Set<String> getDictionary() {
        return words.stream().sorted(RAO).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void generateReportByAlphabeticalOrder() {
        List<String> strings = wordFrequency.entrySet().stream().map(mapEntryToString).toList();
        writeFile(strings, "report-by-alph.txt");
    }

    public void generateReportByLastLettersAlphabeticalOrder() {
        List<String> strings = wordFrequency.entrySet().stream().
                sorted((e1, e2) -> RAO.compare(reverseString(e1.getKey()), reverseString(e2.getKey()))).map(mapEntryToString).toList();
        writeFile(strings, "report-by-alph-rev.txt");
    }

    public void generateReportByFrequency() {
        List<String> strings = wordFrequency.entrySet().stream().
                sorted((e1, e2) -> e2.getValue() - e1.getValue()).map(mapEntryToString).toList();
        writeFile(strings, "report-by-freq.txt");
    }

    private void deleteWordsWithNotRussianLetters() {
        words.removeIf(w -> !doesConsistOnlyOfRussianLettersAndHyphen(w));
    }

    private void countWordsFrequency() {
        for (String word : words) {
            if (wordFrequency.containsKey(word)) {
                wordFrequency.put(word, wordFrequency.get(word) + 1);
            } else {
                wordFrequency.put(word, 1);
            }
        }
    }

    private String reverseString(String original) {
        return new StringBuilder(original).reverse().toString();
    }

    private void writeFile(List<String> strings, String fileName) {
        Path path = Path.of(reportDirectory + File.separator + fileName);
        try {
            Files.write(path, strings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
