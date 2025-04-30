package com.projectsky.service;

import com.projectsky.dto.VoteDto;
import com.projectsky.model.Topic;
import com.projectsky.model.Vote;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class TopicService {
    private final ConcurrentHashMap<String, Topic> topics;

    public TopicService(ConcurrentHashMap<String, Topic> topics) {
        this.topics = topics;
    }

    public String getTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public List<String> getVotesByTopic(String topicName){
        return topics.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(topic -> topic.getName().equals(topicName))
                .flatMap(topic -> topic.getVotes().values().stream())
                .map(Vote::getName)
                .toList();
    }

    public VoteDto getInfoByTopicAndVote(String topicName, String voteName){
        List<VoteDto> dtoList = filterByTopicAndVote(topicName, voteName)
                .map(vote -> new VoteDto(vote.getDescription(), vote.getOptions(), vote.getOptionVotes()))
                .toList();
        return dtoList.isEmpty() ? null : dtoList.getFirst();
    }

    public void voteByTopicAndVote(String topicName, String voteName, String option){
        filterByTopicAndVote(topicName, voteName)
                .forEach(topic -> {
                    topic.vote(option);
                });
    }

    public void createTopic(String topicName){
        topics.put(topicName, new Topic(topicName));
    }

    public void createVote(String topicName, String voteName, String voteDescription, List<String> options){
        if(topics.containsKey(topicName)){
            topics.get(topicName).addVote(new Vote(
                    voteName,
                    voteDescription,
                    options
            ));
        }
    }

    private Stream<Vote> filterByTopicAndVote(String topicName, String voteName){
        return topics.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(topic -> topic.getName().equals(topicName))
                .flatMap(topic -> topic.getVotes().values().stream())
                .filter(vote -> vote.getName().equals(voteName));
    }



}
