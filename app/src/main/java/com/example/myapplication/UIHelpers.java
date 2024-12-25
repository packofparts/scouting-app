package com.example.myapplication;


import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class UIHelpers {
    public static int purple = Color.parseColor("#6750A3");
    public static int teamColor = Color.parseColor("#73C2F0");
    public static ColorStateList purpleAsList = ColorStateList.valueOf(purple);
    public static ColorStateList teamColorAsList = ColorStateList.valueOf(teamColor);
    public static final float[] wolfFrames = {0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f};
    public static MediaPlayer mp;
    public static boolean darkMode = false;
    public static void lightDark (ViewGroup v, boolean mode){
        //background color and color of the actual ui elements


        for (int i = 0; i < v.getChildCount(); i ++){
            String viewColor = !mode ? "#000000" : "#FFFFFF";
            View child = v.getChildAt(i);
            boolean found = false;
            String str = (MainActivity.scoutLocation < 3 ? "red" : "blue") + "_" + (mode ? "dark" : "light");
            try {

                viewColor = child.getResources().getString(R.color.class.getField(child.getTag() + "_" + str).getInt(null));
                found = true;
            } catch (Exception e) {

                //Associated color is not found. Default light/dark values will be used.
            }

            if (child.getTag() != null && child.getTag().equals(v.getResources().getString(R.string.no_change))){
                continue;
            }
            if (!(child instanceof Spinner) && !(child instanceof TextInputLayout)) {
                if (found && child instanceof Button){
                    child.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(viewColor)));
                }
                if (!(child instanceof  Button)) {
                    if (child instanceof RelativeLayout) {
                        RelativeLayout r = (RelativeLayout) child;
                        r.setBackground(v.getResources().getDrawable(MainActivity.scoutLocation < 3 ? (mode ? R.drawable.red_dark : R.drawable.red_light) : (mode ? R.drawable.blue_dark : R.drawable.blue_light), null));
                    } else if (child instanceof TextView) {
                        TextView tx = (TextView) child;
                        tx.setTextColor(Color.parseColor(viewColor));
                    } else if (child instanceof TextInputEditText) {
                        TextView tx = (TextInputEditText) child;
                        tx.setTextColor(Color.parseColor(viewColor));
                        tx.setHintTextColor(Color.parseColor(viewColor));
                    }
                    if (child instanceof Switch) {
                        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch tx = (Switch) child;
                        tx.setTextColor(Color.parseColor(viewColor));
                    }
                }
                if (child instanceof ViewGroup) {
                    lightDark((ViewGroup) child, mode);
                }
            }
        }
    }
    public static void relate (ViewGroup v, float width, float height, float density){
        float relX = 410.0f * density;
        float relY = 730.0f * density;

        float w = Math.min(width, height);
        float h = Math.max(width, height);
        width = w;
        height = h;
        if (v instanceof CustomScrollView || v instanceof RelativeLayout) {
            v.setMinimumHeight((int) h);
        }
        //background color and color of the actual ui elements
        for (int i = 0; i < v.getChildCount(); i ++){
            View child = v.getChildAt(i);
            if (! (child instanceof ViewGroup)){
                child.setTranslationX(width * (child.getTranslationX() / relX));
                child.setTranslationY(height * (child.getTranslationY() / relY));
                if (width < relX && height < relY) {
                    child.setScaleX(width / relX);
                    child.setScaleY(height / relY);
                }
            } else {
                relate((ViewGroup) child, width, height, density);
            }
        }
    }

    public static void playHowlSound(Context c) {
        if (mp == null) {
            mp = MediaPlayer.create(c, R.raw.howl);
        }
        mp.start();
    }
    public static void darkModeToggle(ViewGroup v, ObjectAnimator animation, Context context) {
        animation.start();
        darkMode = !darkMode;
        lightDark(v, darkMode);
        playHowlSound(context);
    }
    public static void makeConfirmationAlert(String title, String message, Runnable yes, Runnable no, Context c){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            yes.run();
            dialog.cancel();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            no.run();
            dialog.cancel();
        });
        builder.create().show();
    }
    public static void makeHelpAlert(String title, String message, Context c){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("I got it!", (dialog, which) -> dialog.cancel());
        builder.setNegativeButton("I need help!", (dialog, which) -> {
            dialog.cancel();
            AlertDialog.Builder b = new AlertDialog.Builder(c);
            b.setTitle("Please help me for " + title + "!");
            b.setMessage("Please raise your hand high up in the air, so a scout member can help you with " + title + "!");
            b.setPositiveButton("Okay", (d, w) -> d.cancel());
            b.create().show();
        });
        builder.create().show();
    }
}
