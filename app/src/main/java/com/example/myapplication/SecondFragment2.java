package com.example.myapplication;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecond2Binding;
import com.example.myapplication.databinding.FragmentSecondBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SecondFragment2 extends Fragment {

    private FragmentSecond2Binding binding;

    ViewGroup v = null;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecond2Binding.inflate(inflater, container, false);
        v = container;
        binding.team.setText("Team " + MainActivity.teamNumber);
        //binding.team.setText(getActivity().toString());
        binding.notesStuckCounter.setText("" + MainActivity.noteStuck);
        binding.notesSuccessCounter.setText("" + MainActivity.noteSuccess);
        binding.notesThrownCounter.setText("" + MainActivity.notesThrown);
        binding.notesHitCounter.setText("" + MainActivity.notesHit);
        binding.human.setChecked(MainActivity.human);
        binding.textInput.setText(MainActivity.teleOpNotes);
        binding.characterLimit.setText("Character Limit: " + binding.textInput.getText().length() + "/150");
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment2.this)
                        .navigate(R.id.action_SecondFragment2_to_FirstFragment);
            }
        });

        binding.prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment2.this)
                        .navigate(R.id.action_SecondFragment2_to_SecondFragment);
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

        binding.input.setTranslationY(height * 0.784f);
        binding.input.setTranslationX(width * 0.073f);
        binding.characterLimit.setTranslationY(height * 0.875f);
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
                MainActivity.teleOpNotes = binding.textInput.getText() + "";
            }
        });
        binding.textInput.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                binding.relativeLayoutFirst.setTranslationY(-binding.input.getTranslationY() + 100);
                return false;
            }
        });
        binding.textInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    binding.relativeLayoutFirst.setTranslationY(0f);
                }
                return false;
            }
        });
        ArrayAdapter<String> chainAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, MainActivity.chainAttempts);
        binding.chainAttempt.setAdapter(chainAdapter);
        binding.chainAttempt.setSelection(MainActivity.chainAttemptIndex);
        binding.chainAttempt.setTranslationY(height * 0.201f);
        binding.chainAttempt.setTranslationX(width * 0.366f);
        binding.chainAttempt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.chainAttemptIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.chainAttemptBackground.setTranslationY(binding.chainAttempt.getTranslationY() - (height * 0.045f));
        binding.chainAttemptBackground.setTranslationX(binding.chainAttempt.getTranslationX());
        binding.chain.setTranslationY(height * 0.201f);
        binding.chain.setTranslationX(width * 0.073f);

        ArrayAdapter<String> harmonyAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, MainActivity.harmonyAttempts);
        binding.harmonyAttempt.setAdapter(harmonyAdapter);
        binding.harmonyAttempt.setSelection(MainActivity.harmonyAttemptIndex);
        binding.harmonyAttempt.setTranslationY(height * 0.302f);
        binding.harmonyAttempt.setTranslationX(width * 0.366f);
        binding.harmonyAttempt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.harmonyAttemptIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.harmonyAttemptBackground.setTranslationY(binding.harmonyAttempt.getTranslationY() - (height * 0.045f));
        binding.harmonyAttemptBackground.setTranslationX(binding.harmonyAttempt.getTranslationX());
        binding.harmony.setTranslationY(height * 0.302f);
        binding.harmony.setTranslationX(width * 0.073f);

        binding.notesStuck.setTranslationX(width * 0.073f);
        binding.notesStuck.setTranslationY(height * 0.417f);
        binding.minusNotesStuck.setTranslationX(width * 0.366f);
        binding.minusNotesStuck.setTranslationY(height * 0.396f);
        binding.minusNotesStuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.noteStuck > 0){
                    MainActivity.noteStuck --;
                    binding.notesStuckCounter.setText("" + MainActivity.noteStuck);
                }
            }
        });
        binding.plusNotesStuck.setTranslationX(width * 0.732f);
        binding.plusNotesStuck.setTranslationY(height * 0.396f);
        binding.plusNotesStuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.noteStuck < 3) {
                    MainActivity.noteStuck++;
                    binding.notesStuckCounter.setText("" + MainActivity.noteStuck);
                }
            }
        });
        binding.notesStuckCounter.setTranslationX(width * 0.598f);
        binding.notesStuckCounter.setTranslationY(height * 0.453f);

        binding.notesSuccess.setTranslationX(width * 0.073f);
        binding.notesSuccess.setTranslationY(height * 0.576f);
        binding.minusNotesSuccess.setTranslationX(width * 0.366f);
        binding.minusNotesSuccess.setTranslationY(height * 0.525f);
        binding.minusNotesSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.noteSuccess > 0){
                    MainActivity.noteSuccess --;
                    binding.notesSuccessCounter.setText("" + MainActivity.noteSuccess);
                }
            }
        });
        binding.plusNotesSuccess.setTranslationX(width * 0.732f);
        binding.plusNotesSuccess.setTranslationY(height * 0.525f);
        binding.plusNotesSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.noteSuccess < 3) {
                    MainActivity.noteSuccess++;
                    binding.notesSuccessCounter.setText("" + MainActivity.noteSuccess);
                }
            }
        });
        binding.notesSuccessCounter.setTranslationX(width * 0.598f);
        binding.notesSuccessCounter.setTranslationY(height * 0.583f);

        binding.human.setTranslationY(height * 0.719f);
        binding.human.setTranslationX(width * 0.073f);
        binding.human.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (binding.human.isChecked()){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.human.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                        binding.human.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    }
                    ViewGroup.LayoutParams layoutParams = binding.relativeLayoutFirst.getLayoutParams();
                    layoutParams.width = (int) width;
                    layoutParams.height = (int) height + 500;
                    binding.relativeLayoutFirst.setLayoutParams(layoutParams);
                    binding.next.setTranslationY(height * 0.863f + 500);
                    binding.prev.setTranslationY(height * 0.863f + 500);
                    binding.input.setTranslationY(binding.next.getTranslationY() - 118);
                    binding.characterLimit.setTranslationY(binding.input.getTranslationY() + (height * 0.091f));
                    binding.notesThrown.setVisibility(VISIBLE);
                    binding.plusNotesThrown.setVisibility(VISIBLE);
                    binding.minusNotesThrown.setVisibility(VISIBLE);
                    binding.notesThrownCounter.setVisibility(VISIBLE);
                    binding.notesHit.setVisibility(VISIBLE);
                    binding.plusNotesHit.setVisibility(VISIBLE);
                    binding.minusNotesHit.setVisibility(VISIBLE);
                    binding.notesHitCounter.setVisibility(VISIBLE);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.human.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                        binding.human.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    }
                    ViewGroup.LayoutParams layoutParams = binding.relativeLayoutFirst.getLayoutParams();
                    layoutParams.width = (int) width;
                    layoutParams.height = (int) height - 38;
                    binding.relativeLayoutFirst.setLayoutParams(layoutParams);
                    binding.next.setTranslationY(height * 0.863f);
                    binding.prev.setTranslationY(height * 0.863f);
                    binding.input.setTranslationY(height * 0.784f);
                    binding.characterLimit.setTranslationY(height * 0.875f);
                    binding.notesThrown.setVisibility(GONE);
                    binding.plusNotesThrown.setVisibility(GONE);
                    binding.minusNotesThrown.setVisibility(GONE);
                    binding.notesThrownCounter.setVisibility(GONE);
                    binding.notesHit.setVisibility(GONE);
                    binding.plusNotesHit.setVisibility(GONE);
                    binding.minusNotesHit.setVisibility(GONE);
                    binding.notesHitCounter.setVisibility(GONE);
                }
                MainActivity.human = binding.human.isChecked();
            }
        });
        binding.notesThrown.setTranslationY(binding.notesStuck.getTranslationY() + 530);
        binding.notesThrown.setTranslationX(binding.notesStuck.getTranslationX());
        binding.minusNotesThrown.setTranslationY(binding.minusNotesStuck.getTranslationY() + 500);
        binding.minusNotesThrown.setTranslationX(binding.minusNotesStuck.getTranslationX());
        binding.minusNotesThrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.notesThrown > 0) {
                    MainActivity.notesThrown--;
                    binding.notesThrownCounter.setText("" + MainActivity.notesThrown);
                }
            }
        });
        binding.plusNotesThrown.setTranslationY(binding.plusNotesStuck.getTranslationY() + 500);
        binding.plusNotesThrown.setTranslationX(binding.plusNotesStuck.getTranslationX());
        binding.plusNotesThrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.notesThrown < 3) {
                    MainActivity.notesThrown++;
                    binding.notesThrownCounter.setText("" + MainActivity.notesThrown);
                }
            }
        });
        binding.notesThrownCounter.setTranslationY(binding.notesStuckCounter.getTranslationY() + 500);
        binding.notesThrownCounter.setTranslationX(binding.notesStuckCounter.getTranslationX());

        binding.notesHit.setTranslationY(binding.notesSuccess.getTranslationY() + 530);
        binding.notesHit.setTranslationX(binding.notesSuccess.getTranslationX());
        binding.minusNotesHit.setTranslationY(binding.minusNotesSuccess.getTranslationY() + 500);
        binding.minusNotesHit.setTranslationX(binding.minusNotesSuccess.getTranslationX());
        binding.minusNotesHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.notesHit > 0) {
                    MainActivity.notesHit--;
                    binding.notesHitCounter.setText("" + MainActivity.notesHit);
                }
            }
        });
        binding.plusNotesHit.setTranslationY(binding.plusNotesSuccess.getTranslationY() + 500);
        binding.plusNotesHit.setTranslationX(binding.plusNotesSuccess.getTranslationX());
        binding.plusNotesHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.notesHit < 3) {
                    MainActivity.notesHit++;
                    binding.notesHitCounter.setText("" + MainActivity.notesHit);
                }
            }
        });
        binding.notesHitCounter.setTranslationY(binding.notesSuccessCounter.getTranslationY() + 500);
        binding.notesHitCounter.setTranslationX(binding.notesSuccessCounter.getTranslationX());
        if (binding.human.isChecked()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.human.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                binding.human.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
            }
            ViewGroup.LayoutParams layoutParam = binding.relativeLayoutFirst.getLayoutParams();
            layoutParam.width = (int) width;
            layoutParam.height = (int) height + 500;
            binding.relativeLayoutFirst.setLayoutParams(layoutParam);
            binding.next.setTranslationY(height * 0.863f + 500);
            binding.prev.setTranslationY(height * 0.863f + 500);
            binding.input.setTranslationY(binding.next.getTranslationY() - 118);
            binding.characterLimit.setTranslationY(binding.input.getTranslationY() + (height * 0.091f));
            binding.notesThrown.setVisibility(VISIBLE);
            binding.plusNotesThrown.setVisibility(VISIBLE);
            binding.minusNotesThrown.setVisibility(VISIBLE);
            binding.notesThrownCounter.setVisibility(VISIBLE);
            binding.notesHit.setVisibility(VISIBLE);
            binding.plusNotesHit.setVisibility(VISIBLE);
            binding.minusNotesHit.setVisibility(VISIBLE);
            binding.notesHitCounter.setVisibility(VISIBLE);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.human.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                binding.human.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
            }
            ViewGroup.LayoutParams layoutParam = binding.relativeLayoutFirst.getLayoutParams();
            layoutParam.width = (int) width;
            layoutParam.height = (int) height - 38;
            binding.relativeLayoutFirst.setLayoutParams(layoutParam);
            binding.next.setTranslationY(height * 0.863f);
            binding.prev.setTranslationY(height * 0.863f);
            binding.input.setTranslationY(height * 0.784f);
            binding.characterLimit.setTranslationY(height * 0.875f);
            binding.notesThrown.setVisibility(GONE);
            binding.plusNotesThrown.setVisibility(GONE);
            binding.minusNotesThrown.setVisibility(GONE);
            binding.notesThrownCounter.setVisibility(GONE);
            binding.notesHit.setVisibility(GONE);
            binding.plusNotesHit.setVisibility(GONE);
            binding.minusNotesHit.setVisibility(GONE);
            binding.notesHitCounter.setVisibility(GONE);
        }
        lightDark(v, MainActivity.darkMode);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     *
     * @param v current ViewGroup obtained from onCreateView
     * @param mode false for light mode, true for dark mode
     */
    public void lightDark (ViewGroup v, boolean mode){
        if (!mode){
            for (int i = 0; i < v.getChildCount(); i ++){
                View child = v.getChildAt(i);
                if (!(child instanceof Spinner) && !(child instanceof TextInputLayout)) {
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
                if (!(child instanceof Spinner) && !(child instanceof TextInputLayout)) {
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