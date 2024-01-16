package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentSecond2Binding;
import com.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment2 extends Fragment {

    private FragmentSecond2Binding binding;
    public int noteStuck = 0;
    public int noteSuccess = 0;
    public boolean mode = false;

    ViewGroup v = null;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecond2Binding.inflate(inflater, container, false);
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

        binding.input.setTranslationY(height * 0.201f);
        binding.input.setTranslationX(width * 0.073f);
        binding.characterLimit.setTranslationY(height * 0.295f);
        binding.characterLimit.setTranslationX(width * 0.073f);
        binding.textInput.addTextChangedListener(new TextWatcher() {
            String text = binding.textInput.getText() + "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                text = binding.textInput.getText() + "";
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 150) {
                    binding.textInput.setText(text);
                }
                binding.characterLimit.setText("Character Limit: " + binding.textInput.getText().length() + "/150");
            }
        });

        String[] chainAttempts = {"No Attempt", "Failed Attempt", "Successful Attempt"};
        ArrayAdapter<String> chainAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, chainAttempts);
        binding.chainAttempt.setAdapter(chainAdapter);
        binding.chainAttempt.setTranslationY(height * 0.331f);
        binding.chainAttempt.setTranslationX(width * 0.366f);
        binding.chainAttemptBackground.setTranslationY(binding.chainAttempt.getTranslationY() - (height * 0.045f));
        binding.chainAttemptBackground.setTranslationX(binding.chainAttempt.getTranslationX());
        binding.chain.setTranslationY(height * 0.331f);
        binding.chain.setTranslationX(width * 0.073f);

        String[] harmonyAttempts = {"No Attempt", "Failed Attempt", "2 On Chain", "3 On Chain"};
        ArrayAdapter<String> harmonyAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, harmonyAttempts);
        binding.harmonyAttempt.setAdapter(harmonyAdapter);
        binding.harmonyAttempt.setTranslationY(height * 0.424f);
        binding.harmonyAttempt.setTranslationX(width * 0.366f);
        binding.harmonyAttemptBackground.setTranslationY(binding.harmonyAttempt.getTranslationY() - (height * 0.045f));
        binding.harmonyAttemptBackground.setTranslationX(binding.harmonyAttempt.getTranslationX());
        binding.harmony.setTranslationY(height * 0.424f);
        binding.harmony.setTranslationX(width * 0.073f);

        binding.notesStuck.setTranslationX(width * 0.073f);
        binding.notesStuck.setTranslationY(height * 0.619f);
        binding.minusNotesStuck.setTranslationX(width * 0.366f);
        binding.minusNotesStuck.setTranslationY(height * 0.576f);
        binding.minusNotesStuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noteStuck > 0){
                    noteStuck --;
                    binding.notesStuckCounter.setText("" + noteStuck);
                }
            }
        });
        binding.plusNotesStuck.setTranslationX(width * 0.732f);
        binding.plusNotesStuck.setTranslationY(height * 0.576f);
        binding.plusNotesStuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noteStuck < 3) {
                    noteStuck++;
                    binding.notesStuckCounter.setText("" + noteStuck);
                }
            }
        });
        binding.notesStuckCounter.setTranslationX(width * 0.598f);
        binding.notesStuckCounter.setTranslationY(height * 0.633f);

        binding.notesSuccess.setTranslationX(width * 0.073f);
        binding.notesSuccess.setTranslationY(height * 0.770f);
        binding.minusNotesSuccess.setTranslationX(width * 0.366f);
        binding.minusNotesSuccess.setTranslationY(height * 0.727f);
        binding.minusNotesSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noteSuccess > 0){
                    noteSuccess --;
                    binding.notesSuccessCounter.setText("" + noteSuccess);
                }
            }
        });
        binding.plusNotesSuccess.setTranslationX(width * 0.732f);
        binding.plusNotesSuccess.setTranslationY(height * 0.727f);
        binding.plusNotesSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noteSuccess < 3) {
                    noteSuccess++;
                    binding.notesSuccessCounter.setText("" + noteSuccess);
                }
            }
        });
        binding.notesSuccessCounter.setTranslationX(width * 0.598f);
        binding.notesSuccessCounter.setTranslationY(height * 0.784f);

        binding.human.setTranslationY(height * 0.547f);
        binding.human.setTranslationX(width * 0.073f);
        binding.human.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.human.isChecked()){
                    binding.human.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.human.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    ViewGroup.LayoutParams layoutParams = binding.relativeLayoutFirst.getLayoutParams();
                    layoutParams.width = (int) width;
                    layoutParams.height = (int) height + 500;
                    binding.relativeLayoutFirst.setLayoutParams(layoutParams);
                    binding.next.setTranslationY(height * 0.863f + 500);
                } else {
                    binding.human.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.human.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    ViewGroup.LayoutParams layoutParams = binding.relativeLayoutFirst.getLayoutParams();
                    layoutParams.width = (int) width;
                    layoutParams.height = (int) height;
                    binding.relativeLayoutFirst.setLayoutParams(layoutParams);
                    binding.next.setTranslationY(height * 0.863f);
                }
            }
        });
        binding.notesThrown.setTranslationY(binding.notesStuck.getTranslationY() + 500);
        binding.notesThrown.setTranslationX(width * 0.073f);
        binding.notesHit.setTranslationY(binding.notesSuccess.getTranslationY() + 500);
        binding.notesHit.setTranslationX(width * 0.073f);
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
                if (!(child instanceof Spinner)) {
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
                    if (child instanceof ViewGroup) {
                        lightDark((ViewGroup) child, mode);
                    }
                }
            }
        } else {
            for (int i = 0; i < v.getChildCount(); i ++){
                View child = v.getChildAt(i);
                if (!(child instanceof Spinner)) {
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
                    if (child instanceof ViewGroup) {
                        lightDark((ViewGroup) child, mode);
                    }
                }
            }
        }
    }
}