package com.example.home.androidtestgame.objects;

/**
 * Created by Home on 30.5.2018 г..
 */

public class PossibleAnswer {

    public long id,questionId,isCorrect;
    public String name;
    public int points;

    public String toString() {
        return name;
    }
}
