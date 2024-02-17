package com.example.myapplication;

public class MatchData {

    //Pre-Game
    int teamNumber = 0;

    //Auto Data
    boolean workingAuto = false;
    boolean moveOutOfZone = false;
    int ampAuto = 0;
    int speakerAuto = 0;
    public enum ContactRobot {
        None,
        Allies,
        Opposition
    }
    ContactRobot contactBot;

    //Teleop Data
    boolean broke = false;
    boolean defense = false;
    int ampTeleop = 0;
    int amplifiedSpeaker = 0;
    int speakerTeleop = 0;
    int dump = 0;
    public enum NoteAcquisition {
        None,
        Floor,
        Source,
        Both
    }
    NoteAcquisition noteAcquired;

    //End of Match data
    public enum Chain {
        Nope,
        Attempted,
        Succeeded
    }
    Chain chaining;

    public enum Trap {
        Nope,
        Stuck,
        Fell,
        Succeeded
    }
    Trap trapping;
    public enum Harmony {
        Nope,
        Attempted,
        Succeeded
    }
    Harmony harmo;
    Boolean humanPlayerAtAmp = false;
    int humanPlayerNotesThrown = 0;
    int humanPlayerNotesSpotlighted = 0;
    String notes = "";
    // add human player spotlight data later


    //Getters and Setters

    //Test

    public int getTeamNumber() {    return teamNumber;    }
    public void setTeamNumber(int teamNumber) { this.teamNumber = teamNumber;    }

    //Auto

    public boolean isWorkingAuto() {
        return workingAuto;
    }
    public void setWorkingAuto(boolean workingAuto) {    this.workingAuto = workingAuto;    }

    public boolean isMoveOutOfZone() {
        return moveOutOfZone;
    }
    public void setMoveOutOfZone(boolean moveOutOfZone) {
        this.moveOutOfZone = moveOutOfZone;
    }

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

    public ContactRobot getContactBot() {
        return contactBot;
    }
    public void setContactBot(ContactRobot contactBot) {
        this.contactBot = contactBot;
    }

    //Teleop
    public boolean isBroke() {
        return broke;
    }
    public void setBroke(boolean broke) {
        this.broke = broke;
    }

    public boolean isDefense() {
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

    public int getAmplifiedSpeaker() {
        return amplifiedSpeaker;
    }
    public void setAmplifiedSpeaker(int amplifiedSpeaker) {   this.amplifiedSpeaker = amplifiedSpeaker;   }

    public int getSpeakerTeleop() {
        return speakerTeleop;
    }
    public void setSpeakerTeleop(int speakerTeleop) {
        this.speakerTeleop = speakerTeleop;
    }

    public int getDump() {
        return dump;
    }
    public void setDump(int dump) {
        this.dump = dump;
    }

    public NoteAcquisition getNoteAcquired() {   return noteAcquired;    }
    public void setNoteAcquired(NoteAcquisition noteAcquired) { this.noteAcquired = noteAcquired;  }

    //Endgame

    public Chain getChaining() {    return chaining;    }
    public void setChaining(Chain chain) { this.chaining = chain;  }

    public Trap getTrapping() {     return trapping;    }
    public void setTrapping(Trap trapping) {    this.trapping = trapping;    }

    public Harmony getHarmo() {     return harmo;     }
    public void setHarmo(Harmony harmony) { this.harmo = harmony;  }

    public boolean getHumanPlayerAtAmp(){    return this.humanPlayerAtAmp;    }
    public void setHumanPlayerAtAmp(boolean humanPlayerAtAmp){   this.humanPlayerAtAmp = humanPlayerAtAmp;    }
    public int getHumanPlayerNotesThrown() {   return this.humanPlayerNotesThrown;    }
    public void setHumanPlayerNotesThrown(int notes) {    this.humanPlayerNotesThrown = notes;    }
    public int getHumanPlayerNotesSpotlighted(){    return this.humanPlayerNotesSpotlighted;    }
    public void setHumanPlayerNotesSpotlighted(int notesSpotlighted){    this.humanPlayerNotesSpotlighted = notesSpotlighted;    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
