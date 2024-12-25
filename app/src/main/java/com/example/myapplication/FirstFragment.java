package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    ViewGroup v;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        v = container;

        String currentMatchNumber = UserModel.getMatchData().getMatchNumber();
        binding.matchInput.setText(currentMatchNumber);
        try {
            String currentTeamNumber = MainActivity.teams.get(Integer.parseInt(currentMatchNumber) - 1);
            binding.input.setText(currentTeamNumber);
        } catch (Exception e){
            e.printStackTrace();
        }

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        UserModel userModel = viewModelProvider.get(UserModel.class);
        MatchData matchData = new MatchData();
        userModel.setMatchData(matchData);
        userModel.setPitData(new PitData());

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cont.setOnClickListener(v -> {
            String teamNumber = String.valueOf(binding.input.getText());
            String matchNumber = String.valueOf(binding.matchInput.getText());
            boolean teamNumberCheck = (!teamNumber.isEmpty() && teamNumber.length() < 5 && !teamNumber.equals("0"));
            boolean matchNumCheck = (!matchNumber.isEmpty() && !matchNumber.equals("0"));
            if (teamNumberCheck && matchNumCheck) {
                UserModel.getMatchData().setTeamNumber(teamNumber);
                UserModel.getMatchData().setMatchNumber(matchNumber);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_ThirdFragment);
            } else {
                if (!teamNumberCheck) {
                    Snackbar.make(view, "Invalid team number", 600).show();
                }
                if (!matchNumCheck) {
                    Snackbar.make(view, "Invalid match number", 600).show();
                }
            }
        });

        binding.back.setOnClickListener(v -> {
            String teamNumber = String.valueOf(binding.input.getText());
            boolean teamNumberCheck = (!teamNumber.isEmpty() && teamNumber.length() < 5 && !teamNumber.equals("0"));
            if (teamNumberCheck) {
                UserModel.getPitData().setTeamNumber(teamNumber);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_HomePage);
            } else {
                Snackbar.make(view, "Invalid team number", 600).show();
            }
        });

        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", UIHelpers.wolfFrames);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, animation, this.getContext()));

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;


        binding.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Editable input = binding.input.getText();
                if (input != null) {
                    String teamNumber = String.valueOf(input);
                    UserModel.getMatchData().setTeamNumber(teamNumber);
                }
            }
        });

        binding.teamNumberHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Team Number", "This is the number of the team that you will be scouting!", getContext()));
        binding.matchNumberHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Match Number", "This is the match number that we are currently on!", getContext()));

        binding.matchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Editable input = binding.matchInput.getText();
                if (input != null) {
                    String matchNumber = String.valueOf(input);
                    UserModel.getMatchData().setMatchNumber(matchNumber);
                    try {
                        int num = Integer.parseInt(matchNumber) - 1;
                        if (num >= 0 && num < MainActivity.teams.size()) {
                            binding.input.setText(MainActivity.teams.get(num));
                        } else {
                            Snackbar.make(view, "Match number is too high/low.", 600).show();
                            if (matchNumber.equals("0")) {
                                binding.matchInput.setText("1");
                                UserModel.getMatchData().setMatchNumber("1");
                                binding.input.setText(MainActivity.teams.get(0));
                            } else {
                                binding.matchInput.setText(String.valueOf(MainActivity.teams.size()));
                                UserModel.getMatchData().setMatchNumber(String.valueOf(MainActivity.teams.size()));
                                binding.input.setText(MainActivity.teams.get(MainActivity.teams.size() - 1));
                            }
                        }
                    } catch (Exception e){
                        Snackbar.make(view, "Please Enter a Value", 600).show();
                    }
                }
            }
        });
        binding.bottomTag.setText((MainActivity.scoutLocation < 3 ? "Red " : "Blue ") + (MainActivity.scoutLocation % 3 + 1));

        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

