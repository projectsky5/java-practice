package com.projectsky.dto;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public record VoteDto(String description, List<String> options, ConcurrentHashMap<String, AtomicInteger> optionVotes) {
}
