package com.nishchay.concurrentpkg.collection;

public class Student {
    private String name;
    private int rank;

    public Student(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                '}';
    }
}