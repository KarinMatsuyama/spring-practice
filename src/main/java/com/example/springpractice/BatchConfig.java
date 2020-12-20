package com.example.springpractice;

public class BatchConfig {
    private int start;
    private int end;
    private int size;

    public BatchConfig() {
        this.start = 101;
        this.end = 106;
        this.size = 3;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getSize() {
        return size;
    }
}
