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
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;
public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    ViewGroup v;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater  inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        v = container;
        int currentTeamNumber = UserModel.getMatchData().getTeamNumber();
        binding.input.setText(currentTeamNumber == 0 ? "" : String.valueOf(currentTeamNumber));

        int currentMatchNumber = UserModel.getMatchData().getTeamNumber();
        binding.input.setText(currentMatchNumber == 0 ? "" : String.valueOf(currentMatchNumber));

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                binding.cont.setOnClickListener(v -> {
            String teamNumber = String.valueOf(binding.input.getText());
            if (teamNumber.length() > 0 && teamNumber.length() < 6) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_ThirdFragment);
            } else {
                Snackbar.make(view, "Invalid team number", 600).show();
                binding.input.setText(teamNumber.equals("0") ? "" : teamNumber);
            }
        });
        binding.back.setOnClickListener(v -> NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_HomePage));
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", 0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> {
            animation.start();
            UIHelpers.darkMode = !UIHelpers.darkMode;
            UIHelpers.lightDark(v, UIHelpers.darkMode);
        });
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        binding.title.setTranslationX(height * 0.072f);
        binding.title.setTranslationX(width * 0.146f);
        binding.back.setTranslationY(height * 0.270f);
        binding.back.setTranslationX(width * 0.024f);
        binding.input.setTranslationY(height * 0.158f);

        binding.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.input.getText() == null){
                    return;
                }
                String teamNumber = String.valueOf(binding.input.getText());
                UserModel.getMatchData().setTeamNumber(Integer.parseInt(teamNumber));
            }
        });

        binding.matchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.matchInput.getText() == null){
                    return;
                }
                String matchNumber = String.valueOf(binding.matchInput.getText());
                UserModel.getMatchData().setMatchNumber(Integer.parseInt(matchNumber));
            }
        });
        binding.cont.setTranslationY(height * 0.270f);
        binding.cont.setTranslationX(width * 0.707f);
        binding.pop.setTranslationY(height * 0.719f);
        binding.pop.setTranslationX(width * 0.073f);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



