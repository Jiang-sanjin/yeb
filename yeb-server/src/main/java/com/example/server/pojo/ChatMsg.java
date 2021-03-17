package com.example.server.pojo;



import java.time.LocalDateTime;

/**
 * 消息
 *
 *
 */

public class ChatMsg {
    private String from;
    private String to;
    private String content;
    private LocalDateTime date;
    private String fromNickName;

    public ChatMsg() {
    }

    public ChatMsg(String from, String to, String content, LocalDateTime date, String fromNickName) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.date = date;
        this.fromNickName = fromNickName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getFromNickName() {
        return fromNickName;
    }

    public void setFromNickName(String fromNickName) {
        this.fromNickName = fromNickName;
    }

    @Override
    public String toString() {
        return "ChatMsg{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", fromNickName='" + fromNickName + '\'' +
                '}';
    }
}
