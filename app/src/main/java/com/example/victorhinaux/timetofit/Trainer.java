package com.example.victorhinaux.timetofit;


import java.util.List;

/**
 * Created by mathp on 27/12/2018.
 */

public class Trainer {
    String name, email, numero;
    List<Training> trainings;

    public Trainer(String name, String email, String numero) {
        this.name = name;
        this.email = email;
        this.numero = numero;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public String trainingsNames()
    {
        String trains = "";
        for (Training train:trainings
             ) {
            trains=trains+train.getName();
        }
        return trains;
    }
}
