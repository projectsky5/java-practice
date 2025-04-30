package com.projectsky.model;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Vote {
    private final String name;
    private final String description;
    private final List<String> options;
    private final ConcurrentHashMap<String, AtomicInteger> optionVotes = new ConcurrentHashMap<>();

    public Vote(String name, String description, List<String> options) {
        this.name = name;
        this.description = description;
        this.options = options;
        for (String option : options) {
            optionVotes.put(option, new AtomicInteger(0));
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getOptions() {
        return options;
    }

    public ConcurrentHashMap<String, AtomicInteger> getOptionVotes() {
        return optionVotes;
    }

    public void vote(String option) {
        if (optionVotes.containsKey(option)) {
            optionVotes.get(option).incrementAndGet();
        }
    }
}
