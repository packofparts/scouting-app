package com.example.myapplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PitData {

    //Pre-Game
    private String teamNumber = "";

    private int driveTrain = 0;

    private int intake = 0;

    private int terrain = 0;

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

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }


    public int getIntake() {
        return intake;
    }

    public void setIntake(int intake) {
        this.intake = intake;
    }

    public int getTerrain() {
        return terrain;
    }

    public void setTerrain(int terrain) {
        this.terrain = terrain;
    }
}
