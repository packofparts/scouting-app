package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityMainBinding;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    // In-app variables
    private AppBarConfiguration appBarConfiguration;

    public static ArrayList<String[]> teams = new ArrayList<>();

    public static ArrayList<String> names = new ArrayList<>();

    public static int scoutLocation = readInt("ScoutLocation");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        Objects.requireNonNull(getSupportActionBar()).hide();
        if (teams.isEmpty()) {
            updateTeams(getResources());
        }
        if (names.isEmpty()) {
            updateNames(getResources());
        }
        writeInt("ScoutLocation", scoutLocation);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Do nothing here to disable the back button
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up , so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public static String getLocationText(){
        return (MainActivity.scoutLocation < 3 ? "Red " : "Blue ") + (MainActivity.scoutLocation % 3 + 1);
    }
    public static void updateTeams(Resources r){
        teams.clear();
        
        Scanner scanner = new Scanner(r.openRawResource(R.raw.schedule));

        while (scanner.hasNext()) {
            scanner.nextLine();
            teams.add(scanner.nextLine().split("\t"));

        }
        scanner.close();
    }
    public static void updateNames(Resources r){
        names.clear();

        Scanner scanner = new Scanner(r.openRawResource(R.raw.names));
        int line = 0;
        while (scanner.hasNext()) {
            String name = scanner.nextLine();
            if (line % 5 == 1 && !name.contains("@")){
                names.add(name);
            }
            line += 1;
        }
    }

    public static void writeInt(String fileName, int num){
        @SuppressLint("SdCardPath") File file = new File("/sdcard/Documents/" + fileName + ".txt");
        boolean deleted = file.delete();
        if (!deleted) {
            // Log the failure or take appropriate action
            Log.w("FileDeletion", "Failed to delete file");
        }
        try {
            boolean created = file.createNewFile();
            if (!created) {
                // Log the failure or take appropriate action
                Log.w("FileCreation", "Failed to create file");
            }
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(num);
            writer.close();
        } catch (IOException ignored) {

        }

    }
    public static int readInt(String fileName) {
        @SuppressLint("SdCardPath") File file = new File("/sdcard/Documents/" + fileName + ".txt");
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            int res = reader.read();
            reader.close();
            return res;
        } catch (IOException ignored) {

        }
        return 0;
    }

}


