package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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

    void doAction() throws IOException {
        // write final matchData to file
        //string and file location currently hardcoded for testing purposes, will fix later
        String text = "Hello, World!";
        File file = new File("C:\\Users\\camil\\Robotics\\scouting-app", "hello.txt");

        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write(text);
        osw.flush();
        osw.close();
        fos.close();
    }
}
