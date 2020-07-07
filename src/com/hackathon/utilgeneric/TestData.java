package com.hackathon.utilgeneric;

public class TestData {

    public String[] items;

    public TestData(String... items) {
        this.items = items; // should probably make a defensive copy
    }

    public String get(int x) {
        return items[x];
    }
	
}
