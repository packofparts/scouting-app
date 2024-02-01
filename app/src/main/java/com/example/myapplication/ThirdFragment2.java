package com.example.myapplication;
import android.animation.ObjectAnimator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentThird2Binding;




public class ThirdFragment2 extends Fragment{



    private FragmentThird2Binding binding;
    ViewGroup v = null;


    @Override
    public View onCreateView(

            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState


    ){
        binding = FragmentThird2Binding.inflate(inflater, container, false);
        v = container;
        return binding.getRoot();


    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.toTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment2.this)
                        .navigate(R.id.action_thirdFragment2_to_SecondFragment);
            }
        });
        EditText comments = binding.editTextTextMultiLine.findViewById(R.id.editTextTextMultiLine);
        comments.getText();
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotationX", new float[]{0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f});
        animation.setDuration(1000);
        binding.pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation.start();
                MainActivity.darkMode = !MainActivity.darkMode;
                lightDark(v, MainActivity.darkMode);
            }



        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;

    }
    public void lightDark (ViewGroup v, boolean mode){
        if (!mode){
            for (int i = 0; i < v.getChildCount(); i ++){
                View child = v.getChildAt(i);
                if (!(child instanceof Button)) {
                    child.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    if (child instanceof TextView) {
                        TextView tx = (TextView) child;
                        tx.setTextColor(Color.parseColor("#000000"));
                    }
                }
                if (child instanceof Switch) {
                    Switch tx = (Switch) child;
                    tx.setTextColor(Color.parseColor("#000000"));
                }
                if (child instanceof ViewGroup){
                    lightDark((ViewGroup)child, mode);
                }
            }
        } else {
            for (int i = 0; i < v.getChildCount(); i ++){
                View child = v.getChildAt(i);
                if (!(child instanceof Button)) {
                    child.setBackgroundColor(Color.parseColor("#000000"));
                    if (child instanceof TextView) {
                        TextView tx = (TextView) child;
                        tx.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
                if (child instanceof Switch) {
                    Switch tx = (Switch) child;
                    tx.setTextColor(Color.parseColor("#FFFFFF"));
                }
                if (child instanceof ViewGroup){
                    lightDark((ViewGroup)child, mode);
                }
            }
        }
    }




    }



