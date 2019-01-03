package com.example.victorhinaux.timetofit;

/**
 * Created by mathp on 27/12/2018.
 */

public class Training {
    String name, details;
    Integer day;
    Integer month;
    Integer year;
    Trainer trainer;

    public Training(String name, String details, Integer day, Integer month, Integer year, Trainer trainer) {
        this.name = name;
        this.details = details;
        this.day = day;
        this.month = month;
        this.year = year;
        this.trainer = trainer;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
