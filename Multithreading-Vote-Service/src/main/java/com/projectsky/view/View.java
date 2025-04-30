package com.projectsky.view;

import com.projectsky.dto.VoteDto;
import com.projectsky.model.Topic;
import com.projectsky.service.TopicService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class View {
    private final ConcurrentHashMap<String, Topic> topics;
    private final TopicService topicService;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public View(ConcurrentHashMap<String, Topic> topics) {
        this.topics = topics;
        this.topicService = new TopicService(topics);
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void printInfoByTopicAndVote(String topicName, String voteName) throws IOException {
        VoteDto dto = topicService.getInfoByTopicAndVote(topicName, voteName);
        ConcurrentHashMap<String, AtomicInteger> optionVotes = dto.optionVotes();
        StringBuffer buffer = new StringBuffer("Варианты ответа:\n");
        for (int i = 0; i < dto.options().size(); i++) {
            buffer.append(String.format("%d: %s [%d]\n", i + 1,
                    dto.options().get(i),
                    dto.optionVotes().get(dto.options().get(i)).get()));
        }
        dataOutputStream.writeUTF(String.format("%s\n%s", dto.description(), buffer));

    }

    public void printVotesByTopic(String request) throws IOException {
        List<String> votesByTopic = topicService.getVotesByTopic(getParam(request, "-t"));
        if(!votesByTopic.isEmpty()){
            StringBuffer buffer = new StringBuffer("Активные голосования:\n");
            for (int i = 0; i < votesByTopic.size(); i++) {
                buffer.append(String.format("%d: %s\n", i + 1, votesByTopic.get(i)));
            }
            dataOutputStream.writeUTF(buffer.toString());
        } else {
            dataOutputStream.writeUTF("Нет активных голосований");
        }

    }

    public void printEcho(String request) throws IOException {
        String response = request.trim().substring("echo".length()).isEmpty() ? "Missing message" : String.format("Echo:%s", request.trim().substring("echo".length()));
        dataOutputStream.writeUTF(response);
    }

    public void printTime() throws IOException {
        dataOutputStream.writeUTF("Current time: " + topicService.getTime());
    }

    public void doVote(String req) throws IOException {
        String topicName = getParams(req, "-t", "-v").getFirst();
        String voteName = getParams(req, "-t", "-v").get(1);
        dataOutputStream.writeUTF("Выберите нужный пункт");
        String vote = dataInputStream.readUTF();
        topicService.voteByTopicAndVote(topicName, voteName, vote);
        printInfoByTopicAndVote(topicName, voteName);
    }

    public void doCreateTopic(String request) throws IOException {
        topicService.createTopic(getParam(request, "-n"));
        dataOutputStream.writeUTF(String.format("Topic with name %s was created", getParam(request, "-n")));
    }

    public void doCreateVote(String request) throws IOException {
        String topicName = getParam(request, "-t");
        if(topics.containsKey(topicName)){
            dataOutputStream.writeUTF("Введите название голосования");
            String voteName = dataInputStream.readUTF();
            dataOutputStream.writeUTF("Добавьте описание голосования");
            String description = dataInputStream.readUTF();
            dataOutputStream.writeUTF("Введите варианты ответа через запятую");
            String optionList= dataInputStream.readUTF();
            List<String> options = Arrays.asList(sanitizeString(optionList));
            topicService.createVote(topicName, voteName, description, options);
            dataOutputStream.writeUTF(String.format("Vote with name %s was created", voteName));
        } else {
            dataOutputStream.writeUTF(String.format("Topic with name %s was not found", topicName));
        }
    }

    public void printActiveUsers(CopyOnWriteArrayList<Socket> clients) throws IOException {
        String response = String.format("Number of active users: %d", clients.size());
        dataOutputStream.writeUTF(response);
    }

    public List<String> getParams(String request, String firstParamName, String secondParamName) { // Кривая валидация, может сломать если будет create vote -t<field> (без =)
        List<String> params = List.of(
                new String(request.substring(request.indexOf(firstParamName) + 3, request.indexOf(secondParamName) - 1)),
                new String(request.substring(request.indexOf(secondParamName) + 3))
        );
        return params;
    }

    public String getParam(String request, String paramName) { // Кривая валидация, может сломать если будет create vote -t<field> (без =)
        return request.substring(request.indexOf(paramName) + 3);
    }

    private static String[] sanitizeString(String str) {
        return str.trim()
                .replaceAll("\\s+", "")
                .split(",");
    }

    public void printHelpMenuForUser() throws IOException {
        dataOutputStream.writeUTF("""
                            Commands that users can use:
                            * echo <your text>
                            * time
                            * view -t=<topic>
                            * view -t=<topic> -v=<vote>
                            * vote -t=<topic> -v=<vote>
                            * end
                            * help
                            """);
    }

    public void printHelpMenuForAdmin() throws IOException {
        dataOutputStream.writeUTF("""
                            Commands that users can use:
                            * echo <your text>
                            * time
                            * view -t=<topic>
                            * view -t=<topic> -v=<vote>
                            * vote -t=<topic> -v=<vote>
                            * create topic -n=<name>
                            * create vote -t=<topic>
                            * clients
                            * end
                            * exit
                            * help
                            """);
    }
}
