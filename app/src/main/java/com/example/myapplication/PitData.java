package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

@SuppressWarnings("unused") //ObjectWrapper will access these methods to generate json file. These methods will also be used in later updates.

public class PitData {

    //Pre-Game
    private String teamNumber = "";

    private int driveTrain = 0;

    private int intake = 0;
    private int launcher = 0;
    private int width = 0;

    private int terrain = 0;

    private String notes = "";

    protected Uri image = null; //This cannot have a setter/getter else ObjectMatter will pick the uri up.


    @SuppressLint("SdCardPath")
    public void toJson(Context context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (!new File("/sdcard/Documents/PitData/").mkdirs()){
            Log.w("PitDataFolderDeletion", "Failed to create folder");
        }
        // convert Java object to JSON file
        File dataFile = new File("/sdcard/Documents/PitData/pit" + "_team" + UserModel.getPitData().getTeamNumber() +".json");
        if (!dataFile.createNewFile()){
            Log.w("PitDataCreation", "Failed to create pit data file");
        }
        mapper.writeValue(dataFile, this);

        if (image != null) {
            File tempFile = new File("/sdcard/Documents/PitData/image" + "_team" + UserModel.getPitData().getTeamNumber() +".jpg");

            try (InputStream inputStream = context.getContentResolver().openInputStream(image);
                 OutputStream outputStream = new FileOutputStream(tempFile)) {

                if (inputStream != null) {

                    // 3. Copy bytes from the URI's stream to the local file
                    byte[] buffer = new byte[8192]; // 8KB buffer
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }

                    outputStream.flush();
                }

            } catch (IOException e) {
                Log.w("DataFlagCreation", "Failed to write image");
            }

        }


        File newDataFlag = new File("/sdcard/Documents/PitData/newDataFlag.txt");
        if(!newDataFlag.createNewFile()){
            Log.w("DataFlagCreation", "Failed to create data flag");
        }
    }

    //Getters and Setters - Some may appear to have no usage but will be used for json packaging.

    //Pre Match
    public String getTeamNumber() {    return teamNumber;  }
    public void setTeamNumber(String teamNumber) { this.teamNumber = teamNumber;  }

    //Auto
    public int getDriveTrain(){return driveTrain;}
    public void setDriveTrain(int driveTrain){this.driveTrain = driveTrain;}

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }


    public int getIntake() {
        return intake;
    }

    public void setIntake(int intake) {
        this.intake = intake;
    }

    public int getTerrain() {
        return terrain;
    }

    public void setTerrain(int terrain) {
        this.terrain = terrain;
    }

    public int getLauncher(){
        return launcher;
    }
    public void setLauncher(int launcher) {
        this.launcher = launcher;
    }

    public int getWidth(){
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
