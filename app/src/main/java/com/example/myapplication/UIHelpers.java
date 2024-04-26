package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UIHelpers {
    public static int purple = Color.parseColor("#6750A3");
    public static int teamColor = Color.parseColor("#73C2F0");
    public static ColorStateList purpleAsList = ColorStateList.valueOf(purple);
    public static ColorStateList teamColorAsList = ColorStateList.valueOf(teamColor);

    public static MediaPlayer mp;
    public static boolean darkMode = false;
    public static void lightDark (ViewGroup v, boolean mode){
        //background color and color of the actual ui elements
        String bgColor = !mode ? "#FFFFFF" : "#000000";
        String viewColor = !mode ? "#000000" : "#FFFFFF";
        for (int i = 0; i < v.getChildCount(); i ++){
            View child = v.getChildAt(i);
            if (!(child instanceof Spinner) && !(child instanceof TextInputLayout)) {
                if (!(child instanceof Button)) {
                    child.setBackgroundColor(Color.parseColor(bgColor));
                    if (child instanceof TextView) {
                        TextView tx = (TextView) child;
                        tx.setTextColor(Color.parseColor(viewColor));
                    }
                    if (child instanceof TextInputEditText) {
                        TextView tx = (TextInputEditText) child;
                        tx.setTextColor(Color.parseColor(viewColor));
                        tx.setHintTextColor(Color.parseColor(viewColor));
                    }
                }
                if (child instanceof Switch) {
                    @SuppressLint("UseSwitchCompatOrMaterialCode") Switch tx = (Switch) child;
                    tx.setTextColor(Color.parseColor(viewColor));
                }
                if (child instanceof ViewGroup) {
                    lightDark((ViewGroup) child, mode);
                }
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
}
