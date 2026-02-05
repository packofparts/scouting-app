package com.example.myapplication;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.TeamSelectionBinding;
import com.google.android.material.snackbar.Snackbar;


import java.util.Objects;
import java.util.Random;

public class TeamSelection extends Fragment {
    private TeamSelectionBinding binding;

    private ViewGroup v;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = TeamSelectionBinding.inflate(inflater, container, false);

        v = container;

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.subtitleText.setText(MainActivity.getLocationText());

        binding.logoImage.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, binding.logoImage, this.getContext()));

        binding.startScoutingButton.setOnClickListener(v -> {
            String teamNumber = String.valueOf(binding.teamNumberInput.getText());
            String matchNumber = String.valueOf(binding.matchInputContainer.getText());
            String scouterName = String.valueOf(binding.scouterNameInput.getText());
            boolean teamNumberCheck = (!teamNumber.isEmpty() && teamNumber.length() <= 5 && !teamNumber.equals("0"));
            boolean matchNumCheck = (!matchNumber.isEmpty() && !matchNumber.equals("0"));
            boolean scouterNameCheck = (!scouterName.isEmpty());
            if (teamNumberCheck && matchNumCheck && scouterNameCheck) {
                Animation bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                v.startAnimation(bounce);
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

        binding.random.setOnClickListener(v -> {
            Animation bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
            v.startAnimation(bounce);
            int randomIndex = (new Random()).nextInt(MainActivity.names.size());
            binding.scouterNameInput.setText(MainActivity.names.get(randomIndex));
        });


        binding.matchInputContainer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                Editable input = binding.matchInputContainer.getText();
                if (input != null) {
                    String matchNumber = String.valueOf(input);
                    UserModel.getMatchData().setMatchNumber(matchNumber);
                    try {
                        int num = Integer.parseInt(matchNumber) - 1;
                        if (num >= 0 && num < MainActivity.teams.size()) {
                            binding.teamNumberInput.setText(MainActivity.teams.get(num)[MainActivity.scoutLocation]);

                            LinearLayout[] matchBoxes = {binding.Match1, binding.Match2, binding.Match3};
                            TextView[] matchBoxNums = {binding.matchBox1Num, binding.matchBox2Num, binding.matchBox3Num};
                            for(int i = 0; i < 3; i++){
                                int currentMatchIndex = num + i;

                                if (currentMatchIndex >= MainActivity.teams.size()) {
                                    matchBoxes[i].setVisibility(View.GONE);
                                    matchBoxNums[i].setVisibility(View.GONE);
                                    continue;
                                }
                                matchBoxes[i].setVisibility(View.VISIBLE);
                                matchBoxNums[i].setVisibility(View.VISIBLE);

                                LinearLayout box = matchBoxes[i];
                                TextView match = matchBoxNums[i];
                                match.setText("#" + (currentMatchIndex + 1));
                                String[] currentTeams = MainActivity.teams.get(currentMatchIndex);
                                for(int j = 0; j < 6; j++) {
                                    TextView child = (TextView) box.getChildAt(j);
                                    if (j < currentTeams.length) {
                                        child.setText(currentTeams[j]);
                                    } else {
                                        child.setText("");
                                    }
                                }
                            }


                        } else {
                            Snackbar.make(view, "Match number is too high/low.", 600).show();
                            if (matchNumber.equals("0")) {
                                binding.matchInputContainer.setText("1");
                                UserModel.getMatchData().setMatchNumber("1");
                                binding.teamNumberInput.setText(MainActivity.teams.get(0)[MainActivity.scoutLocation]);
                            } else {
                                binding.teamNumberInput.setText("");
                            }
                        }
                    } catch (Exception ignored){

                    }
                }
            }
        });

        String currentMatchNumber = UserModel.getMatchData().getMatchNumber();
        binding.matchInputContainer.setText(currentMatchNumber);
        String scouterName = UserModel.getMatchData().getScouterName();
        binding.scouterNameInput.setText(scouterName);
        try {
            String currentTeamNumber = MainActivity.teams.get(Integer.parseInt(currentMatchNumber) - 1)[MainActivity.scoutLocation];
            binding.teamNumberInput.setText(currentTeamNumber);
        } catch (Exception ignored){

        }

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        UserModel userModel = viewModelProvider.get(UserModel.class);
        MatchData matchData = new MatchData();
        userModel.setMatchData(matchData);
        UserModel.setPitData(new PitData());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, MainActivity.names);
        binding.scouterNameInput.setAdapter(adapter);

        //the location of the scouting changing thing
        binding.subtitleText.setOnLongClickListener(lc -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Set Scout Location");
            builder.setOnDismissListener(d -> {
                MainActivity.updateTeams(getResources());
                binding.subtitleText.setText(MainActivity.getLocationText());

            });
            builder.setNeutralButton("Cancel", (d, w) -> d.cancel());
            builder.setItems(new CharSequence[]{"Red 1", "Red 2", "Red 3", "Blue 1", "Blue 2", "Blue 3"}, (d, w) -> {
                MainActivity.scoutLocation = w;
                ((MainActivity) requireActivity()).writeInt("ScoutLocation", w);
            });
            builder.create().show();
            return false;
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

