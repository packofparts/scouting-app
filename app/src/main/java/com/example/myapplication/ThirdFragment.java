package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import androidx.navigation.fragment.NavHostFragment;


import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.example.myapplication.databinding.FragmentThirdBinding;
import com.google.android.material.textfield.TextInputLayout;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ThirdFragment extends Fragment {


    private FragmentThirdBinding binding;
    ViewGroup v = null;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        v = container;
        ColorStateList purple = ColorStateList.valueOf(Color.parseColor(("#6750A3")));
        ColorStateList blue = ColorStateList.valueOf((Color.parseColor("#73C2F0")));
        if (binding.switch1.isChecked()){
            binding.switch1.setThumbTintList(purple);
            binding.switch1.setTrackTintList(purple);
        } else {
            binding.switch1.setThumbTintList(blue);
            binding.switch1.setTrackTintList(blue);
        }
        binding.switch2.setChecked(MainActivity.defense);
        if (binding.switch2.isChecked()){
            binding.switch2.setThumbTintList(blue);
            binding.switch2.setTrackTintList(blue);
        } else {
            binding.switch2.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
            binding.switch2.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
        }
        binding.switch3.setChecked(MainActivity.ground);
        if (binding.switch3.isChecked()){
            binding.switch3.setThumbTintList(blue);
            binding.switch3.setTrackTintList(blue);
        } else {
            binding.switch3.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
            binding.switch3.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
        }

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toAutoCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_thirdFragment2);
            }
        });
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotationX", new float[]{0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f});
        animation.setDuration(1000);
        binding.pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation.start();
                MainActivity.darkMode = !MainActivity.darkMode;
                lightDark(v, MainActivity.darkMode);
            }
        });
        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                if (binding.switch1.isChecked()){
                    binding.switch1.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.switch1.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.switch1.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.switch1.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
                MainActivity.broke = binding.switch1.isChecked();
            }
        });
        binding.switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                if (binding.switch2.isChecked()){
                    binding.switch2.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.switch2.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.switch2.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.switch2.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
                MainActivity.broke = binding.switch2.isChecked();
            }
        });
        binding.switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                if (binding.switch3.isChecked()){
                    binding.switch3.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                    binding.switch3.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#6750A3")));
                } else {
                    binding.switch3.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                    binding.switch3.setTrackTintList(ColorStateList.valueOf(Color.parseColor("#73C2F0")));
                }
                MainActivity.broke = binding.switch3.isChecked();
            }
        });
        String[] typeContact = {"No contact", "Teammate to Teammate", "Teammate to opponent"};
        ArrayAdapter<String> contact = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, typeContact);
        binding.spinner.setAdapter(contact);


        Editable numNotes = binding.numNotes.getText();

        lightDark(v, MainActivity.darkMode);

        Switch switch1 = view.findViewById(R.id.switch1);
        switch1.isChecked();

        Switch switch2 = view.findViewById(R.id.switch2);
        switch2.isChecked();

        Switch switch3 = view.findViewById(R.id.switch3);
        switch3.isChecked();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void lightDark (ViewGroup v, boolean mode){
        int bgColor = (mode ? (Color.BLACK) : Color.WHITE);
        int textColor = (mode ? (Color.WHITE) : (Color.BLACK));
        for (int i = 0; i < v.getChildCount(); i ++){
            View child = v.getChildAt(i);
            if (!(child instanceof Button)) {
                child.setBackgroundColor(bgColor);
                if (child instanceof TextView) {
                    TextView tx = (TextView) child;
                    tx.setTextColor(textColor);
                }
            }
            if (child instanceof Switch) {
                Switch tx = (Switch) child;
                tx.setTextColor(textColor);
            }
            if (child instanceof ViewGroup){
                lightDark((ViewGroup)child, mode);
            }
        }
    }


    //TODO: Rename parameter arguments, choose names that match
    //the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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