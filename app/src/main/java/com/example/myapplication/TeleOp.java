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

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MatchData data = UserModel.getMatchData();
        //initialization
        TextView[] tv = new TextView[]{binding.fuelScored, binding.fuelMissed, binding.fuelPassed};
        Button[] plus = new Button[]{binding.fuelScoredPlus, binding.fuelMissedPlus, binding.fuelPassedPlus};
        Button[] minus = new Button[]{binding.fuelScoredMinus, binding.fuelMissedMinus, binding.fuelPassedMinus};
        String[] setters = {"setTeleOpHub", "setTeleOpHubMissed", "setTeleOpPassed"};
        String[] getters = {"getTeleOpHub", "getTeleOpHubMissed", "getTeleOpPassed"};

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
        binding.cont.setOnClickListener(v -> NavHostFragment.findNavController(TeleOp.this)
                .navigate(R.id.action_SecondFragment_to_SecondFragment2));

        binding.back.setOnClickListener(v-> NavHostFragment.findNavController(TeleOp.this).navigate(R.id.action_SecondFragment_to_ThirdFragment));

        binding.reset.setOnClickListener(v -> UIHelpers.makeConfirmationAlert("Reset Data", "Do you want to reset all Tele-Operated fields?", () -> {
            data.setTeleOpHub(0);
            data.setTeleOpHubMissed(0);
            data.setTeleOpPassed(0);
            binding.fuelScored.setText(data.getTeleOpHub() + "");
            binding.fuelMissed.setText(data.getTeleOpHubMissed() + "");
            binding.fuelPassed.setText(data.getTeleOpPassed() + "");
        }, () ->{}, getContext()));
    }
   
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;

    }
}

