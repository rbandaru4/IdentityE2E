package utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class FileReaderUtil {

    public static List<String> extractRegistrationNumbers(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        Pattern pattern = Pattern.compile("\\b[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}\\b");
        Matcher matcher = pattern.matcher(content);

        List<String> registrationNumbers = new ArrayList<>();
        while (matcher.find()) {
            registrationNumbers.add(matcher.group());
        }
        return registrationNumbers;
    }

    public static Map<String, String> readExpectedOutput(String filePath) throws IOException {
        Map<String, String> outputMap = new HashMap<>();
        List<String> lines = Files.readAllLines(Path.of(filePath));

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                outputMap.put(parts[0].trim(), parts[1].trim());
            }
        }
        return outputMap;
    }
}
