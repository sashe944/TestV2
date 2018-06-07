package com.example.home.androidtestgame.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 30.5.2018 г..
 */

public class Question implements Parcelable{

    public long id,questionTypeId,testHeaderId;
    public String name;
    public List<PossibleAnswer> possibleAnswers;

    public Question() {
        possibleAnswers = new ArrayList<>();
    }

    protected Question(Parcel in) {
        id = in.readLong();
        questionTypeId = in.readLong();
        testHeaderId = in.readLong();
        name = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(questionTypeId);
        dest.writeLong(testHeaderId);
        dest.writeString(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public long getTestHeaderId() {
        return testHeaderId;
    }

    public void setTestHeaderId(long testHeaderId) {
        this.testHeaderId = testHeaderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PossibleAnswer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<PossibleAnswer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
