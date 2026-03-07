package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;


import com.example.myapplication.databinding.AutonomousBinding;




public class Autonomous extends Fragment {

    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    private AutonomousBinding binding;
    ViewGroup v = null;
    @SuppressLint({"ObsoleteSdkInt", "SetTextI18n"})
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        binding = AutonomousBinding.inflate(inflater, container, false);
        v = container;

        return binding.getRoot();
    }

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MatchData data = UserModel.getMatchData();
        //initialization
        binding.fuelScored.setText(data.getAutoHub() + "");
        binding.fuelMissed.setText(data.getAutoHubMissed() + "");
        binding.title.setText("Autonomous Team " + data.getTeamNumber());

        binding.fuelScoredPlus.setOnClickListener(v -> {
            data.setAutoHub(data.getAutoHub() + 1);
            binding.fuelScored.setText(Integer.toString(data.getAutoHub()));
        });

        Runnable plusFuelScored = new Runnable() {
            @Override
            public void run() {
                data.setAutoHub(data.getAutoHub() + 1);
                binding.fuelScored.setText(Integer.toString(data.getAutoHub()));
                uiHandler.postDelayed(this, 100);
            }
        };

        Runnable plusFuelMissed = new Runnable() {
            @Override
            public void run() {
                data.setAutoHubMissed(data.getAutoHubMissed() + 1);
                binding.fuelMissed.setText(Integer.toString(data.getAutoHubMissed()));
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


        binding.fuelScoredMinus.setOnClickListener(v -> {
            data.setAutoHub(data.getAutoHub() <= 0 ? 0 : data.getAutoHub() - 1);
            binding.fuelScored.setText(Integer.toString(data.getAutoHub()));
        });

        binding.fuelMissedPlus.setOnClickListener(v -> {
            data.setAutoHubMissed(data.getAutoHubMissed() + 1);
            binding.fuelMissed.setText(Integer.toString(data.getAutoHubMissed()));
        });
        binding.fuelMissedMinus.setOnClickListener(v -> {
            data.setAutoHubMissed(data.getAutoHubMissed() <= 0 ? 0 : data.getAutoHubMissed() - 1);
            binding.fuelMissed.setText(Integer.toString(data.getAutoHubMissed()));
        });




        binding.toggleGroupClimbLevel.setOnCheckedChangeListener((r, i) -> {
            int climb = binding.toggleGroupClimbLevel.indexOfChild(binding.toggleGroupClimbLevel.findViewById(i));
            data.setAutoClimb(climb);
            if (climb > 0){
                binding.climbImage.setVisibility(View.VISIBLE);
                binding.toggleGroupClimbLocation.setVisibility(View.VISIBLE);
            } else {
                binding.climbImage.setVisibility(View.GONE);
                binding.toggleGroupClimbLocation.setVisibility(View.GONE);
            }
        });

        ((RadioButton) binding.toggleGroupClimbLevel.getChildAt(data.getAutoClimb())).setChecked(true); //Triggers listener

        binding.toggleGroupClimbLocation.setOnCheckedChangeListener((r, i) -> data.setAutoClimbLocation(binding.toggleGroupClimbLocation.indexOfChild(binding.toggleGroupClimbLocation.findViewById(i)) + 1));

        ((RadioButton) binding.toggleGroupClimbLocation.getChildAt(data.getAutoClimbLocation() - 1)).setChecked(true);

        binding.cont.setOnClickListener(view12 -> NavHostFragment.findNavController(Autonomous.this)
                .navigate(R.id.action_ThirdFragment_to_SecondFragment));

        binding.back.setOnClickListener(view1 -> UIHelpers.makeConfirmationAlert("Cancel Match Data", "Do you want to cancel your match data?", () -> NavHostFragment.findNavController(Autonomous.this)
                .navigate(R.id.action_ThirdFragment_to_FirstFragment), () -> {}, getContext()));

        binding.reset.setOnClickListener(v -> UIHelpers.makeConfirmationAlert("Reset Data", "Do you want to reset all Autonomous fields?", () -> {
            data.setAutoHub(0);
            data.setAutoHubMissed(0);
            binding.fuelScored.setText(data.getAutoHub() + "");
            binding.fuelMissed.setText(data.getAutoHubMissed() + "");
            ((RadioButton) binding.toggleGroupClimbLevel.getChildAt(0)).setChecked(true);
            ((RadioButton) binding.toggleGroupClimbLocation.getChildAt(0)).setChecked(true);
        }, () ->{}, getContext()));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}