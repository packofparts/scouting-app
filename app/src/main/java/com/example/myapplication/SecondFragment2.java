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
    public static boolean human = false;
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
        binding.notesThrownCounter.setText(String.valueOf(UserModel.getMatchData().getHumanPlayerNotesThrown()));
        binding.notesHitCounter.setText(String.valueOf(UserModel.getMatchData().getHumanPlayerNotesSpotlighted()));
        binding.human.setChecked(UserModel.getMatchData().getHumanPlayerAtAmp());
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

        File dataFile = new File("/sdcard/Documents/ScoutingData/" + UserModel.getMatchData().getMatchNumber() + ".txt");
        dataFile.createNewFile();
        PrintWriter pw = new PrintWriter(dataFile);
        pw.print(UserModel.getMatchData().returnAllData());
        pw.close();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Files.deleteIfExists(Paths.get("/sdcard/Documents/ScoutingData/lock.txt"));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.next.setOnClickListener(view1 -> {
            try {
                writeFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            NavHostFragment.findNavController(SecondFragment2.this).navigate(R.id.action_SecondFragment2_to_FirstFragment);
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
            String text = String.valueOf(binding.textInput.getText());
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                text = String.valueOf(binding.textInput.getText());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 150) {
                    binding.textInput.setText(text);
                }
                binding.characterLimit.setText("Character Limit: " + Objects.requireNonNull(binding.textInput.getText()).length() + "/150");
                UserModel.getMatchData().setNotes(binding.textInput.getText().toString());
            }
        });
        ArrayAdapter<String> chainAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Attempt", "Failed Attempt", "Successful Attempt"});
        binding.chainAttempt.setAdapter(chainAdapter);
        binding.chainAttempt.setSelection(SecondFragment2.chainAttemptIndex);
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
        binding.harmonyAttempt.setSelection(SecondFragment2.harmonyAttemptIndex);
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

        binding.human.setTranslationY(height * 0.719f);
        binding.human.setTranslationX(width * 0.073f);
        binding.human.setOnCheckedChangeListener((buttonView, isChecked) -> {
            humanOperation(binding.human.isChecked(), width, height);
            UserModel.getMatchData().setHumanPlayerAtAmp(binding.human.isChecked());
            SecondFragment2.human = binding.human.isChecked();
        });
        binding.notesThrown.setTranslationY(binding.notesStuck.getTranslationY() + 530);
        binding.notesThrown.setTranslationX(binding.notesStuck.getTranslationX());
        binding.minusNotesThrown.setTranslationY(binding.minusNotesStuck.getTranslationY() + 500);
        binding.minusNotesThrown.setTranslationX(binding.minusNotesStuck.getTranslationX());
        binding.minusNotesThrown.setOnClickListener(v -> {
            if (UserModel.getMatchData().getHumanPlayerNotesThrown() > 0) {
                UserModel.getMatchData().setHumanPlayerNotesThrown(UserModel.getMatchData().getHumanPlayerNotesThrown() - 1);
                binding.notesThrownCounter.setText(String.valueOf(UserModel.getMatchData().getHumanPlayerNotesThrown()));
            }
        });
        binding.plusNotesThrown.setTranslationY(binding.plusNotesStuck.getTranslationY() + 500);
        binding.plusNotesThrown.setTranslationX(binding.plusNotesStuck.getTranslationX());
        binding.plusNotesThrown.setOnClickListener(v -> {
            if (UserModel.getMatchData().getHumanPlayerNotesThrown() < 3) {
                UserModel.getMatchData().setHumanPlayerNotesThrown(UserModel.getMatchData().getHumanPlayerNotesThrown() + 1);
                binding.notesThrownCounter.setText(String.valueOf(UserModel.getMatchData().getHumanPlayerNotesThrown()));
            }
        });
        binding.notesThrownCounter.setTranslationY(binding.notesStuckCounter.getTranslationY() + 500);
        binding.notesThrownCounter.setTranslationX(binding.notesStuckCounter.getTranslationX());

        binding.notesHit.setTranslationY(binding.notesSuccess.getTranslationY() + 530);
        binding.notesHit.setTranslationX(binding.notesSuccess.getTranslationX());
        binding.minusNotesHit.setTranslationY(binding.minusNotesSuccess.getTranslationY() + 500);
        binding.minusNotesHit.setTranslationX(binding.minusNotesSuccess.getTranslationX());
        binding.minusNotesHit.setOnClickListener(v -> {
            if (UserModel.getMatchData().getHumanPlayerNotesSpotlighted() > 0) {
                UserModel.getMatchData().setHumanPlayerNotesSpotlighted(UserModel.getMatchData().getHumanPlayerNotesSpotlighted() - 1);
                binding.notesHitCounter.setText(String.valueOf(UserModel.getMatchData().getHumanPlayerNotesSpotlighted()));            }
        });
        binding.plusNotesHit.setTranslationY(binding.plusNotesSuccess.getTranslationY() + 500);
        binding.plusNotesHit.setTranslationX(binding.plusNotesSuccess.getTranslationX());
        binding.plusNotesHit.setOnClickListener(v -> {
            if (UserModel.getMatchData().getHumanPlayerNotesSpotlighted() < 3) {
                UserModel.getMatchData().setHumanPlayerNotesSpotlighted(UserModel.getMatchData().getHumanPlayerNotesSpotlighted() + 1);
                binding.notesHitCounter.setText(String.valueOf(UserModel.getMatchData().getHumanPlayerNotesSpotlighted()));
            }
        });
        binding.notesHitCounter.setTranslationY(binding.notesSuccessCounter.getTranslationY() + 500);
        binding.notesHitCounter.setTranslationX(binding.notesSuccessCounter.getTranslationX());

        humanOperation(binding.human.isChecked(), width, height);
        UIHelpers.lightDark(v, UIHelpers.darkMode);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("ObsoleteSdkInt")
    public void humanOperation(boolean checked, float width, float height){
        int layout = checked ? 1000:500;
        int view = checked? 500: 0;
        int vis = checked? VISIBLE:GONE;
        ColorStateList col = checked? UIHelpers.purpleAsList: UIHelpers.teamColorAsList;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.human.setThumbTintList(col);
            binding.human.setTrackTintList(col);
        }
        ViewGroup.LayoutParams layoutParam = binding.relativeLayoutFirst.getLayoutParams();
        layoutParam.width = (int) width;
        layoutParam.height = (int) height + layout;
        binding.relativeLayoutFirst.setLayoutParams(layoutParam);
        binding.next.setTranslationY(height * 0.863f + view);
        binding.prev.setTranslationY(height * 0.863f + view);
        float input = checked? binding.next.getTranslationY() - 118: height * 0.784f;
        binding.input.setTranslationY(input);
        binding.characterLimit.setTranslationY(binding.input.getTranslationY() + (height * 0.091f));
        binding.notesThrown.setVisibility(vis);
        binding.plusNotesThrown.setVisibility(vis);
        binding.minusNotesThrown.setVisibility(vis);
        binding.notesThrownCounter.setVisibility(vis);
        binding.notesHit.setVisibility(vis);
        binding.plusNotesHit.setVisibility(vis);
        binding.minusNotesHit.setVisibility(vis);
        binding.notesHitCounter.setVisibility(vis);
    }

}