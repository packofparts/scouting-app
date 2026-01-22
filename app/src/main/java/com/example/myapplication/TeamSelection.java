package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.TeamSelectionBinding;
import com.google.android.material.snackbar.Snackbar;

public class TeamSelection extends Fragment {
    private TeamSelectionBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = TeamSelectionBinding.inflate(inflater, container, false);

        String currentMatchNumber = UserModel.getMatchData().getMatchNumber();
        binding.matchNumberInput.setText(currentMatchNumber);
        String scouterName = UserModel.getMatchData().getScouterName();
        binding.scouterNameInput.setText(scouterName);
        try {
            String currentTeamNumber = MainActivity.teams.get(Integer.parseInt(currentMatchNumber) - 1);
            binding.teamNumberInput.setText(currentTeamNumber);
        } catch (Exception e){
            //e.printStackTrace();
            //commented out for nwo. cauz no need to print stack trace
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

        binding.startScoutingButton.setOnClickListener(v -> {
            String teamNumber = String.valueOf(binding.teamNumberInput.getText());
            String matchNumber = String.valueOf(binding.matchNumberInput.getText());
            String scouterName = String.valueOf(binding.scouterNameInput.getText());
            boolean teamNumberCheck = (!teamNumber.isEmpty() && teamNumber.length() <= 5 && !teamNumber.equals("0"));
            boolean matchNumCheck = (!matchNumber.isEmpty() && !matchNumber.equals("0"));
            boolean scouterNameCheck = (!scouterName.isEmpty());
            if (teamNumberCheck && matchNumCheck && scouterNameCheck) {
                UserModel.getMatchData().setTeamNumber(teamNumber);
                UserModel.getMatchData().setMatchNumber(matchNumber);
                UserModel.getMatchData().setScouterName(scouterName);
                NavHostFragment.findNavController(TeamSelection.this)
                        .navigate(R.id.action_FirstFragment_to_ThirdFragment);
            } else {
                if (!teamNumberCheck) {
                    Snackbar.make(view, "Invalid team number", 600).show();
                }
                if (!matchNumCheck) {
                    Snackbar.make(view, "Invalid match number", 600).show();
                }
                if(!scouterNameCheck){
                    Snackbar.make(view, "Please enter a name", 600).show();
                }
            }
        });

        binding.pitScoutingButton.setOnClickListener(v -> {
            String teamNumber = String.valueOf(binding.teamNumberInput.getText());
            boolean teamNumberCheck = (!teamNumber.isEmpty() && teamNumber.length() <= 5 && !teamNumber.equals("0"));
            if (teamNumberCheck) {
                UserModel.getPitData().setTeamNumber(teamNumber);
                NavHostFragment.findNavController(TeamSelection.this)
                        .navigate(R.id.action_FirstFragment_to_HomePage);
            } else {
                Snackbar.make(view, "Invalid team number", 600).show();
            }
        });

        binding.teamNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Editable input = binding.teamNumberInput.getText();
                if (input != null) {
                    String teamNumber = String.valueOf(input);
                    UserModel.getMatchData().setTeamNumber(teamNumber);
                }
            }
        });

        binding.matchNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Editable input = binding.matchNumberInput.getText();
                if (input != null) {
                    String matchNumber = String.valueOf(input);
                    UserModel.getMatchData().setMatchNumber(matchNumber);
                    try {
                        int num = Integer.parseInt(matchNumber) - 1;
                        if (num >= 0 && num < MainActivity.teams.size()) {
                            binding.teamNumberInput.setText(MainActivity.teams.get(num));
                        } else {
                            Snackbar.make(view, "Match number is too high/low.", 600).show();
                            if (matchNumber.equals("0")) {
                                binding.matchNumberInput.setText("1");
                                UserModel.getMatchData().setMatchNumber("1");
                                binding.teamNumberInput.setText(MainActivity.teams.get(0));
                            } else {
                                binding.teamNumberInput.setText("");
                            }
                        }
                    } catch (Exception e){
                        Snackbar.make(view, "Please Enter a Value", 600).show();
                    }
                }
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, MainActivity.names);
        binding.scouterNameInput.setAdapter(adapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

