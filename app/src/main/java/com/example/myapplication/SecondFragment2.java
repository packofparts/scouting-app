package com.example.myapplication;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecond2Binding;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class SecondFragment2 extends Fragment {

    private FragmentSecond2Binding binding;
    public static int chainAttemptIndex = 0;
    public static int harmonyAttemptIndex = 0;
    public boolean confirm = false;

    ViewGroup v = null;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecond2Binding.inflate(inflater, container, false);
        v = container;
        binding.team.setText("Team " + UserModel.getMatchData().getTeamNumber());
        binding.notesStuckCounter.setText(String.valueOf(UserModel.getMatchData().getTrapFail()));
        binding.notesSuccessCounter.setText(String.valueOf(UserModel.getMatchData().getTrapSucess()));
        binding.textInput.setText(UserModel.getMatchData().getNotes());
        binding.characterLimit.setText("Character Limit: " + Objects.requireNonNull(binding.textInput.getText()).length() + "/150");
        return binding.getRoot();
    }

    /*Suppressed due to server having no way to get the dynamic path
        TODO: Add matchNumber to file name once added
    */
    @SuppressLint("SdCardPath")
    public void writeFile() throws IOException {
        new File("/sdcard/Documents/ScoutingData/").mkdirs();

        File dataFile = new File("/sdcard/Documents/ScoutingData/match" + UserModel.getMatchData().getMatchNumber() + "_team" + UserModel.getMatchData().getTeamNumber() +".json");
        dataFile.createNewFile();
        PrintWriter pw = new PrintWriter(dataFile);
        pw.print(UserModel.getMatchData().returnAllData());
        pw.close();
        File newDataFlag = new File("/sdcard/Documents/ScoutingData/newDataFlag.txt");
        newDataFlag.createNewFile();
    }

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.next.setOnClickListener(view1 -> {
            if (confirm) {
                try {
                    writeFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                NavHostFragment.findNavController(SecondFragment2.this).navigate(R.id.action_SecondFragment2_to_FirstFragment);
            } else {
                binding.next.setText("Confirm");
                Snackbar.make(view, "Plug into computer and click again to confirm data transfer. Click somewhere else to cancel.", 600).show();
                confirm = true;
            }
        });
        binding.relativeLayoutFirst.setOnClickListener(v -> {
            binding.next.setText("Next");
            confirm = false;
        });


        binding.prev.setOnClickListener(view12 -> NavHostFragment.findNavController(SecondFragment2.this)
                .navigate(R.id.action_SecondFragment2_to_SecondFragment));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
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
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", 0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view13 -> {
            animation.start();
            UIHelpers.darkMode = !UIHelpers.darkMode;
            UIHelpers.lightDark(v, UIHelpers.darkMode);
        });

        binding.input.setTranslationY(height * 0.784f);
        binding.input.setTranslationX(width * 0.073f);
        binding.characterLimit.setTranslationY(height * 0.875f);
        binding.characterLimit.setTranslationX(width * 0.073f);
        binding.textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                binding.characterLimit.setText("Character Limit: " + Objects.requireNonNull(binding.textInput.getText()).length() + "/150");
                UserModel.getMatchData().setNotes(binding.textInput.getText().toString());
            }
        });
        ArrayAdapter<String> chainAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Attempt", "Failed Attempt", "Successful Attempt"});
        binding.chainAttempt.setAdapter(chainAdapter);
        binding.chainAttempt.setSelection(getChainIndex());
        binding.chainAttempt.setTranslationY(height * 0.201f);
        binding.chainAttempt.setTranslationX(width * 0.366f);
        binding.chainAttempt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SecondFragment2.chainAttemptIndex = position;
                MatchData.Chain chainStatus;
                switch (position){
                    case 0:
                        chainStatus = MatchData.Chain.NOPE;
                        break;
                    case 1:
                        chainStatus = MatchData.Chain.ATTEMPTED;
                        break;
                    default:
                        chainStatus = MatchData.Chain.SUCCEDED;
                        break;
                }
                UserModel.getMatchData().setChaining(chainStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.chainAttemptBackground.setTranslationY(binding.chainAttempt.getTranslationY() - (height * 0.045f));
        binding.chainAttemptBackground.setTranslationX(binding.chainAttempt.getTranslationX());
        binding.chain.setTranslationY(height * 0.201f);
        binding.chain.setTranslationX(width * 0.073f);

        ArrayAdapter<String> harmonyAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Attempt", "Failed Attempt", "2 On Chain", "3 On Chain"});
        binding.harmonyAttempt.setAdapter(harmonyAdapter);
        binding.harmonyAttempt.setSelection(getHarmonyIndex());
        binding.harmonyAttempt.setTranslationY(height * 0.302f);
        binding.harmonyAttempt.setTranslationX(width * 0.366f);
        binding.harmonyAttempt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SecondFragment2.harmonyAttemptIndex = position;
                MatchData.Harmony harmonyStatus;
                switch (position){
                    case 0:
                        harmonyStatus = MatchData.Harmony.NOPE;
                        break;
                    case 1:
                        harmonyStatus = MatchData.Harmony.ATTEMPTED;
                        break;
                    case 2:
                        harmonyStatus = MatchData.Harmony.TWO;
                        break;
                    default:
                        harmonyStatus = MatchData.Harmony.THREE;
                        break;

                }
                UserModel.getMatchData().setHarmo(harmonyStatus);
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
        binding.minusNotesStuck.setOnClickListener(view14 -> {
            if (UserModel.getMatchData().getTrapFail() > 0){
                UserModel.getMatchData().setTrapFail(UserModel.getMatchData().getTrapFail() - 1);
                binding.notesStuckCounter.setText(String.valueOf(UserModel.getMatchData().getTrapFail()));
            }
        });
        binding.plusNotesStuck.setTranslationX(width * 0.732f);
        binding.plusNotesStuck.setTranslationY(height * 0.396f);
        binding.plusNotesStuck.setOnClickListener(view15 -> {
            if (UserModel.getMatchData().getTrapFail() < 3) {
                UserModel.getMatchData().setTrapFail(UserModel.getMatchData().getTrapFail() + 1);
                binding.notesStuckCounter.setText(String.valueOf(UserModel.getMatchData().getTrapFail()));
            }
        });
        binding.notesStuckCounter.setTranslationX(width * 0.598f);
        binding.notesStuckCounter.setTranslationY(height * 0.453f);

        binding.notesSuccess.setTranslationX(width * 0.073f);
        binding.notesSuccess.setTranslationY(height * 0.576f);
        binding.minusNotesSuccess.setTranslationX(width * 0.366f);
        binding.minusNotesSuccess.setTranslationY(height * 0.525f);
        binding.minusNotesSuccess.setOnClickListener(view16 -> {
            if (UserModel.getMatchData().getTrapSucess() > 0){
                UserModel.getMatchData().setTrapSucess(UserModel.getMatchData().getTrapSucess() - 1);
                binding.notesSuccessCounter.setText(String.valueOf(UserModel.getMatchData().getTrapSucess()));
            }
        });
        binding.plusNotesSuccess.setTranslationX(width * 0.732f);
        binding.plusNotesSuccess.setTranslationY(height * 0.525f);
        binding.plusNotesSuccess.setOnClickListener(view17 -> {
            if (UserModel.getMatchData().getTrapSucess() < 3) {
                UserModel.getMatchData().setTrapSucess(UserModel.getMatchData().getTrapSucess() + 1);
                binding.notesSuccessCounter.setText(String.valueOf(UserModel.getMatchData().getTrapSucess()));
            }
        });
        binding.notesSuccessCounter.setTranslationX(width * 0.598f);
        binding.notesSuccessCounter.setTranslationY(height * 0.583f);
        UIHelpers.lightDark(v, UIHelpers.darkMode);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private static int getChainIndex(){
        MatchData.Chain chain = UserModel.getMatchData().getChaining();
        if(chain == MatchData.Chain.NOPE) {
            return 0;
        } else if (chain == MatchData.Chain.ATTEMPTED) {
            return 1;
        } else {
            return 2;
        }
    }

    private static int getHarmonyIndex(){
        MatchData.Harmony harmony = UserModel.getMatchData().getHarmo();
        if(harmony == MatchData.Harmony.NOPE) {
            return 0;
        } else if (harmony == MatchData.Harmony.ATTEMPTED) {
            return 1;
        } else if (harmony == MatchData.Harmony.TWO) {
            return 2;
        } else {
            return 3;
        }
    }

}