package com.example.myapplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PitData {

    //Pre-Game
    private String teamNumber = "";

    private int driveTrain = 0;

    private int climb = 0;

    private boolean L1, L2, L3, L4 = false;

    private double analyzerScore = 0.0;

    private String notes = "";



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

    //Getters and Setters - Some may appear to have no usage but will be used for json packaging.

    //Pre Match
    public String getTeamNumber() {    return teamNumber;  }
    public void setTeamNumber(String teamNumber) { this.teamNumber = teamNumber;  }

    //Auto
    public int getDriveTrain(){return driveTrain;}
    public void setDriveTrain(int driveTrain){this.driveTrain = driveTrain;}

    public int getClimb(){return climb;}
    public void setClimb(int climb){this.climb = climb;}

    public boolean getL1() {return L1;}
    public void setL1(boolean l) {L1 = l;}
    public boolean getL2() {return L2;}
    public void setL2(boolean l) {L2 = l;}
    public boolean getL3() {return L3;}
    public void setL3(boolean l) {L3 = l;}
    public boolean getL4() {return L4;}
    public void setL4(boolean l) {L4 = l;}

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
