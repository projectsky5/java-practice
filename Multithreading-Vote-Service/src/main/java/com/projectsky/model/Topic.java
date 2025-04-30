package com.projectsky.model;

import java.util.concurrent.ConcurrentHashMap;

public class Topic {
    private final String name;
    private final ConcurrentHashMap<String, Vote> votes = new ConcurrentHashMap<>();

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ConcurrentHashMap<String, Vote> getVotes() {
        return votes;
    }

    public void addVote(Vote vote) {
        votes.put(vote.getName(), vote);
    }
}
