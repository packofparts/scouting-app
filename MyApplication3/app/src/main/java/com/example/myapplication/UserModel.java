package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    private MutableLiveData<MatchData> userLiveData = new MutableLiveData<>();

    public LiveData<MatchData> getData() {
        return userLiveData;
    }

    public UserModel(MatchData mData) {
        // trigger user load.
        MatchData matchInfo = new MatchData();
        userLiveData = new MutableLiveData<>(mData);
    }

    /*void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }*/
}
