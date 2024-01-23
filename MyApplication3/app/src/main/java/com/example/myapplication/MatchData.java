package com.example.myapplication;

public class MatchData {

    //Test Data
    boolean tf = false;

    //Auto Data
    boolean workingAuto = false;
    boolean moveOutOfZone = false;
    int ampA = 0;
    int speakerA = 0;
    enum contactRobot {
        None,
        Allies,
        Opposition
    }
    contactRobot contactBot;

    //Teleop Data
    boolean broke = false;
    boolean defense = false;
    int ampT = 0;
    int ampSpeaker = 0;
    int speakerT = 0;
    int dump = 0;
    enum noteAquisition {
        None,
        Floor,
        Source,
        Both
    }
    noteAquisition noteAquired;

    //End of Match data
    enum chain {
        Nope,
        Attempted,
        Failed,
        Succeded
    }
    chain chaining;

    enum trap {
        Nope,
        Attempted,
        Failed,
        Suceded
    }
    trap trapping;
    enum harmony {
        Nope,
        Attempted,
        Failed,
        Succeded
    }
    harmony harmo;
    String notes = "";
    // add human player spotlight data later


    //Getters

    //Test

    public boolean setTf() {    return tf;    }

    //Auto

    public boolean isWorkingAuto() {
        return workingAuto;
    }

    public boolean isMoveOutOfZone() {
        return moveOutOfZone;
    }

    public int getAmpA() {
        return ampA;
    }

    public int getSpeakerA() {
        return speakerA;
    }

    public contactRobot getContactBot() {
        return contactBot;
    }

    //Teleop
    public boolean isBroke() {
        return broke;
    }

    public boolean isDefense() {
        return defense;
    }

    public int getAmpT() {
        return ampT;
    }

    public int getAmpSpeaker() {
        return ampSpeaker;
    }

    public int getSpeakerT() {
        return speakerT;
    }

    public int getDump() {
        return dump;
    }

    public noteAquisition getNoteAquired() {   return noteAquired;    }

    //Endgame

    public chain getChaining() {    return chaining;    }

    public trap getTrapping() {     return trapping;    }

    public harmony getHarmo() {     return harmo;     }

    public String getNotes() {
        return notes;
    }

    //Setters

    //Test
    public void setTf(boolean tf) {    this.tf = tf;    }

    //Auto
    public void setWorkingAuto(boolean workingAuto) {    this.workingAuto = workingAuto;    }

    public void setMoveOutOfZone(boolean moveOutOfZone) {
        this.moveOutOfZone = moveOutOfZone;
    }

    public void setAmpA(int ampA) {
        this.ampA = ampA;
    }

    public void setSpeakerA(int speakerA) {
        this.speakerA = speakerA;
    }

    public void setContactBot(contactRobot contactBot) {
        this.contactBot = contactBot;
    }

    //Teleop
    public void setBroke(boolean broke) {
        this.broke = broke;
    }

    public void setDefense(boolean defense) {
        this.defense = defense;
    }

    public void setAmpT(int ampT) {
        this.ampT = ampT;
    }

    public void setAmpSpeaker(int ampSpeaker) {
        this.ampSpeaker = ampSpeaker;
    }

    public void setSpeakerT(int speakerT) {
        this.speakerT = speakerT;
    }

    public void setDump(int dump) {
        this.dump = dump;
    }

    public void setNoteAquired(noteAquisition noteAquired) {   this.noteAquired = noteAquired;    }

    //Endgame
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setChaining(chain chaining) {    this.chaining = chaining;    }

    public void setTrapping(trap trapping) {    this.trapping = trapping;    }

    public void setHarmo(harmony harmo) {     this.harmo = harmo;    }
}
