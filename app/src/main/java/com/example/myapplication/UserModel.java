package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    private static MatchData matchData = new MatchData();
    public MutableLiveData<MatchData> userLiveData = new MutableLiveData<>(matchData);

    public LiveData<MatchData> getData() {

        return userLiveData;
    }

    public UserModel() {
        // trigger user load.
        userLiveData = new MutableLiveData<>();
    }

    public void setMatchData(MatchData matchData){
        UserModel.matchData = matchData;
    }
    public static MatchData getMatchData(){
        return UserModel.matchData;
    }

}
