package com.example.myapplication;

import android.annotation.SuppressLint;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused") //ObjectWrapper will access these methods to generate json file. These methods will also be used in later updates.
public class MatchData {

    //Pre-Game
    private String teamNumber = "";
    private String matchNumber = "";

    //For climb, 0 = none, 1 = fail, 2 = L1, 3 = L2, and 4 = L3

    private int autoHub, autoHubMissed, autoClimb = 0;

    private int teleOpHub, teleOpHubMissed, teleOpPassed, teleOpClimb = 0;

    private int defEffectiveness = 0;

    private int defDuration, brokeDuration, underDefDuration = 0;

    private String notes = "";


    private String scouterName = "";
    @SuppressLint("SdCardPath")
    public void toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (!new File("/sdcard/Documents/ScoutingData/").mkdirs()) {
            // Log the failure or take appropriate action
            Log.w("ScoutingDataFolderCreation", "Failed to create folder");
        }
        // convert Java object to JSON file
        String s = UserModel.getMatchData().getMatchNumber().length() > 1 ? "": "0";
        File dataFile = new File("/sdcard/Documents/ScoutingData/match" + s + UserModel.getMatchData().getMatchNumber() + "_team" + UserModel.getMatchData().getTeamNumber() +".json");

        if (!dataFile.createNewFile()) {
            // Log the failure or take appropriate action
            Log.w("MatchDataCreation", "Failed to create match data file");
        }
        mapper.writeValue(dataFile, this);
        File newDataFlag = new File("/sdcard/Documents/ScoutingData/newDataFlag.txt");

        if (!newDataFlag.createNewFile()) {
            // Log the failure or take appropriate action
            Log.w("DataFlagCreation", "Failed to create data flag");
        }
    }

    //Getters and Setters

    public String getTeamNumber() {    return teamNumber;  }
    public void setTeamNumber(String teamNumber) { this.teamNumber = teamNumber;  }
    public String getMatchNumber() {    return matchNumber;    }
    public void setMatchNumber(String matchNumber) { this.matchNumber = matchNumber;    }
    public String getScouterName(){ return scouterName;}

    public void setScouterName(String scouterName){this.scouterName = scouterName;}
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getDefDuration() { return defDuration; }

    public void setDefDuration(int defDuration) {
        this.defDuration = defDuration;
    }

    public int getunderDefDuration(){return underDefDuration;}

    public void setunderDefDuration(int underDefDuration){this.underDefDuration = underDefDuration;}

    public int getDefEffectiveness() {
        return defEffectiveness;
    }

    public void setDefEffectiveness(int defEffectiveness) {this.defEffectiveness = defEffectiveness; }

    public int getBrokeDuration() {
        return brokeDuration;
    }

    public void setBrokeDuration(int brokeDuration) {
        this.brokeDuration = brokeDuration;
    }

    public int getAutoHub() {
        return autoHub;
    }

    public void setAutoHub(int autoHub) {
        this.autoHub = autoHub;
    }

    public int getAutoHubMissed() {
        return autoHubMissed;
    }

    public void setAutoHubMissed(int autoHubMissed) {
        this.autoHubMissed = autoHubMissed;
    }

    public int getAutoClimb() {
        return autoClimb;
    }

    public void setAutoClimb(int autoClimb) {
        this.autoClimb = autoClimb;
    }

    public int getTeleOpHub() {
        return teleOpHub;
    }

    public void setTeleOpHub(int teleOpHub) {
        this.teleOpHub = teleOpHub;
    }

    public int getTeleOpHubMissed() {
        return teleOpHubMissed;
    }

    public void setTeleOpHubMissed(int teleOpHubMissed) {
        this.teleOpHubMissed = teleOpHubMissed;
    }

    public int getTeleOpPassed() {
        return teleOpPassed;
    }

    public void setTeleOpPassed(int teleOpPassed) {
        this.teleOpPassed = teleOpPassed;
    }

    public int getTeleOpClimb() {
        return teleOpClimb;
    }

    public void setTeleOpClimb(int teleOpClimb) {
        this.teleOpClimb = teleOpClimb;
    }
}

