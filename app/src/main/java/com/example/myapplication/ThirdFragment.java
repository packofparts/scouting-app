package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    public static boolean ground = false;
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
        binding.switch3.setChecked(ThirdFragment.ground);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.switch3.setThumbTintList(UIHelpers.teamColorAsList);
            binding.switch3.setTrackTintList(UIHelpers.teamColorAsList);
        }
        binding.textView4.setText("Autonomous Team " + UserModel.getMatchData().getTeamNumber());
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
        binding.team.setText("Team " + UserModel.getMatchData().getTeamNumber());
        //VARIABLES

        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", 0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> {
            animation.start();
            UIHelpers.darkMode = !UIHelpers.darkMode;
            UIHelpers.lightDark(v, UIHelpers.darkMode);

        });
        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setSwitchColor(binding.switch1);
            UserModel.getMatchData().setWorkingAuto(isChecked);
        });
        binding.switch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setSwitchColor(binding.switch2);
            UserModel.getMatchData().setMoveOutOfZone(isChecked);
        });
        binding.switch3.setOnCheckedChangeListener((buttonView, isChecked) -> setSwitchColor(binding.switch3));
        binding.toSecondFragment.setOnClickListener(view12 -> {
            NavHostFragment.findNavController(ThirdFragment.this)
                    .navigate(R.id.action_ThirdFragment_to_SecondFragment);
        });
        binding.toHomePage.setOnClickListener(view12 -> {
            NavHostFragment.findNavController(ThirdFragment.this)
                    .navigate(R.id.action_ThirdFragment_to_FirstFragment                                                                      );
        });

        UIHelpers.lightDark(v, UIHelpers.darkMode);

        updateEditTextBackground(binding.numNotes);
        updateEditTextBackground(binding.numNotesInAmp);

        binding.numNotesInAmp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                Editable input = binding.numNotesInAmp.getText();
                if (input == null || input.length() == 0){
                    return;
                } else {
                    UserModel.getMatchData().setAmpAuto(Integer.parseInt(input.toString()));
                }
            }
        });
        binding.numNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                Editable input = binding.numNotes.getText();
                if (input == null || input.length() == 0){
                    return;
                } else {
                    UserModel.getMatchData().setSpeakerAuto(Integer.parseInt(input.toString()));
                }
            }
        });
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

    private static void updateEditTextBackground(EditText editText) {
        editText
                .getBackground()
                .setColorFilter(
                        Color.parseColor("#73C2F0"),
                        PorterDuff.Mode.SRC_ATOP);
    }


}