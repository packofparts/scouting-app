package com.example.myapplication;

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
    public enum Harmony {
        NOPE,
        ATTEMPTED,
        TWO,
        THREE
    }
    Harmony harmo = Harmony.NOPE;
    String notes = "";

    // add human player spotlight data later

    public String returnAllData() {

        String sanitizedNotes = notes.replace("\"", "\\\"");
        return "{\"teamNumber\":\"" + teamNumber + "\"," + "\"matchNumber\":\"" + matchNumber + "\"," + "\"workingAuto\":\"" + workingAuto + "\"," + "\"moveOutOfZone\":\""
                + moveOutOfZone + "\"," + "\"ampAuto\":\"" + ampAuto + "\"," + "\"speakerAuto\":\"" + speakerAuto + "\","
                + "\"getBroke\":\"" + broke + "\"," + "\"getDefense\":\"" + defense + "\"," + "\"ampTeleop\":\"" + ampTeleop + "\"," + "\"missedNotes\":\"" + missedNotes + "\","
                + "\"speakerTeleop\":\"" + speakerTeleop + "\"," + "\"noteAcquired\":\"" + noteAcquired + "\"," + "\"chaining\":\"" + chaining + "\","
                + "\"trapSucess\":\"" + trapSucess + "\"," + "\"trapFail\":\"" + trapFail + "\"," + "\"harmo\":\"" + harmo + "\"," + "\"notes\":\"" + sanitizedNotes + "\"}";
    }

    //Getters and Setters

    //Pre Match
    public void setTeamNumber(String teamNumber) { 
        this.teamNumber = teamNumber;   
    }

    public String getTeamNumber() {    
        return teamNumber;
    }
 
    public void setMatchNumber(String matchNumber) { 
        this.matchNumber = matchNumber;    
    }

    public String getMatchNumber() {    
        return matchNumber;    
    }

    //Auto
    public void setWorkingAuto(boolean workingAuto) {    
        this.workingAuto = workingAuto;    
    }

    public void setMoveOutOfZone(boolean moveOutOfZone) {
        this.moveOutOfZone = moveOutOfZone;
    }

    public boolean getMoveOutOfZone() {
        return moveOutOfZone;
    }

    public void setAmpAuto(int ampAuto) {
        this.ampAuto = ampAuto;
    }

    public int getAmpAuto() {
        return ampAuto;
    }

    public void setSpeakerAuto(int speakerAuto) {
        this.speakerAuto = speakerAuto;
    }

    public int getSpeakerAuto() {
        return speakerAuto;
    }

    //Teleop
    
    public void setBroke(boolean broke) {
        this.broke = broke;
    }
    
    public boolean getBroke() {
        return broke;
    }

    public void setDefense(boolean defense) {
        this.defense = defense;
    }

    public boolean getDefense() {
        return defense;
    }

    public void setAmpTeleop(int ampTeleop) {
        this.ampTeleop = ampTeleop;
    }

    public int getAmpTeleop() {
        return ampTeleop;
    }

    public void setMissedNotes(int missedNotes) {   
        this.missedNotes = missedNotes;   
    }

    public int getMissedNotes() {
        return missedNotes;
    }

    public void setSpeakerNotes(int speakerTeleop) {
        this.speakerTeleop = speakerTeleop;
    }

    public int getSpeakerNotes() {
        return speakerTeleop;
    }

    public void setNoteAcquired(NoteAcquisition noteAcquired) { 
        this.noteAcquired = noteAcquired;  
    }

    public NoteAcquisition getNoteAcquired() {   
        return noteAcquired;    
    }

    //Endgame
    public void setChaining(Chain chain) { 
        this.chaining = chain;  
    }

    public Chain getChaining() {    
        return chaining;    
    }

    public void setTrapSucess(int trapSuccess) {    
        this.trapSuccess = trapSuccess;    
    }

    public int getTrapSuccess() {     
        return trapSuccess;    
    }

    public void setTrapFail(int trapFail) {    
        this.trapFail = trapFail;    
    }
    
    public int getTrapFail() {     
        return trapFail;    
    }
    
    public void setHarmo(Harmony harmony) { 
        this.harmo = harmony;  
    }

    public Harmony getHarmo() {     
        return harmo;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
        
    public String getNotes() {
        return notes;
    }
}
