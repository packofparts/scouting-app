package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    ViewGroup v = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        v = container;
        binding.team.setText("Team " + MainActivity.teamNumber);
        binding.ampCounter.setText("" + MainActivity.amp);
        binding.speakerUnampCounter.setText("" + MainActivity.speakerUnamp);
        binding.speakerAmpCounter.setText("" + MainActivity.speakerAmp);
        binding.broke.setChecked(MainActivity.broke);
        if (binding.broke.isChecked()){
            binding.broke.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
            binding.broke.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
        } else {
            binding.broke.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
            binding.broke.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
        }
        binding.defense.setChecked(MainActivity.defense);
        if (binding.defense.isChecked()){
            binding.defense.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
            binding.defense.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
        } else {
            binding.defense.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
            binding.defense.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
        }
        binding.ground.setChecked(MainActivity.ground);
        if (binding.ground.isChecked()){
            binding.ground.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
            binding.ground.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
        } else {
            binding.ground.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
            binding.ground.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
        }
        binding.source.setChecked(MainActivity.source);
        if (binding.source.isChecked()){
            binding.source.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
            binding.source.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
        } else {
            binding.source.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
            binding.source.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
        }
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_SecondFragment2);
            }
        });
        binding.prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_thirdFragment2);
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
        binding.relativeLayoutFirst.setTranslationY(50);
        binding.next.setTranslationX((width - 300)/ 1.1f);
        binding.next.setTranslationY(height * 0.863f);
        binding.prev.setTranslationX((width - 300)/ 8.0f);
        binding.prev.setTranslationY(height * 0.863f);
        binding.title.setTranslationX((width - 136)/ 2.0f);
        binding.team.setTranslationY(height * 0.127f);
        binding.team.setTranslationX(binding.title.getX());
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", new float[]{0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f});
        animation.setDuration(1000);
        binding.pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation.start();
                MainActivity.darkMode = !MainActivity.darkMode;
                lightDark(v, MainActivity.darkMode);
            }
        });
        binding.broke.setTranslationX(width * 0.073f);
        binding.broke.setTranslationY(height * 0.216f);
        binding.broke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                if (binding.broke.isChecked()){
                    binding.broke.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.broke.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.broke.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.broke.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
                MainActivity.broke = binding.broke.isChecked();
            }
        });
        binding.defense.setTranslationX(width * 0.073f);
        binding.defense.setTranslationY(height * 0.288f);
        binding.defense.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                if (binding.defense.isChecked()){
                    binding.defense.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.defense.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.defense.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.defense.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
                MainActivity.defense = binding.defense.isChecked();
            }
        });
        binding.ground.setTranslationX(width * 0.073f);
        binding.ground.setTranslationY(height * 0.360f);
        binding.ground.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                if (binding.ground.isChecked()){
                    binding.ground.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.ground.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.ground.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.ground.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
                MainActivity.ground = binding.ground.isChecked();
            }
        });
        binding.source.setTranslationX(width * 0.073f);
        binding.source.setTranslationY(height * 0.432f);
        binding.source.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (binding.source.isChecked()){
                    binding.source.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.source.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.source.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.source.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
                MainActivity.source = binding.source.isChecked();
            }
        });
        binding.ampNotes.setTranslationX(width * 0.073f);
        binding.ampNotes.setTranslationY(height * 0.504f);
        binding.minusAmp.setTranslationX(width * 0.366f);
        binding.minusAmp.setTranslationY(height * 0.453f);
        binding.minusAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.amp > 0){
                    MainActivity.amp --;
                    binding.ampCounter.setText("" + MainActivity.amp);
                }
            }
        });
        binding.plusAmp.setTranslationX(width * 0.732f);
        binding.plusAmp.setTranslationY(height * 0.453f);
        binding.plusAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.amp ++;
                binding.ampCounter.setText("" + MainActivity.amp);
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
                if (MainActivity.speakerUnamp > 0){
                    MainActivity.speakerUnamp --;
                    binding.speakerUnampCounter.setText("" + MainActivity.speakerUnamp);
                }
            }
        });
        binding.plusSpeakerUnamp.setTranslationX(width * 0.732f);
        binding.plusSpeakerUnamp.setTranslationY(height * 0.576f);
        binding.plusSpeakerUnamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.speakerUnamp ++;
                binding.speakerUnampCounter.setText("" + MainActivity.speakerUnamp);
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
                if (MainActivity.speakerAmp > 0){
                    MainActivity.speakerAmp --;
                    binding.speakerAmpCounter.setText("" + MainActivity.speakerAmp);
                }
            }
        });
        binding.plusSpeakerAmp.setTranslationX(width * 0.732f);
        binding.plusSpeakerAmp.setTranslationY(height * 0.727f);
        binding.plusSpeakerAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.speakerAmp ++;
                binding.speakerAmpCounter.setText("" + MainActivity.speakerAmp);
            }
        });
        binding.speakerAmpCounter.setTranslationX(width * 0.598f);
        binding.speakerAmpCounter.setTranslationY(height * 0.784f);
        lightDark(v, MainActivity.darkMode);
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