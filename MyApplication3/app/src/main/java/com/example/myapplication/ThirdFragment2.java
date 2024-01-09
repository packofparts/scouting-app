package com.example.myapplication;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import androidx.navigation.fragment.NavHostFragment;


import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;

import com.example.myapplication.databinding.FragmentThird2Binding;
import com.example.myapplication.databinding.FragmentThirdBinding;
public class ThirdFragment2 extends Fragment{



    private FragmentThird2Binding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        binding = FragmentThird2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment2.this)
                        .navigate(R.id.action_thirdFragment2_to_SecondFragment);
            }
        });

    }
}
