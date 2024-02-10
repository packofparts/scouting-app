package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    ViewGroup v;
    @Override
    public View onCreateView(
            LayoutInflater  inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        v = container;
        binding.input.setText(MainActivity.teamNumber.equals("0") ? "" : MainActivity.teamNumber);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        
        binding.cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.teamNumber.length() > 0 && MainActivity.teamNumber.length() < 6) {
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_ThirdFragment);
                } else {
                    Snackbar.make(view, "Invalid team number", 600).show();
                    MainActivity.teamNumber = "0";
                    binding.input.setText(MainActivity.teamNumber.equals("0") ? "" : MainActivity.teamNumber);
                }
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_HomePage);
            }
        });
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", new float[]{0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f});
        animation.setDuration(1000);
        binding.pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation.start();
                MainActivity.darkMode = !MainActivity.darkMode;
                UIHelpers.lightDark(v, MainActivity.darkMode);
            }
        });
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        binding.title.setTranslationX(height * 0.072f);
        binding.title.setTranslationX(width * 0.146f);
        binding.back.setTranslationY(height * 0.170f);
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
                MainActivity.teamNumber = binding.input.getText() + "";
            }
        });
        binding.cont.setTranslationY(height * 0.170f);
        binding.cont.setTranslationX(width * 0.707f);
        binding.pop.setTranslationY(height * 0.719f);
        binding.pop.setTranslationX(width * 0.073f);
        UIHelpers.lightDark(v, MainActivity.darkMode);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}