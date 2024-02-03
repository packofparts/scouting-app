package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.databinding.FragmentHomepageBinding;
import com.example.myapplication.databinding.FragmentSecondBinding;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewGroup v;
    private FragmentHomepageBinding binding;
    public HomePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Teleop.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePage newInstance(String param1, String param2) {
        HomePage fragment = new HomePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomepageBinding.inflate(inflater, container, false);
        v = container;
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomePage.this)
                        .navigate(R.id.action_HomePage_to_pitFragment);
            }
        });*/
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.WolfLogoInfo, "rotation", new float[]{0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f});
        animation.setDuration(1000);
        binding.WolfLogoInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation.start();
                MainActivity.darkMode = !MainActivity.darkMode;
                lightDark(v, MainActivity.darkMode);
            }
        });
        lightDark(v, MainActivity.darkMode);
    }
    public void lightDark (ViewGroup v, boolean mode){
        if (!mode){
            for (int i = 0; i < v.getChildCount(); i ++){
                View child = v.getChildAt(i);
                if (!(child instanceof Button)) {
                    child.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    if (child instanceof TextView) {
                        TextView tx = (TextView) child;
                        tx.setTextColor(Color.parseColor("#000000"));
                    }
                    if (child instanceof TextInputEditText) {
                        TextView tx = (TextInputEditText) child;
                        tx.setTextColor(Color.parseColor("#000000"));
                        tx.setHintTextColor(Color.parseColor("#000000"));
                    }
                }
                if (child instanceof Switch) {
                    Switch tx = (Switch) child;
                    tx.setTextColor(Color.parseColor("#000000"));
                }
                if (child instanceof ViewGroup){
                    lightDark((ViewGroup)child, mode);
                }
            }
        } else {
            for (int i = 0; i < v.getChildCount(); i ++){
                View child = v.getChildAt(i);
                if (!(child instanceof Button)) {
                    child.setBackgroundColor(Color.parseColor("#000000"));
                    if (child instanceof TextView) {
                        TextView tx = (TextView) child;
                        tx.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                    if (child instanceof TextInputEditText) {
                        TextView tx = (TextInputEditText) child;
                        tx.setTextColor(Color.parseColor("#FFFFFF"));
                        tx.setHintTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
                if (child instanceof Switch) {
                    Switch tx = (Switch) child;
                    tx.setTextColor(Color.parseColor("#FFFFFF"));
                }
                if (child instanceof ViewGroup){
                    lightDark((ViewGroup)child, mode);
                }
            }
        }
    }
}