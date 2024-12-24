package com.example.myapplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PitData {

    //Pre-Game
    String teamNumber = "";

    double analyzerScore = 0.0;

    String notes = "";



    public void toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        new File("/sdcard/Documents/PitData/").mkdirs();
        // convert Java object to JSON file
        File dataFile = new File("/sdcard/Documents/PitData/pit" + "_team" + UserModel.getPitData().getTeamNumber() +".json");
        dataFile.createNewFile();
        mapper.writeValue(dataFile, this);
        File newDataFlag = new File("/sdcard/Documents/PitData/newDataFlag.txt");
        newDataFlag.createNewFile();
    }

    //Getters and Setters

    //Pre Match
    public String getTeamNumber() {    return teamNumber;  }
    public void setTeamNumber(String teamNumber) { this.teamNumber = teamNumber;  }

    //Auto

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getAnalyzerScore() {
        return analyzerScore;
    }
    public void setAnalyzerScore(double analyzerScore) {this.analyzerScore = analyzerScore;}
}
