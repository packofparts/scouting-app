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
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.TeleOpBinding;


public class TeleOp extends Fragment {

    private TeleOpBinding binding;
    ViewGroup v = null;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = TeleOpBinding.inflate(inflater, container, false);
        v = container;
        binding.team.setText("Team " + UserModel.getMatchData().getTeamNumber());
        binding.L4Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL4()));
        binding.L3Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL3()));
        binding.L2Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL2()));
        binding.L1Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL1()));
        binding.MissedCoralDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpMissedCoral()));
        binding.MissedDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpMissedAlgae()));
        binding.NetDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpNet()));
        binding.ProcessorDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpProcessor()));
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.cont.setOnClickListener(view1 -> NavHostFragment.findNavController(TeleOp.this)
                .navigate(R.id.action_SecondFragment_to_SecondFragment2));
        binding.back.setOnClickListener(view12 -> NavHostFragment.findNavController(TeleOp.this)
                .navigate(R.id.action_SecondFragment_to_ThirdFragment));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", UIHelpers.wolfFrames);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, animation, this.getContext()));

        binding.L4Minus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getTeleOpL4()>0){
                UserModel.getMatchData().setTeleOpL4(UserModel.getMatchData().getTeleOpL4()-1);
            }
            binding.L4Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL4()));
        });
        binding.L4Plus.setOnClickListener(v -> {
            UserModel.getMatchData().setTeleOpL4(UserModel.getMatchData().getTeleOpL4()+1);
            binding.L4Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL4()));
        });
        binding.L3Minus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getTeleOpL3()>0){
                UserModel.getMatchData().setTeleOpL3(UserModel.getMatchData().getTeleOpL3()-1);
            }
            binding.L3Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL3()));
        });
        binding.L3Plus.setOnClickListener(v -> {
            UserModel.getMatchData().setTeleOpL3(UserModel.getMatchData().getTeleOpL3()+1);
            binding.L3Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL3()));
        });
        binding.L2Minus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getTeleOpL2()>0){
                UserModel.getMatchData().setTeleOpL2(UserModel.getMatchData().getTeleOpL2()-1);
            }
            binding.L2Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL2()));
        });
        binding.L2Plus.setOnClickListener(v -> {
            UserModel.getMatchData().setTeleOpL2(UserModel.getMatchData().getTeleOpL2()+1);
            binding.L2Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL2()));
        });
        binding.L1Minus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getTeleOpL1()>0){
                UserModel.getMatchData().setTeleOpL1(UserModel.getMatchData().getTeleOpL1()-1);
            }
            binding.L1Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL1()));
        });
        binding.L1Plus.setOnClickListener(v -> {
            UserModel.getMatchData().setTeleOpL1(UserModel.getMatchData().getTeleOpL1()+1);
            binding.L1Display.setText(String.valueOf(UserModel.getMatchData().getTeleOpL1()));
        });
        binding.MissedCoralMinus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getTeleOpMissedCoral()>0){
                UserModel.getMatchData().setTeleOpMissedCoral(UserModel.getMatchData().getTeleOpMissedCoral()-1);
            }
            binding.MissedCoralDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpMissedCoral()));
        });
        binding.MissedCoralPlus.setOnClickListener(v -> {
            UserModel.getMatchData().setTeleOpMissedCoral(UserModel.getMatchData().getTeleOpMissedCoral()+1);
            binding.MissedCoralDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpMissedCoral()));
        });
        binding.NetMinus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getTeleOpNet()>0){
                UserModel.getMatchData().setTeleOpNet(UserModel.getMatchData().getTeleOpNet()-1);
            }
            binding.NetDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpNet()));
        });
        binding.NetPlus.setOnClickListener(v -> {
            UserModel.getMatchData().setTeleOpNet(UserModel.getMatchData().getTeleOpNet()+1);
            binding.NetDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpNet()));
        });
        binding.ProcessorMinus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getTeleOpProcessor()>0){
                UserModel.getMatchData().setTeleOpProcessor(UserModel.getMatchData().getTeleOpProcessor()-1);
            }
            binding.ProcessorDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpProcessor()));
        });
        binding.ProcessorPlus.setOnClickListener(v -> {
            UserModel.getMatchData().setTeleOpProcessor(UserModel.getMatchData().getTeleOpProcessor()+1);
            binding.ProcessorDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpProcessor()));
        });
        binding.MissedMinus.setOnClickListener(v -> {
            if(UserModel.getMatchData().getTeleOpMissedAlgae()>0){
                UserModel.getMatchData().setTeleOpMissedAlgae(UserModel.getMatchData().getTeleOpMissedAlgae()-1);
            }
            binding.MissedDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpMissedAlgae()));
        });
        binding.MissedPlus.setOnClickListener(v -> {
            UserModel.getMatchData().setTeleOpMissedAlgae(UserModel.getMatchData().getTeleOpMissedAlgae()+1);
            binding.MissedDisplay.setText(String.valueOf(UserModel.getMatchData().getTeleOpMissedAlgae()));
        });
        binding.coralScoringHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Coral Scoring", "These are the number of times the robot scored coral onto each level or missed from any level.", getContext()));
        binding.AlgaeScoringHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Algae Scoring", "These are the number of times the robot scored algae into each location or missed from any location.", getContext()));
        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
        binding.bottomTag.setText(MainActivity.getLocationText());
    }
   
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;

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
    }}

