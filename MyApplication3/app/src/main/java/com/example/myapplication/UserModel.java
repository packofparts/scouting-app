package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    private final MutableLiveData<MatchData> userLiveData = new MutableLiveData<>();

    public LiveData<MatchData> getUser() {
        //return userLiveData;
        return null;
    }

    public UserModel() {
        // trigger user load.
        MatchData matchInfo = new MatchData();
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }
}
