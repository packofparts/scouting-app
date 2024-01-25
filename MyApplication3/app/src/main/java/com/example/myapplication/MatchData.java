package com.example.myapplication;

public class MatchData {

    //Test Data
    boolean testBool = false;

    //Auto Data
    boolean workingAuto = false;
    boolean moveOutOfZone = false;
    int ampAuto = 0;
    int speakerAuto = 0;
    enum ContactRobot {
        None,
        Allies,
        Opposition
    }
    ContactRobot contactBot;

    //Teleop Data
    boolean broke = false;
    boolean defense = false;
    int ampTeleop = 0;
    int ampSpeaker = 0;
    int speakerTeleop = 0;
    int dump = 0;
    enum NoteAcquisition {
        None,
        Floor,
        Source,
        Both
    }
    NoteAcquisition noteAcquired;

    //End of Match data
    enum Chain {
        Nope,
        Attempted,
        Succeeded
    }
    Chain chaining;

    enum Trap {
        Nope,
        Attempted,
        Succeeded
    }
    Trap trapping;
    enum Harmony {
        Nope,
        Attempted,
        Succeeded
    }
    Harmony harmo;
    String notes = "";
    // add human player spotlight data later


    //Getters and Setters

    //Test

    public boolean getTestBool() {    return testBool;    }
    public void setTestBool(boolean testBool) {    this.testBool = testBool;    }

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

    public int getAmpSpeaker() {
        return ampSpeaker;
    }
    public void setAmpSpeaker(int ampSpeaker) {
        this.ampSpeaker = ampSpeaker;
    }

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
    public void setNoteAcquired(NoteAcquisition noteAcquired) {   this.noteAcquired = noteAcquired;   }

    //Endgame

    public Chain getChaining() {    return chaining;    }
    public void setChaining(Chain chaining) {    this.chaining = chaining;    }

    public Trap getTrapping() {     return trapping;    }
    public void setTrapping(Trap trapping) {    this.trapping = trapping;    }

    public Harmony getHarmo() {     return harmo;     }
    public void setHarmo(Harmony harmo) {     this.harmo = harmo;    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

}
