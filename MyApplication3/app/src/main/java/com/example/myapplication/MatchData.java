package com.example.myapplication;

public class MatchData {

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

    //End of Match data
    enum chain {
        Nope,
        Attempted,
        Failed,
        Succeded
    }

    enum trap {
        Nope,
        Attempted,
        Failed,
        Suceded
    }
    enum harmony {
        Nope,
        Attempted,
        Failed,
        Succeded
    }
    String notes = "";
    // add human player spotlight data later



}
