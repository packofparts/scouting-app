package com.example.myapplication;


import android.annotation.SuppressLint;

import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.cont.setOnClickListener(view1 -> NavHostFragment.findNavController(TeleOp.this)
                .navigate(R.id.action_SecondFragment_to_SecondFragment2));
        binding.back.setOnClickListener(view12 -> NavHostFragment.findNavController(TeleOp.this)
                .navigate(R.id.action_SecondFragment_to_ThirdFragment));

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

