package com.example.myapplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MatchData {

    //Pre-Game
    String teamNumber = "";
    String matchNumber = "";

    double analyzerScore = 0.0;

    String notes = "";



    public void toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        new File("/sdcard/Documents/ScoutingData/").mkdirs();
        // convert Java object to JSON file
        File dataFile = new File("/sdcard/Documents/ScoutingData/match" + UserModel.getMatchData().getMatchNumber() + "_team" + UserModel.getMatchData().getTeamNumber() +".json");
        dataFile.createNewFile();
        mapper.writeValue(dataFile, this);
        File newDataFlag = new File("/sdcard/Documents/ScoutingData/newDataFlag.txt");
        newDataFlag.createNewFile();
    }

    //Getters and Setters

    //Pre Match
    public String getTeamNumber() {    return teamNumber;  }
    public void setTeamNumber(String teamNumber) { this.teamNumber = teamNumber;  }
    public String getMatchNumber() {    return matchNumber;    }
    public void setMatchNumber(String matchNumber) { this.matchNumber = matchNumber;    }

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
