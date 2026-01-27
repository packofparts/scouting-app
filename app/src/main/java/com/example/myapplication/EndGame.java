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

import com.example.myapplication.databinding.EndGameBinding;

public class EndGame extends Fragment {

    private EndGameBinding binding;
    ViewGroup v = null;

    private int currentRating = 4;
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

        binding.rbNone.setButtonDrawable(null);
        binding.rbFail.setButtonDrawable(null);
        binding.rbL1.setButtonDrawable(null);
        binding.rbL2.setButtonDrawable(null);
        binding.rbL3.setButtonDrawable(null);

        return binding.getRoot();
    }

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MatchData data = UserModel.getMatchData();

        setupClimbLevelButtons();

        setupSliders();

        setupStarRating();

        binding.tvReset.setOnClickListener(v -> resetAllFields());

        binding.etMatchNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                data.setNotes(s.toString());
            }
        });

        binding.btnSubmit.setOnClickListener(view1 -> UIHelpers.makeConfirmationAlert("Submit Match Data",
                "Do you want to submit your match data?", () -> {
                }, () -> {}, getContext()));

    }

    private void setupClimbLevelButtons() {
        MatchData data = UserModel.getMatchData();

        ((RadioButton) binding.radioGroupClimbLevel.getChildAt(data.getTeleOpClimb())).setChecked(true);

        binding.radioGroupClimbLevel.setOnCheckedChangeListener((r, i) -> {
            int selectedIndex = binding.radioGroupClimbLevel.indexOfChild(binding.radioGroupClimbLevel.findViewById(i));
            data.setTeleOpClimb(selectedIndex);
            RadioButton[] buttons = {
                    binding.rbNone,
                    binding.rbFail,
                    binding.rbL1,
                    binding.rbL2,
                    binding.rbL3
            };
            updateClimbButtons(buttons, selectedIndex);
        });
    }

    private void updateClimbButtons(RadioButton[] buttons, int selectedIndex) {
        for (int i = 0; i < buttons.length; i++) {
            if (i == selectedIndex) {
                buttons[i].setBackgroundResource(R.drawable.cr6b0d59f2);
                buttons[i].setTextColor(Color.WHITE);
                buttons[i].setTypeface(null, android.graphics.Typeface.BOLD);
                buttons[i].setElevation(4f);
            } else {
                buttons[i].setBackground(null);
                buttons[i].setTextColor(Color.parseColor("#94A3B8"));
                buttons[i].setTypeface(null, android.graphics.Typeface.NORMAL);
                buttons[i].setElevation(0f);
            }
        }
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

        ((RadioButton) binding.radioGroupClimbLevel.getChildAt(4)).setChecked(true);
        data.setTeleOpClimb(4);

        binding.sliderDefense.setValue(30);
        data.setDefDuration(30);

        binding.sliderUnderDefense.setValue(0);
        data.setunderDefDuration(0);

        binding.sliderBroke.setValue(0);
        data.setBrokeDuration(0);

        currentRating = 4;
        updateStars(currentRating);
        updateQualityBadge(currentRating);
        data.setDefEffectiveness(4);

        binding.etMatchNotes.setText("");
        data.setNotes("");
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}