package com.kryx07.hellorest;

public class LineContainer {

    private final String content;

    public LineContainer(long id, String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }
}