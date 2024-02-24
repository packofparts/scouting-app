package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    public static boolean defense = false;
    public static boolean ground = false;

    public enum Contact{
        NONE,
        TEAMMATE,
        OPPONENT,

    }
    public static ThirdFragment.Contact contactMethod = ThirdFragment.Contact.NONE;
    public static int contactIndex = 0;
    @SuppressLint({"ObsoleteSdkInt", "SetTextI18n"})
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        v = container;
            setSwitchColor(binding.switch1);
            setSwitchColor(binding.switch2);
            setSwitchColor(binding.switch3);
        binding.switch2.setChecked(ThirdFragment.defense);
        binding.switch3.setChecked(ThirdFragment.ground);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.switch3.setThumbTintList(UIHelpers.teamColorAsList);
            binding.switch3.setTrackTintList(UIHelpers.teamColorAsList);
        }
        binding.textView4.setText("Autonomous Team " + MainActivity.teamNumber);
        return binding.getRoot();
    }

    @SuppressLint("ObsoleteSdkInt")
    private void setSwitchColor(@SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch1) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            switch1.setThumbTintList(switch1.isChecked() ? UIHelpers.purpleAsList : UIHelpers.teamColorAsList);
            switch1.setTrackTintList(switch1.isChecked() ? UIHelpers.purpleAsList : UIHelpers.teamColorAsList);
        }
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.team.setText("Team " + MainActivity.teamNumber);
        //VARIABLES

        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", 0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> {
            animation.start();
            UIHelpers.darkMode = !UIHelpers.darkMode;
            UIHelpers.lightDark(v, UIHelpers.darkMode);

        });
        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> setSwitchColor(binding.switch1));
        binding.switch2.setOnCheckedChangeListener((buttonView, isChecked) -> setSwitchColor(binding.switch2));
        binding.switch3.setOnCheckedChangeListener((buttonView, isChecked) -> setSwitchColor(binding.switch3));
        binding.toSecondFragment.setOnClickListener(view12 -> {
            NavHostFragment.findNavController(ThirdFragment.this)
                    .navigate(R.id.action_ThirdFragment_to_SecondFragment);
            //getting the value of variables when switching to teleop from auto
            /*binding.switch1.isChecked();
            binding.switch2.isChecked();
            binding.switch3.isChecked();
            numNotes.getText();
            numNotesInAmp.getText();
            Contact.getSelectedItem();*/
        });
        String[] typeContact = {"No contact", "Teammate to Teammate", "Teammate to opponent"};
        ArrayAdapter<String> contact = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, typeContact);
        binding.spinner.setAdapter(contact);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ThirdFragment.contactIndex = position;
                switch (position){
                    case 0:
                        contactMethod = ThirdFragment.Contact.NONE;
                        break;
                    case 1:
                        contactMethod = ThirdFragment.Contact.TEAMMATE;
                        break;
                    case 2:
                        contactMethod = ThirdFragment.Contact.OPPONENT;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        UIHelpers.lightDark(v, UIHelpers.darkMode);



        binding.numNotes.getBackground().setColorFilter(Color.parseColor("#73C2F0"), PorterDuff.Mode.SRC_ATOP);
        binding.numNotesInAmp.getBackground().setColorFilter(Color.parseColor("#73C2F0"), PorterDuff.Mode.SRC_ATOP);
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