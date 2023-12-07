package org.j120.lab2.scriptlanguageinterpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptLanguageInterpreter {

    private final List<String> lines;
    private final Map<String, Integer> variablesMemory = new HashMap<>();

    public ScriptLanguageInterpreter(String scriptFilePath) {
        checkPath(scriptFilePath);
        Path path = Path.of(scriptFilePath);
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkPath(String path) {
        if (path == null){
            throw new IllegalArgumentException("Path is null");
        }
        if (!path.endsWith(".txt")) {
            throw new IllegalArgumentException("Only .txt files are accepted");
        }
        if (!Files.isRegularFile(Path.of(path))) {
            throw new IllegalArgumentException("Invalid text file path: " + path);
        }
    }

    public void interpret() {
        for (String line : lines) {
            interpretLine(line);
        }
    }

    private void interpretLine(String line) {
        if (line.startsWith("#") || line.isBlank()) {
            return;
        }
        String operator = line.split(" ")[0];
        switch (operator) {
            case "print":
                print(line);
                break;
            case "set":
                set(line);
                break;
            case "input":
                input(line);
                break;
        }
    }

    private void input(String line) {
        Pattern pattern = Pattern.compile("\".*\"");
        Matcher matcher1 = pattern.matcher(line);
        if (matcher1.find()) {
            System.out.println(matcher1.group());
        }

        pattern = Pattern.compile("\\$\\w+");
        Matcher matcher2 = pattern.matcher(line);
        if (matcher2.find()) {
            Scanner scanner = new Scanner(System.in);
            variablesMemory.put(matcher2.group(), scanner.nextInt());
            scanner.close();
        }
    }

    private void set(String line) {
        String varName;
        Integer value;
        if (line.matches("set\\s+\\$\\w+\\s*=[^=]*")) {
            Pattern pattern = Pattern.compile("\\$\\w+");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                varName = matcher.group();
                value = calculate(line.substring(line.indexOf("=") + 1));
                variablesMemory.put(varName, value);
            }
        } else throw new RuntimeException("Cannot parse string: " + line);
    }

    private Integer calculate(String arithmExp) {
        arithmExp = arithmExp.trim();
        List<String> values = Arrays.stream(arithmExp.split("\\s*[\\-+]\\s*")).toList();
        List<String> operators = Arrays.stream(arithmExp.split("(\\s*\\$\\w+\\s*|\\s*\\d+\\s*)")).skip(1).toList();
        Integer result = getInt(values.get(0));

        for (int i = 0; i < operators.size(); i++) {
            switch (operators.get(i)) {
                case "+":
                    result += getInt(values.get(i + 1));
                    break;
                case "-":
                    result -= getInt(values.get(i + 1));
                    break;
            }
        }
        return result;
    }

    private Integer getInt(String value) {
        if (value.startsWith("$")) {
            return getFromMemory(value);
        } else {
            return Integer.parseInt(value);
        }
    }

    private void print(String line) {
        Pattern pattern = Pattern.compile("\".*?\"|\\$\\w+");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String value = matcher.group();
            if (value.startsWith("\"")) {
                System.out.print(value.substring(1, value.length() - 1));
            } else {
                System.out.print(getFromMemory(value));
            }
        }
        System.out.println();
    }

    private Integer getFromMemory(String var) {
        if (variablesMemory.containsKey(var)) {
            return variablesMemory.get(var);
        } else throw new RuntimeException("Unknown variable: " + var);
    }
}
