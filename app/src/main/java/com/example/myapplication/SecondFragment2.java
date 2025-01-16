package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecond2Binding;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Objects;

public class SecondFragment2 extends Fragment {

    private FragmentSecond2Binding binding;

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
        binding.input.setText(UserModel.getMatchData().getNotes());
        binding.inzone.setChecked(UserModel.getMatchData().getInZone());



        binding.characterLimit.setText("Character Limit: " + Objects.requireNonNull(binding.input.getText()).length() + "/150");
        binding.analyzerScore.setText("Analyzer Score: " + UserModel.getMatchData().getAnalyzerScore());

        String[] climbItems = {"No Attempt", "Failed Attempt", "Successful Attempt"};

        ArrayAdapter<String> climbAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, climbItems);

        binding.climbDrop.setAdapter(climbAdapter);

        binding.climbDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserModel.getMatchData().setClimb(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        binding.climbDrop.setSelection(UserModel.getMatchData().getClimb());
        String[] depthItems = {"Not In Zone", "Shallow", "Deep"};

        ArrayAdapter<String> depthAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, depthItems);

        binding.depthDrop.setAdapter(depthAdapter);

        binding.depthDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserModel.getMatchData().setDepth(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.depthDrop.setSelection(UserModel.getMatchData().getDepth());
        binding.inzone.setOnCheckedChangeListener((v, b) -> UserModel.getMatchData().setInZone(b));

        binding.ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> UserModel.getMatchData().setStars(rating));
        binding.ratingBar.setRating((float) UserModel.getMatchData().getStars());

        return binding.getRoot();
    }



    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.next.setOnClickListener(view1 -> {
            UIHelpers.makeConfirmationAlert("Transfer Match Data", "Do you want to transfer your match data?", () -> {
                try {
                    UserModel.getMatchData().toJson();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int num = Integer.parseInt(UserModel.getMatchData().getMatchNumber());
                num++;
                num = num < 1 ? 1 : num;
                num = num > MainActivity.teams.size() ? MainActivity.teams.size() : num;
                UserModel.getMatchData().setMatchNumber(String.valueOf(num));
                NavHostFragment.findNavController(SecondFragment2.this).navigate(R.id.action_SecondFragment2_to_FirstFragment);
            }, () -> {}, getContext());
        });


        binding.prev.setOnClickListener(view12 -> NavHostFragment.findNavController(SecondFragment2.this)
                .navigate(R.id.action_SecondFragment2_to_SecondFragment));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", UIHelpers.wolfFrames);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> {
            ObjectAnimator scaleAnimation = ObjectAnimator.ofFloat(binding.pop, "scaleX", 1f, 1.2f, 1f);
            scaleAnimation.setDuration(500);
            scaleAnimation.setRepeatCount(1);
            scaleAnimation.setRepeatMode(ObjectAnimator.REVERSE);

            scaleAnimation.start();

            UIHelpers.darkModeToggle(v, animation, this.getContext());
        });

        binding.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                binding.characterLimit.setText("Character Limit: " + Objects.requireNonNull(binding.input.getText()).length() + "/150");
                Analyzer2.populate(getResources().openRawResource(R.raw.cleansentiment));
                double score = Analyzer2.analyze(binding.input.getText().toString());
                binding.analyzerScore.setText("Analyzer Score: " + score);
                UserModel.getMatchData().setNotes(binding.input.getText().toString());
                UserModel.getMatchData().setAnalyzerScore(score);
            }
        });

        binding.notesHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Notes", "Here, you can jot down anything extra that you've observed in-game!", getContext()));
        binding.limitHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Character Limit", "You have a 150-character limit for your notes.", getContext()));
        binding.analyzerHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Sentiment Analyzer", "This is the overall sentiment (positivity/negativity) of your notes!", getContext()));

        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
        binding.bottomTag.setText(MainActivity.getLocationText());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}