package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import java.lang.reflect.Method;

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

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MatchData data = UserModel.getMatchData();
        //initialization

        TextView[] tv = new TextView[]{binding.fuelScored, binding.fuelMissed};
        Button[] plus = new Button[]{binding.fuelScoredPlus, binding.fuelMissedPlus};
        Button[] minus = new Button[]{binding.fuelScoredMinus, binding.fuelMissedMinus};
        String[] setters = {"setAutoHub", "setAutoHubMissed"};
        String[] getters = {"getAutoHub", "getAutoHubMissed"};

        for (int i = 0; i < tv.length; i++) {
            final int index = i;
            try {
                Method getMethod = data.getClass().getMethod(getters[i]);
                Method setMethod = data.getClass().getMethod(setters[i], getMethod.getReturnType());

                getMethod.invoke(data);
                tv[i].setText(getMethod.invoke(data) + "");

                Runnable plusFuel = new Runnable() {
                    @Override
                    public void run() {
                        try{
                            setMethod.invoke(data, (int) getMethod.invoke(data) + 1);
                            tv[index].setText(Integer.toString((int) getMethod.invoke(data)));
                            uiHandler.postDelayed(this, 100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                plus[i].setOnLongClickListener(v -> {
                    uiHandler.postDelayed(plusFuel, 100);
                    return true;
                });

                plus[i].setOnTouchListener((v, event) -> {
                    if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                        uiHandler.removeCallbacks(plusFuel);
                    }
                    return false;
                });

                plus[i].setOnClickListener(v -> {
                    try {
                        setMethod.invoke(data, (int) getMethod.invoke(data) + 1);
                        tv[index].setText(Integer.toString((int) getMethod.invoke(data)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                minus[i].setOnClickListener(v -> {
                    try {
                        setMethod.invoke(data, (int) getMethod.invoke(data) <= 0 ? 0 : (int) getMethod.invoke(data) - 1);
                        tv[index].setText(Integer.toString((int) getMethod.invoke(data)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        binding.title.setText("Autonomous Team " + data.getTeamNumber());

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