package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;


import com.example.myapplication.databinding.AutonomousBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Autonomous#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Autonomous extends Fragment {


    private AutonomousBinding binding;
    ViewGroup v = null;
    @SuppressLint({"ObsoleteSdkInt", "SetTextI18n"})
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        binding = AutonomousBinding.inflate(inflater, container, false);
        v = container;
        binding.team.setText("Team " + UserModel.getMatchData().getTeamNumber());

        return binding.getRoot();
    }

    @SuppressLint("ObsoleteSdkInt")
    public void checkedOperation (View v){
        if (v instanceof Switch){
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch s = (Switch) v;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                if (s.isChecked()){
                    s.setThumbTintList(UIHelpers.purpleAsList);
                    s.setTrackTintList(UIHelpers.purpleAsList);
                } else {
                    s.setThumbTintList(UIHelpers.teamColorAsList);
                    s.setTrackTintList(UIHelpers.teamColorAsList);
                }
        }
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.team.setText("Team " + UserModel.getMatchData().getTeamNumber());
        //VARIABLES

        binding.pop.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, binding.pop, this.getContext()));

        binding.cont.setOnClickListener(view12 -> NavHostFragment.findNavController(Autonomous.this)
                .navigate(R.id.action_ThirdFragment_to_SecondFragment));
        binding.back.setOnClickListener(view1 -> UIHelpers.makeConfirmationAlert("Cancel Match Data", "Do you want to cancel your match data?", () -> NavHostFragment.findNavController(Autonomous.this)
                .navigate(R.id.action_ThirdFragment_to_FirstFragment), () -> {}, getContext()));
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
        binding.bottomTag.setText(MainActivity.getLocationText());

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public Autonomous() {
        // Required empty public constructor
    }

    public static Autonomous newInstance(String param1, String param2) {
        Autonomous fragment = new Autonomous();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
}