package com.example.myapplication;

import android.annotation.SuppressLint;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused") //ObjectWrapper will access these methods to generate json file. These methods will also be used in later updates.

public class PitData {

    //Pre-Game
    private String teamNumber = "";

    private int driveTrain = 0;

    private int intake = 0;

    private int terrain = 0;

    private String notes = "";




    @SuppressLint("SdCardPath")
    public void toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (!new File("/sdcard/Documents/PitData/").mkdirs()){
            Log.w("PitDataFolderDeletion", "Failed to create folder");
        }
        // convert Java object to JSON file
        File dataFile = new File("/sdcard/Documents/PitData/pit" + "_team" + UserModel.getPitData().getTeamNumber() +".json");
        if (!dataFile.createNewFile()){
            Log.w("PitDataCreation", "Failed to create pit data file");
        }
        mapper.writeValue(dataFile, this);
        File newDataFlag = new File("/sdcard/Documents/PitData/newDataFlag.txt");
        if(!newDataFlag.createNewFile()){
            Log.w("DataFlagCreation", "Failed to create data flag");
        }
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
