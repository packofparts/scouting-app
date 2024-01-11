package com.example.myapplication;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    public int amp = 0;
    public int speakerUnamp = 0;
    public int speakerAmp = 0;
    public boolean mode = false;

    ViewGroup v = null;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        v = container;
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;
        ViewGroup.LayoutParams layoutParams = binding.relativeLayoutFirst.getLayoutParams();
        layoutParams.width = (int) width;
        layoutParams.height = (int) height;
        binding.relativeLayoutFirst.setLayoutParams(layoutParams);

        binding.next.setTranslationX((width - 300)/ 2.0f);
        binding.next.setTranslationY(height * 0.863f);
        binding.title.setTranslationX((width - 136)/ 2.0f);
        binding.pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mode = !mode;
               lightDark(v, mode);
            }
        });
        binding.broke.setTranslationX(width * 0.073f);
        binding.broke.setTranslationY(height * 0.216f);
        binding.broke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.broke.isChecked()){
                    binding.broke.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.broke.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.broke.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.broke.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
            }
        });
        binding.defense.setTranslationX(width * 0.073f);
        binding.defense.setTranslationY(height * 0.288f);
        binding.defense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.defense.isChecked()){
                    binding.defense.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.defense.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.defense.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.defense.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
            }
        });
        binding.ground.setTranslationX(width * 0.073f);
        binding.ground.setTranslationY(height * 0.360f);
        binding.ground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.ground.isChecked()){
                    binding.ground.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.ground.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.ground.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.ground.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
            }
        });
        binding.source.setTranslationX(width * 0.073f);
        binding.source.setTranslationY(height * 0.432f);
        binding.source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.source.isChecked()){
                    binding.source.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.source.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.source.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.source.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
            }
        });
        binding.ampNotes.setTranslationX(width * 0.073f);
        binding.ampNotes.setTranslationY(height * 0.504f);
        binding.minusAmp.setTranslationX(width * 0.366f);
        binding.minusAmp.setTranslationY(height * 0.453f);
        binding.minusAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amp > 0){
                    amp --;
                    binding.ampCounter.setText("" + amp);
                }
            }
        });
        binding.plusAmp.setTranslationX(width * 0.732f);
        binding.plusAmp.setTranslationY(height * 0.453f);
        binding.plusAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amp ++;
                binding.ampCounter.setText("" + amp);
            }
        });
        binding.ampCounter.setTranslationX(width * 0.598f);
        binding.ampCounter.setTranslationY(height * 0.511f);

        binding.speakerNotesUnamp.setTranslationX(width * 0.073f);
        binding.speakerNotesUnamp.setTranslationY(height * 0.619f);
        binding.minusSpeakerUnamp.setTranslationX(width * 0.366f);
        binding.minusSpeakerUnamp.setTranslationY(height * 0.576f);
        binding.minusSpeakerUnamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (speakerUnamp > 0){
                    speakerUnamp --;
                    binding.speakerUnampCounter.setText("" + speakerUnamp);
                }
            }
        });
        binding.plusSpeakerUnamp.setTranslationX(width * 0.732f);
        binding.plusSpeakerUnamp.setTranslationY(height * 0.576f);
        binding.plusSpeakerUnamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakerUnamp ++;
                binding.speakerUnampCounter.setText("" + speakerUnamp);
            }
        });
        binding.speakerUnampCounter.setTranslationX(width * 0.598f);
        binding.speakerUnampCounter.setTranslationY(height * 0.633f);

        binding.speakerNotesAmp.setTranslationX(width * 0.073f);
        binding.speakerNotesAmp.setTranslationY(height * 0.770f);
        binding.minusSpeakerAmp.setTranslationX(width * 0.366f);
        binding.minusSpeakerAmp.setTranslationY(height * 0.727f);
        binding.minusSpeakerAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (speakerAmp > 0){
                    speakerAmp --;
                    binding.speakerAmpCounter.setText("" + speakerAmp);
                }
            }
        });
        binding.plusSpeakerAmp.setTranslationX(width * 0.732f);
        binding.plusSpeakerAmp.setTranslationY(height * 0.727f);
        binding.plusSpeakerAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakerAmp ++;
                binding.speakerAmpCounter.setText("" + speakerAmp);
            }
        });
        binding.speakerAmpCounter.setTranslationX(width * 0.598f);
        binding.speakerAmpCounter.setTranslationY(height * 0.784f);
        lightDark(v, mode);
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