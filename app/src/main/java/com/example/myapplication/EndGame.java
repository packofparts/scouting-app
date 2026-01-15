package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.EndGameBinding;
import com.google.android.material.slider.Slider;

import java.io.IOException;
import java.util.Objects;

public class EndGame extends Fragment {

    private EndGameBinding binding;
    ViewGroup v = null;

    // Climb level selection
    private String selectedClimbLevel = "L3"; // Default selection

    // Star rating
    private int currentRating = 4; // Default 4 stars
    private ImageView[] stars;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = EndGameBinding.inflate(inflater, container, false);
        v = container;

        // Initialize star array
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

        // Setup climb level toggle buttons
        setupClimbLevelButtons();

        // Setup sliders
        setupSliders();

        // Setup star rating
        setupStarRating();

        // Reset button
        binding.tvReset.setOnClickListener(v -> resetAllFields());

        // Match notes input
        binding.etMatchNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                // Save notes to your data model
                // UserModel.getMatchData().setNotes(s.toString());
            }
        });

        // Submit button
        binding.btnSubmit.setOnClickListener(view1 -> {
            UIHelpers.makeConfirmationAlert("Submit Match Data",
                    "Do you want to submit your match data?", () -> {
                        try {
                            saveMatchData();
                            // UserModel.getMatchData().toJson();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        // Navigate to next screen or clear data
                    }, () -> {}, getContext());
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;

        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
    }

    private void setupClimbLevelButtons() {
        TextView[] buttons = {
                binding.btnNone,
                binding.btnFail,
                binding.btnL1,
                binding.btnL2,
                binding.btnL3
        };

        String[] levels = {"None", "Fail", "L1", "L2", "L3"};

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(v -> {
                selectedClimbLevel = levels[index];
                updateClimbButtons(buttons, index);
            });
        }

        // Set initial selection (L3 is selected by default)
        updateClimbButtons(buttons, 4);
    }

    private void updateClimbButtons(TextView[] buttons, int selectedIndex) {
        for (int i = 0; i < buttons.length; i++) {
            if (i == selectedIndex) {
                // Selected state
                buttons[i].setBackgroundResource(R.drawable.cr6b0d59f2);
                buttons[i].setTextColor(Color.WHITE);
                buttons[i].setTypeface(null, android.graphics.Typeface.BOLD);
                buttons[i].setElevation(4f);
            } else {
                // Unselected state
                buttons[i].setBackground(null);
                buttons[i].setTextColor(Color.parseColor("#94A3B8"));
                buttons[i].setTypeface(null, android.graphics.Typeface.NORMAL);
                buttons[i].setElevation(0f);
            }
        }
    }

    private void setupSliders() {
        // Defense Duration Slider
        binding.sliderDefense.addOnChangeListener((slider, value, fromUser) -> {
            binding.tvDefenseValue.setText(String.valueOf((int) value));
        });

        // Under Defense Slider
        binding.sliderUnderDefense.addOnChangeListener((slider, value, fromUser) -> {
            binding.tvUnderDefenseValue.setText(String.valueOf((int) value));
        });

        // Broke Duration Slider
        binding.sliderBroke.addOnChangeListener((slider, value, fromUser) -> {
            binding.tvBrokeValue.setText(String.valueOf((int) value));
        });
    }

    private void setupStarRating() {
        // Set initial rating
        updateStars(currentRating);

        // Add click listeners to stars
        for (int i = 0; i < stars.length; i++) {
            final int rating = i + 1;
            stars[i].setOnClickListener(v -> {
                currentRating = rating;
                updateStars(rating);
                updateQualityBadge(rating);
            });
        }

        // Set initial badge
        updateQualityBadge(currentRating);
    }

    private void updateStars(int rating) {
        for (int i = 0; i < stars.length; i++) {
            if (i < rating) {
                // Filled star
                stars[i].setImageResource(android.R.drawable.btn_star_big_on);
                stars[i].setColorFilter(Color.parseColor("#0D59F2"), PorterDuff.Mode.SRC_IN);
            } else {
                // Empty star
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
        // Reset climb level to default
        selectedClimbLevel = "L3";
        TextView[] buttons = {
                binding.btnNone,
                binding.btnFail,
                binding.btnL1,
                binding.btnL2,
                binding.btnL3
        };
        updateClimbButtons(buttons, 4);

        // Reset sliders
        binding.sliderDefense.setValue(30);
        binding.sliderUnderDefense.setValue(0);
        binding.sliderBroke.setValue(0);

        // Reset star rating
        currentRating = 4;
        updateStars(currentRating);
        updateQualityBadge(currentRating);

        // Clear notes
        binding.etMatchNotes.setText("");
    }

    private void saveMatchData() {
        // Save all data to your model
        int defenseDuration = (int) binding.sliderDefense.getValue();
        int underDefenseDuration = (int) binding.sliderUnderDefense.getValue();
        int brokeDuration = (int) binding.sliderBroke.getValue();
        String notes = binding.etMatchNotes.getText().toString();

        // Example: Save to UserModel
        // UserModel.getMatchData().setClimbLevel(selectedClimbLevel);
        // UserModel.getMatchData().setDefenseDuration(defenseDuration);
        // UserModel.getMatchData().setUnderDefenseDuration(underDefenseDuration);
        // UserModel.getMatchData().setBrokeDuration(brokeDuration);
        // UserModel.getMatchData().setDefenseQuality(currentRating);
        // UserModel.getMatchData().setNotes(notes);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}