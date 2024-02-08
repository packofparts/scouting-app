package com.example.myapplication;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class UIHelpers {
        public static void lightDark (ViewGroup v, boolean mode){
            int bgColor = (mode ? (Color.BLACK) : Color.WHITE);
            int textColor = (mode ? (Color.WHITE) : (Color.BLACK));
            for (int i = 0; i < v.getChildCount(); i ++){
                View child = v.getChildAt(i);
                if (!(child instanceof Button)) {
                    child.setBackgroundColor(bgColor);
                    if (child instanceof TextView) {
                        TextView tx = (TextView) child;
                        tx.setTextColor(textColor);
                    }
                }
                if (child instanceof Switch) {
                    Switch tx = (Switch) child;
                    tx.setTextColor(textColor);
                }
                if (child instanceof ViewGroup){
                    lightDark((ViewGroup)child, mode);
                }
            }
        }
    }

