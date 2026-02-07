package com.example.myapplication;

import android.annotation.SuppressLint;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.EndGameBinding;

import java.util.Objects;

public class EndGame extends Fragment {

    private EndGameBinding binding;
    ViewGroup v = null;

    private int currentRating = 0;
    private ImageView[] stars;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = EndGameBinding.inflate(inflater, container, false);
        v = container;

        stars = new ImageView[]{
                binding.star1,
                binding.star2,
                binding.star3,
                binding.star4,
                binding.star5
        };

        return binding.getRoot();
    }

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.ivLogo.setOnClickListener(view12 -> NavHostFragment.findNavController(EndGame.this)
                .navigate(R.id.action_SecondFragment2_to_SecondFragment));

        MatchData data = UserModel.getMatchData();

        setupClimbLevelButtons();

        setupSliders();

        setupStarRating();

        ((RadioButton) binding.radioGroupClimbLevel.getChildAt(data.getTeleOpClimb())).setChecked(true);
        binding.sliderDefense.setValue(data.getDefDuration());
        binding.sliderUnderDefense.setValue(data.getunderDefDuration());
        binding.sliderBroke.setValue(data.getBrokeDuration());
        currentRating = data.getDefEffectiveness();
        updateStars(currentRating);
        updateQualityBadge(currentRating);
        binding.tvDefenseValue.setText(String.valueOf(data.getDefDuration()));
        binding.tvUnderDefenseValue.setText(String.valueOf(data.getunderDefDuration()));
        binding.tvBrokeValue.setText(String.valueOf(data.getBrokeDuration()));
        binding.etMatchNotes.setText(data.getNotes());

        binding.tvTitle.setText("Endgame Team " + data.getTeamNumber());

        binding.tvReset.setOnClickListener(v -> UIHelpers.makeConfirmationAlert("Reset Data", "Do you want to reset all Endgame fields?", this::resetAllFields, () -> {}, getContext()));

        binding.etMatchNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                binding.characterLimit.setText("CHARACTER LIMIT: " + Objects.requireNonNull(binding.etMatchNotes.getText()).length() + "/150");
                data.setNotes(s.toString());
            }
        });

        binding.btnSubmit.setOnClickListener(view1 -> UIHelpers.makeConfirmationAlert("Submit Match Data",
                "Do you want to submit your match data?", () -> {
                    try {
                        data.toJson();
                        data.setMatchNumber(String.valueOf(Integer.parseInt(UserModel.getMatchData().getMatchNumber()) + 1));
                    } catch (Exception e) {
                        UIHelpers.makeHelpAlert("Unknown Data Transfer Error!", e.getMessage(), getContext());
                    }
                    NavHostFragment.findNavController(EndGame.this).navigate(R.id.action_SecondFragment2_to_FirstFragment);
                }, () -> {}, getContext()));


    }

    private void setupClimbLevelButtons() {
        MatchData data = UserModel.getMatchData();

        binding.radioGroupClimbLevel.setOnCheckedChangeListener((r, i) -> {
            int selectedIndex = binding.radioGroupClimbLevel.indexOfChild(binding.radioGroupClimbLevel.findViewById(i));
            data.setTeleOpClimb(selectedIndex);
            if (selectedIndex > 0){
                binding.climbImage.setVisibility(View.VISIBLE);
                binding.toggleGroupClimbLocation.setVisibility(View.VISIBLE);
            } else {
                binding.climbImage.setVisibility(View.GONE);
                binding.toggleGroupClimbLocation.setVisibility(View.GONE);
            }
        });

        ((RadioButton) binding.radioGroupClimbLevel.getChildAt(data.getTeleOpClimb())).setChecked(true);

        binding.toggleGroupClimbLocation.setOnCheckedChangeListener((r, i) -> data.setTeleOpClimbLocation(binding.toggleGroupClimbLocation.indexOfChild(binding.toggleGroupClimbLocation.findViewById(i)) + 1));

        ((RadioButton) binding.toggleGroupClimbLocation.getChildAt(data.getTeleOpClimbLocation() - 1)).setChecked(true);
    }


    private void setupSliders() {
        MatchData data = UserModel.getMatchData();

        binding.sliderDefense.addOnChangeListener((slider, value, fromUser) -> {
            binding.tvDefenseValue.setText(String.valueOf((int) value));
            data.setDefDuration((int) value);
        });

        binding.sliderUnderDefense.addOnChangeListener((slider, value, fromUser) -> {
            binding.tvUnderDefenseValue.setText(String.valueOf((int) value));
            data.setunderDefDuration((int) value);
        });

        binding.sliderBroke.addOnChangeListener((slider, value, fromUser) -> {
            binding.tvBrokeValue.setText(String.valueOf((int) value));
            data.setBrokeDuration((int) value);
        });
    }

    private void setupStarRating() {
        MatchData data = UserModel.getMatchData();

        updateStars(currentRating);

        for (int i = 0; i < stars.length; i++) {
            final int rating = i + 1;
            stars[i].setOnClickListener(v -> {
                currentRating = rating;
                updateStars(rating);
                updateQualityBadge(rating);
                data.setDefEffectiveness(rating);
            });
        }

        updateQualityBadge(currentRating);
    }

    private void updateStars(int rating) {
        for (int i = 0; i < stars.length; i++) {
            if (i < rating) {
                stars[i].setImageResource(android.R.drawable.btn_star_big_on);
                stars[i].setColorFilter(Color.parseColor("#0D59F2"), PorterDuff.Mode.SRC_IN);
            } else {
                stars[i].setImageResource(android.R.drawable.btn_star_big_off);
                stars[i].setColorFilter(Color.parseColor("#4A5568"), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    private void updateQualityBadge(int rating) {
        String badgeText;
        switch (rating) {
            case 1:
                badgeText = "VERY LOW";
                break;
            case 2:
                badgeText = "LOW";
                break;
            case 3:
                badgeText = "MEDIUM";
                break;
            case 4:
                badgeText = "HIGH";
                break;
            case 5:
                badgeText = "EXCELLENT";
                break;
            default:
                badgeText = "NONE";
                break;
        }
        binding.tvQualityBadge.setText(badgeText);
    }

    private void resetAllFields() {
        MatchData data = UserModel.getMatchData();

        ((RadioButton) binding.radioGroupClimbLevel.getChildAt(0)).setChecked(true);
        ((RadioButton) binding.toggleGroupClimbLocation.getChildAt(0)).setChecked(true);
        data.setTeleOpClimb(0);

        binding.sliderDefense.setValue(0);
        data.setDefDuration(0);

        binding.sliderUnderDefense.setValue(0);
        data.setunderDefDuration(0);

        binding.sliderBroke.setValue(0);
        data.setBrokeDuration(0);

        currentRating = 0;
        updateStars(currentRating);
        updateQualityBadge(currentRating);
        data.setDefEffectiveness(0);

        binding.etMatchNotes.setText("");
        data.setNotes("");
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}