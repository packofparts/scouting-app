package com.example.myapplication;

import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityMainBinding;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    // In-app variables
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    public static ArrayList<String> teams = new ArrayList<>();

    public static int scoutLocation = readInt("ScoutLocation");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        getSupportActionBar().hide();
        if (teams.isEmpty()) {
            updateTeams(getResources());
        }
        writeInt("ScoutLocation", scoutLocation);
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
    @Override
    public void onBackPressed() { //This is to ensure that nothing happens when you press the back arrow.
        if (false){ //Don't question this.
            super.onBackPressed(); //Android Studio whines if I don't put this line somewhere.
        }
    }

    public static String getLocationText(){
        return (MainActivity.scoutLocation < 3 ? "Red " : "Blue ") + (MainActivity.scoutLocation % 3 + 1);
    }
    public static void updateTeams(Resources r){
        teams.clear();
        
        Scanner scanner = new Scanner(r.openRawResource(R.raw.schedule));

        while (scanner.hasNext()) {
            scanner.nextLine();
            teams.add(scanner.nextLine().split("\t")[scoutLocation]);

        }
        scanner.close();
    }
    public static void writeInt(String fileName, int num){
        File file = new File("/sdcard/Documents/" + fileName + ".txt");
        file.delete();
        try {
            file.createNewFile();
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(num);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static int readInt(String fileName) {
        File file = new File("/sdcard/Documents/" + fileName + ".txt");
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            int res = reader.read();
            reader.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}


