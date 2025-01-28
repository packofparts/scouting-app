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
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.animation.ObjectAnimator;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SpinnerAdapter;

import com.example.myapplication.databinding.FragmentHomepageBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Objects;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.myapplication.databinding.FragmentThirdBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ThirdFragment extends Fragment {


    private FragmentThirdBinding binding;
    ViewGroup v = null;
    @SuppressLint({"ObsoleteSdkInt", "SetTextI18n"})
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        v = container;
        binding.team.setText("Team " + UserModel.getMatchData().getTeamNumber());
        binding.L4Display.setText(String.valueOf(UserModel.getMatchData().getAutoL4()));
        binding.L3Display.setText(String.valueOf(UserModel.getMatchData().getAutoL3()));
        binding.L2Display.setText(String.valueOf(UserModel.getMatchData().getAutoL2()));
        binding.L1Display.setText(String.valueOf(UserModel.getMatchData().getAutoL1()));
        binding.L4Display.setText(String.valueOf(UserModel.getMatchData().getAutoL4()));
        binding.MissedCoralDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoMissedCoral()));
        binding.MissedDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoMissedAlgae()));
        binding.NetDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoNet()));
        binding.ProcessorDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoProcessor()));
        binding.moveOutOfZone.setChecked(UserModel.getMatchData().getMoveOutOfZone());
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

        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", UIHelpers.wolfFrames);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, animation, this.getContext()));

        binding.cont.setOnClickListener(view12 -> NavHostFragment.findNavController(ThirdFragment.this)
                .navigate(R.id.action_ThirdFragment_to_SecondFragment));
        binding.back.setOnClickListener(view1 -> UIHelpers.makeConfirmationAlert("Cancel Match Data", "Do you want to cancel your match data?", () -> NavHostFragment.findNavController(ThirdFragment.this)
                .navigate(R.id.action_ThirdFragment_to_FirstFragment), () -> {}, getContext()));
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
        binding.bottomTag.setText(MainActivity.getLocationText());
        binding.L4Minus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getAutoL4()>0){
                UserModel.getMatchData().setAutoL4(UserModel.getMatchData().getAutoL4()-1);
            }
        binding.L4Display.setText(String.valueOf(UserModel.getMatchData().getAutoL4()));
        });
        binding.L4Plus.setOnClickListener(v -> {
                UserModel.getMatchData().setAutoL4(UserModel.getMatchData().getAutoL4()+1);
            binding.L4Display.setText(String.valueOf(UserModel.getMatchData().getAutoL4()));
        });
        binding.L3Minus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getAutoL3()>0){
                UserModel.getMatchData().setAutoL3(UserModel.getMatchData().getAutoL3()-1);
            }
            binding.L3Display.setText(String.valueOf(UserModel.getMatchData().getAutoL3()));
        });
        binding.L3Plus.setOnClickListener(v -> {
            UserModel.getMatchData().setAutoL3(UserModel.getMatchData().getAutoL3()+1);
            binding.L3Display.setText(String.valueOf(UserModel.getMatchData().getAutoL3()));
        });
        binding.L2Minus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getAutoL2()>0){
                UserModel.getMatchData().setAutoL2(UserModel.getMatchData().getAutoL2()-1);
            }
            binding.L2Display.setText(String.valueOf(UserModel.getMatchData().getAutoL2()));
        });
        binding.L2Plus.setOnClickListener(v -> {
            UserModel.getMatchData().setAutoL2(UserModel.getMatchData().getAutoL2()+1);
            binding.L2Display.setText(String.valueOf(UserModel.getMatchData().getAutoL2()));
        });
        binding.L1Minus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getAutoL1()>0){
                UserModel.getMatchData().setAutoL1(UserModel.getMatchData().getAutoL1()-1);
            }
            binding.L1Display.setText(String.valueOf(UserModel.getMatchData().getAutoL1()));
        });
        binding.L1Plus.setOnClickListener(v -> {
        UserModel.getMatchData().setAutoL1(UserModel.getMatchData().getAutoL1()+1);
        binding.L1Display.setText(String.valueOf(UserModel.getMatchData().getAutoL1()));
        });
        binding.MissedCoralMinus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getAutoMissedCoral()>0){
                UserModel.getMatchData().setAutoMissedCoral(UserModel.getMatchData().getAutoMissedCoral()-1);
           }
        binding.MissedCoralDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoMissedCoral()));
            });
        binding.MissedCoralPlus.setOnClickListener(v -> {
            UserModel.getMatchData().setAutoMissedCoral(UserModel.getMatchData().getAutoMissedCoral()+1);
        binding.MissedCoralDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoMissedCoral()));
        });
        binding.NetMinus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getAutoNet()>0){
                UserModel.getMatchData().setAutoNet(UserModel.getMatchData().getAutoNet()-1);
            }
            binding.NetDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoNet()));
        });
        binding.NetPlus.setOnClickListener(v -> {
            UserModel.getMatchData().setAutoNet(UserModel.getMatchData().getAutoNet()+1);
            binding.NetDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoNet()));
        });
        binding.ProcessorMinus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getAutoProcessor()>0){
                UserModel.getMatchData().setAutoProcessor(UserModel.getMatchData().getAutoProcessor()-1);
            }
            binding.ProcessorDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoProcessor()));
        });
        binding.ProcessorPlus.setOnClickListener(v -> {
            UserModel.getMatchData().setAutoProcessor(UserModel.getMatchData().getAutoProcessor()+1);
            binding.ProcessorDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoProcessor()));
            });
        binding.MissedMinus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getAutoMissedAlgae()>0){
                UserModel.getMatchData().setAutoMissedAlgae(UserModel.getMatchData().getAutoMissedAlgae()-1);
            }
            binding.MissedDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoMissedAlgae()));
        });
        binding.MissedPlus.setOnClickListener(v -> {
            UserModel.getMatchData().setAutoMissedAlgae(UserModel.getMatchData().getAutoMissedAlgae()+1);
            binding.MissedDisplay.setText(String.valueOf(UserModel.getMatchData().getAutoMissedAlgae()));
        });
            binding.moveOutOfZone.setOnCheckedChangeListener((v, b) -> UserModel.getMatchData().setMoveOutOfZone(b));

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public ThirdFragment() {
        // Required empty public constructor
    }

    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
}