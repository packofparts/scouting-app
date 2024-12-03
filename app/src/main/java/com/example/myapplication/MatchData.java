package com.example.myapplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MatchData {

    //Pre-Game
    String teamNumber = "";
    String matchNumber = "";

    //Auto Data
    boolean workingAuto = false;
    boolean moveOutOfZone = false;
    int ampAuto = 0;
    int speakerAuto = 0;

    //Teleop Data
    boolean broke = false;
    boolean defense = false;
    int ampTeleop = 0;
    int missedNotes = 0;
    int speakerTeleop = 0;
    public enum NoteAcquisition {
        NONE,
        FLOOR,
        SOURCE,
        BOTH
    }
    NoteAcquisition noteAcquired = NoteAcquisition.NONE;

    //End of Match data
    public enum Chain {
        NOPE,
        ATTEMPTED,
        SUCCEDED
    }
    Chain chaining = Chain.NOPE;
    int trapSucess = 0;
    int trapFail = 0;

    double analyzerScore = 0.0;

    public enum Harmony {
        NOPE,
        ATTEMPTED,
        TWO,
        THREE
    }
    Harmony harmo = Harmony.NOPE;
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
    public void setWorkingAuto(boolean workingAuto) {    this.workingAuto = workingAuto;    }
    public void setMoveOutOfZone(boolean moveOutOfZone) {
        this.moveOutOfZone = moveOutOfZone;
    }
    public boolean getMoveOutOfZone() {return moveOutOfZone;}

    public int getAmpAuto() {
        return ampAuto;
    }
    public void setAmpAuto(int ampAuto) {
        this.ampAuto = ampAuto;
    }

    public int getSpeakerAuto() {
        return speakerAuto;
    }
    public void setSpeakerAuto(int speakerAuto) {
        this.speakerAuto = speakerAuto;
    }

    //Teleop
    public boolean getIsBroke() {
        return broke;
    }
    public void setBroke(boolean broke) {
        this.broke = broke;
    }

    public boolean getIsDefense() {
        return defense;
    }
    public void setDefense(boolean defense) {
        this.defense = defense;
    }

    public int getAmpTeleop() {
        return ampTeleop;
    }
    public void setAmpTeleop(int ampTeleop) {
        this.ampTeleop = ampTeleop;
    }

    public int getMissedNotes() {
        return missedNotes;
    }
    public void setMissedNotes(int missedNotes) {   this.missedNotes = missedNotes;   }

    public int getSpeakerTeleop() {
        return speakerTeleop;
    }
    public void setSpeakerNotes(int speakerTeleop) {
        this.speakerTeleop = speakerTeleop;
    }

    public NoteAcquisition getNoteAcquired() {   return noteAcquired;    }
    public void setNoteAcquired(NoteAcquisition noteAcquired) { this.noteAcquired = noteAcquired;  }

    //Endgame

    public Chain getChaining() {    return chaining;    }
    public void setChaining(Chain chain) { this.chaining = chain;  }

    public int getTrapSucess() {     return trapSucess;    }
    public void setTrapSucess(int trapSucess) {    this.trapSucess = trapSucess;    }

    public int getTrapFail() {     return trapFail;    }
    public void setTrapFail(int trapFail) {    this.trapFail = trapFail;    }

    public Harmony getHarmo() {     return harmo;     }
    public void setHarmo(Harmony harmony) { this.harmo = harmony;  }

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
