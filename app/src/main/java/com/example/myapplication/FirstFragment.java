package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
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

        }

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        UserModel userModel = viewModelProvider.get(UserModel.class);
        MatchData matchData = new MatchData();
        userModel.setMatchData(matchData);

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

        binding.back.setOnClickListener(v -> NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_HomePage));

        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", 0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, animation, this.getContext(), () -> binding.location.setTextColor(Color.parseColor((MainActivity.scoutLocation < 3 ? "#FF0000" : "#0000FF")))));

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;

        /*binding.title.setTranslationY(height * 0.072f);\
        binding.title.setTranslationX(width * 0.146f);
        binding.back.setTranslationY(height * 0.288f);
        binding.back.setTranslationX(width * 0.024f);
        binding.cont.setTranslationY(height * 0.288f);
        binding.cont.setTranslationX(width * 0.707f);
        binding.input.setTranslationY(height * 0.158f);
        binding.matchInput.setTranslationY(height * 0.158f);*/

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
                        binding.input.setText(MainActivity.teams.get(Integer.parseInt(matchNumber) - 1));
                    } catch (IndexOutOfBoundsException e){
                        if (matchNumber.equals("0")) {
                            binding.matchInput.setText("1");
                            UserModel.getMatchData().setMatchNumber("1");
                            binding.input.setText(MainActivity.teams.get(0));
                        } else {
                            binding.matchInput.setText(String.valueOf(MainActivity.teams.size()));
                            UserModel.getMatchData().setMatchNumber(String.valueOf(MainActivity.teams.size()));
                            binding.input.setText(MainActivity.teams.get(MainActivity.teams.size() - 1));
                        }
                    } catch (Exception e){

                    }
                }
            }
        });
        binding.location.setText((MainActivity.scoutLocation < 3 ? "Red " : "Blue ") + (MainActivity.scoutLocation % 3 + 1));

        /*binding.cont.setTranslationY(height * 0.270f);
        binding.cont.setTranslationX(width * 0.707f);
        binding.pop.setTranslationY(height * 0.719f);
        binding.pop.setTranslationX(width * 0.073f);*/
        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
        binding.location.setTextColor(Color.parseColor((MainActivity.scoutLocation < 3 ? "#FF0000" : "#0000FF")));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

