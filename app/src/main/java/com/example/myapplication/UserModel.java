package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
//J
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

    void doAction(String teamNumber, String matchNumber) throws IOException {
        // write final matchData to file
        //string and file location currently hardcoded for testing purposes, will fix later
        if (matchNumber.length() < 3) {
            if (matchNumber.length() < 2){
                matchNumber = '0' + matchNumber;
            }
            matchNumber = '0' + matchNumber;
        }
        String fileName = matchNumber + " | " + teamNumber + ".txt";
        String text = "Hello, World!";
        File file = new File("C:\\Users\\camil\\Robotics\\scouting-app", fileName);
        //File file = new File(getExternalFilesDir(null), "DemoFile.jpg");

        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write(text);
        osw.flush();
        osw.close();
        fos.close();
    }

    public static void createFile(String fileName){
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



}
