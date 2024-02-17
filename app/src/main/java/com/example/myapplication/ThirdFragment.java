package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

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
    private static ColorStateList purple = ColorStateList.valueOf(Color.parseColor("#6750A3"));
    private static ColorStateList blue = ColorStateList.valueOf(Color.parseColor("#73C2F0"));

    public static boolean defense = false;
    public static boolean ground = false;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        v = container;
            setSwitchColor(binding.switch1);
            setSwitchColor(binding.switch2);
            setSwitchColor(binding.switch3);
        binding.switch2.setChecked(ThirdFragment.defense);
        binding.switch3.setChecked(ThirdFragment.ground);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.switch3.setThumbTintList(blue);
            binding.switch3.setTrackTintList(blue);
        }

        return binding.getRoot();
    }

    private void setSwitchColor(Switch switch1) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            switch1.setThumbTintList(switch1.isChecked() ? purple : blue);
            switch1.setTrackTintList(switch1.isChecked() ? purple : blue);
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_SecondFragment);
            }
        });
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotationX", new float[]{0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f});
        animation.setDuration(1000);
        binding.pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation.start();
                UIHelpers.darkMode = !UIHelpers.darkMode;
                UIHelpers.lightDark(v, UIHelpers.darkMode);

            }
        });
        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                setSwitchColor(binding.switch1);
            }
        });
        binding.switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                setSwitchColor(binding.switch2);
            }
        });
        binding.switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                setSwitchColor(binding.switch3);
            }
        });
        String[] typeContact = {"No contact", "Teammate to Teammate", "Teammate to opponent"};
        ArrayAdapter<String> contact = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, typeContact);
        binding.spinner.setAdapter(contact);


        UIHelpers.lightDark(v, UIHelpers.darkMode);

        Switch switch1 = view.findViewById(R.id.switch1);
        switch1.isChecked();

        Switch switch2 = view.findViewById(R.id.switch2);
        switch2.isChecked();

        Switch switch3 = view.findViewById(R.id.switch3);
        switch3.isChecked();

        EditText numNotes = view.findViewById(R.id.numNotes);
        numNotes.getText();

        EditText numNotesInAmp = view.findViewById(R.id.numNotesInAmp);
        numNotesInAmp.getText();

        Spinner Contact = view.findViewById(R.id.spinner);
        Contact.getSelectedItem();

        numNotes.getBackground().setColorFilter(Color.parseColor("#73C2F0"), PorterDuff.Mode.SRC_ATOP);
        numNotesInAmp.getBackground().setColorFilter(Color.parseColor("#73C2F0"), PorterDuff.Mode.SRC_ATOP);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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