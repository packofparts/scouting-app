package com.example.myapplication;


import android.annotation.SuppressLint;

import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.TeleOpBinding;

import java.lang.reflect.Method;


public class TeleOp extends Fragment {

    private TeleOpBinding binding;
    private final Handler uiHandler = new Handler(Looper.getMainLooper());


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TeleOpBinding.inflate(inflater, container, false);

        binding.title.setText("Tele-Operated Team " + UserModel.getMatchData().getTeamNumber());

        return binding.getRoot();
    }

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MatchData data = UserModel.getMatchData();
        //initialization
        binding.fuelScored.setText(data.getTeleOpHub() + "");
        binding.fuelMissed.setText(data.getTeleOpHubMissed() + "");
        binding.fuelPassed.setText(data.getTeleOpPassed() + "");

        binding.cont.setOnClickListener(view1 -> NavHostFragment.findNavController(TeleOp.this)
                .navigate(R.id.action_SecondFragment_to_SecondFragment2));
        binding.back.setOnClickListener(view12 -> NavHostFragment.findNavController(TeleOp.this)
                .navigate(R.id.action_SecondFragment_to_ThirdFragment));



        binding.fuelScoredPlus.setOnClickListener(v -> {
            data.setTeleOpHub(data.getTeleOpHub() + 1);
            binding.fuelScored.setText(Integer.toString(data.getTeleOpHub()));
        });

        Runnable plusFuelScored = new Runnable() {
            @Override
            public void run() {
                data.setTeleOpHub(data.getTeleOpHub() + 1);
                binding.fuelScored.setText(Integer.toString(data.getTeleOpHub()));
                uiHandler.postDelayed(this, 100);
            }
        };

        Runnable plusFuelMissed = new Runnable() {
            @Override
            public void run() {
                data.setTeleOpHubMissed(data.getTeleOpHubMissed() + 1);
                binding.fuelMissed.setText(Integer.toString(data.getTeleOpHubMissed()));
                uiHandler.postDelayed(this, 100);
            }
        };

        Runnable plusFuelPassed = new Runnable() {
            @Override
            public void run() {
                data.setTeleOpPassed(data.getTeleOpPassed() + 1);
                binding.fuelPassed.setText(Integer.toString(data.getTeleOpPassed()));
                uiHandler.postDelayed(this, 100);
            }
        };

        binding.fuelScoredPlus.setOnLongClickListener(v -> {
            uiHandler.postDelayed(plusFuelScored, 100);
            return true;
        });

        binding.fuelMissedPlus.setOnLongClickListener(v -> {
            uiHandler.postDelayed(plusFuelMissed, 100);
            return true;
        });

        binding.fuelPassedPlus.setOnLongClickListener(v -> {
            uiHandler.postDelayed(plusFuelPassed, 100);
            return true;
        });

        binding.fuelScoredPlus.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP ||
                    event.getAction() == MotionEvent.ACTION_CANCEL) {
                uiHandler.removeCallbacks(plusFuelScored); // stop the loop
            }
            return false; // return false so long click still fires
        });

        binding.fuelMissedPlus.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP ||
                    event.getAction() == MotionEvent.ACTION_CANCEL) {
                uiHandler.removeCallbacks(plusFuelMissed); // stop the loop
            }
            return false; // return false so long click still fires
        });

        binding.fuelPassedPlus.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP ||
                    event.getAction() == MotionEvent.ACTION_CANCEL) {
                uiHandler.removeCallbacks(plusFuelPassed); // stop the loop
            }
            return false; // return false so long click still fires
        });

        binding.fuelScoredMinus.setOnClickListener(v -> {
            data.setTeleOpHub(data.getTeleOpHub() <= 0 ? 0 : data.getTeleOpHub() - 1);
            binding.fuelScored.setText(Integer.toString(data.getTeleOpHub()));
        });

        binding.fuelMissedPlus.setOnClickListener(v -> {
            data.setTeleOpHubMissed(data.getTeleOpHubMissed() + 1);
            binding.fuelMissed.setText(Integer.toString(data.getTeleOpHubMissed()));
        });
        binding.fuelMissedMinus.setOnClickListener(v -> {
            data.setTeleOpHubMissed(data.getTeleOpHubMissed() <= 0 ? 0 : data.getTeleOpHubMissed() - 1);
            binding.fuelMissed.setText(Integer.toString(data.getTeleOpHubMissed()));
        });

        binding.fuelPassedPlus.setOnClickListener(v -> {
            data.setTeleOpPassed(data.getTeleOpPassed() + 1);
            binding.fuelPassed.setText(Integer.toString(data.getTeleOpPassed()));
        });
        binding.fuelPassedMinus.setOnClickListener(v -> {
            data.setTeleOpPassed(data.getTeleOpPassed() <= 0 ? 0 : data.getTeleOpPassed() - 1);
            binding.fuelPassed.setText(Integer.toString(data.getTeleOpPassed()));
        });
    }
   
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;

    }
}

