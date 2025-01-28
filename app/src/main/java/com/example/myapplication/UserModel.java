package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    private static MatchData matchData = new MatchData();
    private static PitData pitData = new PitData();

    public static PitData getPitData() {
        return pitData;
    }

    public static void setPitData(PitData pitData) {
        UserModel.pitData = pitData;
    }

    public void setMatchData(MatchData matchData){
        UserModel.matchData = matchData;
    }
    public static MatchData getMatchData(){
        return UserModel.matchData;
    }

}
