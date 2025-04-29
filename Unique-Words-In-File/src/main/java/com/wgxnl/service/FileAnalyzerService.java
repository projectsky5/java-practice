package com.wgxnl.service;

import com.wgxnl.util.SanitizerUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileAnalyzerService {

    private static final FileAnalyzerService INSTANCE = new FileAnalyzerService();

    private FileAnalyzerService() {}

    public static FileAnalyzerService getInstance() {
        return INSTANCE;
    }

    SanitizerUtil sanitizer = SanitizerUtil.getInstance();
    FilteringService filter = FilteringService.getInstance();

    public void analyzeFile(String fileName, int count) {
        Set<String> uniqueWords = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.trim().isEmpty()) continue;
                String[] split = sanitizer.sanitize(line);
                for (String word : split) {
                    uniqueWords.add(word);
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }

            filter.showUniqueCount(uniqueWords);

            filter.filterPopular(map, count);

            filter.filterNotPopular(map, count);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
