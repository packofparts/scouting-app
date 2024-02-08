package com.example.myapplication;

import android.graphics.Color;
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
    public static void lightDark (ViewGroup v, boolean mode){
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
                    Switch tx = (Switch) child;
                    tx.setTextColor(Color.parseColor(viewColor));
                }
                if (child instanceof ViewGroup) {
                    lightDark((ViewGroup) child, mode);
                }
            }
        }
    }
}
