package com.example.myapplication;




import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecond2Binding;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Objects;

public class SecondFragment2 extends Fragment {

    private FragmentSecond2Binding binding;
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
        binding.input.setText(UserModel.getMatchData().getNotes());
        binding.characterLimit.setText("Character Limit: " + Objects.requireNonNull(binding.input.getText()).length() + "/150");
        binding.analyzerScore.setText("Analyzer Score: " + UserModel.getMatchData().getAnalyzerScore());
        return binding.getRoot();
    }
    

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.next.setOnClickListener(view1 -> {
            if (confirm) {
                try {
                    UserModel.getMatchData().toJson();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int num = Integer.parseInt(UserModel.getMatchData().getMatchNumber());
                num ++;
                num = num < 1 ? 1 : num;
                num = num > MainActivity.teams.size() ? MainActivity.teams.size() : num;
                UserModel.getMatchData().setMatchNumber(String.valueOf(num));
                NavHostFragment.findNavController(SecondFragment2.this).navigate(R.id.action_SecondFragment2_to_FirstFragment);
            } else {
                binding.next.setText("Ok");
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
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", UIHelpers.wolfFrames);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, animation, this.getContext()));

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
        ArrayAdapter<String> chainAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Attempt", "Failed Attempt", "Successful Attempt"});

        ArrayAdapter<String> harmonyAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Attempt", "Failed Attempt", "2 On Chain", "3 On Chain"});

        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
        binding.bottomTag.setText((MainActivity.scoutLocation < 3 ? "Red " : "Blue ") + (MainActivity.scoutLocation % 3 + 1));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    

}