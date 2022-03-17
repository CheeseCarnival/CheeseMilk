package com.cheeseocean.message.api;

public class SendRequest {

    private String author;
    private String src;
    private String dest;
    private String content;

    public SendRequest(String author, String src, String dest, String content) {
        this.author = author;
        this.src = src;
        this.dest = dest;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SendRequest{" +
                "author='" + author + '\'' +
                ", src='" + src + '\'' +
                ", dest='" + dest + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
