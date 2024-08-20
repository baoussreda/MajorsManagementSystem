package com.example.affectation;

public class Choice {
    private String choice_name;
    private int max_capacity = 4;

    public Choice(String choice_name) {
        this.choice_name = choice_name;
    }

    public String getChoice_name() {
        return choice_name;
    }

    public void setChoice_name(String choice_name) {
        this.choice_name = choice_name;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    void dec_max() {
        this.max_capacity = this.max_capacity - 1;
    }

    public String toString() {
        return choice_name;
    }

}