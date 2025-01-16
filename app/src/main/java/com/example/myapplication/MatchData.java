package com.example.myapplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MatchData {

    //Pre-Game
    private String teamNumber = "";
    private String matchNumber = "";

    private boolean moveOutOfZone, inZone = false;

    private int autoL1, autoL2, autoL3, autoL4, autoMissedCoral, teleOpL1, teleOpL2, teleOpL3, teleOpL4, teleopMissedCoral = 0;

    private int autoNet, autoProcessor, autoMissedAlgae, teleOpNet, teleOpProcessor, teleOpMissedAlgae = 0;

    private double defPercent, defEffectiveness, brokePercent, stars = 0.0;

    private int depth, climb = 0;

    private double analyzerScore = 0.0;

    private String notes = "";



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

    public boolean getMoveOutOfZone() {
        return moveOutOfZone;
    }

    public void setMoveOutOfZone(boolean moveOutOfZone) {
        this.moveOutOfZone = moveOutOfZone;
    }

    public int getAutoL1() {
        return autoL1;
    }

    public void setAutoL1(int autoL1) {
        this.autoL1 = autoL1;
    }

    public int getAutoL2() {
        return autoL2;
    }

    public void setAutoL2(int autoL2) {
        this.autoL2 = autoL2;
    }

    public int getAutoL3() {
        return autoL3;
    }

    public void setAutoL3(int autoL3) {
        this.autoL3 = autoL3;
    }

    public int getAutoL4() {
        return autoL4;
    }

    public void setAutoL4(int autoL4) {
        this.autoL4 = autoL4;
    }

    public int getAutoMissedCoral() {
        return autoMissedCoral;
    }

    public void setAutoMissedCoral(int autoMissedCoral) {
        this.autoMissedCoral = autoMissedCoral;
    }

    public int getTeleOpL1() {
        return teleOpL1;
    }

    public void setTeleOpL1(int teleOpL1) {
        this.teleOpL1 = teleOpL1;
    }

    public int getTeleOpL2() {
        return teleOpL2;
    }

    public void setTeleOpL2(int teleOpL2) {
        this.teleOpL2 = teleOpL2;
    }

    public int getTeleOpL3() {
        return teleOpL3;
    }

    public void setTeleOpL3(int teleOpL3) {
        this.teleOpL3 = teleOpL3;
    }

    public int getTeleOpL4() {
        return teleOpL4;
    }

    public void setTeleOpL4(int teleOpL4) {
        this.teleOpL4 = teleOpL4;
    }

    public int getTeleopMissedCoral() {
        return teleopMissedCoral;
    }

    public void setTeleopMissedCoral(int teleopMissedCoral) {
        this.teleopMissedCoral = teleopMissedCoral;
    }

    public int getAutoNet() {
        return autoNet;
    }

    public void setAutoNet(int autoNet) {
        this.autoNet = autoNet;
    }

    public int getAutoProcessor() {
        return autoProcessor;
    }

    public void setAutoProcessor(int autoProcessor) {
        this.autoProcessor = autoProcessor;
    }

    public int getAutoMissedAlgae() {
        return autoMissedAlgae;
    }

    public void setAutoMissedAlgae(int autoMissedAlgae) {
        this.autoMissedAlgae = autoMissedAlgae;
    }

    public int getTeleOpNet() {
        return teleOpNet;
    }

    public void setTeleOpNet(int teleOpNet) {
        this.teleOpNet = teleOpNet;
    }

    public int getTeleOpProcessor() {
        return teleOpProcessor;
    }

    public void setTeleOpProcessor(int teleOpProcessor) {
        this.teleOpProcessor = teleOpProcessor;
    }

    public int getTeleOpMissedAlgae() {
        return teleOpMissedAlgae;
    }

    public void setTeleOpMissedAlgae(int teleOpMissedAlgae) {
        this.teleOpMissedAlgae = teleOpMissedAlgae;
    }

    public double getDefPercent() {
        return defPercent;
    }

    public void setDefPercent(double defPercent) {
        this.defPercent = defPercent;
    }

    public double getDefEffectiveness() {
        return defEffectiveness;
    }

    public void setDefEffectiveness(double defEffectiveness) {
        this.defEffectiveness = defEffectiveness;
    }

    public double getBrokePercent() {
        return brokePercent;
    }

    public void setBrokePercent(double brokePercent) {
        this.brokePercent = brokePercent;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getClimb() {
        return climb;
    }

    public void setClimb(int climb) {
        this.climb = climb;
    }

    public boolean getInZone() {
        return inZone;
    }

    public void setInZone(boolean inZone) {
        this.inZone = inZone;
    }
}
