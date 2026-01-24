package com.example.myapplication;


import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;


public class UIHelpers {
    public static int purple = Color.parseColor("#6750A3");
    public static int teamColor = Color.parseColor("#73C2F0");
    public static ColorStateList purpleAsList = ColorStateList.valueOf(purple);
    public static ColorStateList teamColorAsList = ColorStateList.valueOf(teamColor);
    public static final float[] wolfFrames = {0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f};
    public static final float[] wolfScales = {1f, 1.2f, 1f};
    public static MediaPlayer mp;
    public static boolean darkMode = false;

    public static void relate (ViewGroup v, float width, float height, float density){
        float relX = 410.0f * density;
        float relY = 730.0f * density;

        float w = Math.min(width, height);
        float h = Math.max(width, height);
        width = w;
        height = h;
        if (v instanceof CustomScrollView || v instanceof RelativeLayout) {
            v.setMinimumHeight((int)(h * (v.getLayoutParams().height/relY)));

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
            } else if (child instanceof Spinner) {
                child.setTranslationX(width * (child.getTranslationX() / relX));
                child.setTranslationY(height * ((child.getTranslationY() + child.getHeight()/2f) / relY));
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
    public static void darkModeToggle(ViewGroup ignoredV, ImageView popLogo, Context context) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(popLogo, "rotation", UIHelpers.wolfFrames);
        animation.setDuration(1000);
        animation.start();
        ObjectAnimator scaleAnimation = ObjectAnimator.ofFloat(popLogo, "scaleX", UIHelpers.wolfScales);
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(ObjectAnimator.REVERSE);

        scaleAnimation.start();

        darkMode = !darkMode;
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
        builder.setMessage(message + "\n\nIf needed, please raise your hand, so a scouting member can help you!");
        builder.setPositiveButton("I got it!", (dialog, which) -> dialog.cancel());
        builder.create().show();
    }
}
