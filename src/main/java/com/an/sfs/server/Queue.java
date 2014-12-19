package com.an.sfs.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Queue {
    private static final Log logger = LogFactory.getLog(Queue.class);
    private List<Float> data = new ArrayList<Float>();
    private int capacity;

    public Queue(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Queue size.");
        }
        capacity = size;
    }

    @Override
    public String toString() {
        return "Queue [" + (data != null ? "data=" + data + ", " : "") + "capacity=" + capacity + "]";
    }

    public void add(Float f) {
        if (data.size() < capacity) {
            data.add(f);
        } else if (data.size() == capacity) {
            data.remove(0);
            data.add(f);
        }
    }

    public float average() {
        if (data.size() < capacity) {
            return 0;
        }

        float total = 0;
        for (Float f : data) {
            total += f;
        }
        float result = total / (float) data.size();
        return result;
    }
}
