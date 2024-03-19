package com.example.myapplication;

import android.animation.ObjectAnimator;
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
        binding.numNotes.setText(String.valueOf(UserModel.getMatchData().getSpeakerAuto()));
        binding.numNotesInAmp.setText(String.valueOf(UserModel.getMatchData().getAmpAuto()));
        binding.switch2.setChecked(UserModel.getMatchData().getMoveOutOfZone());
        checkedOperation(binding.switch2);
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

        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", 0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> {
            animation.start();
            UIHelpers.darkMode = !UIHelpers.darkMode;
            UIHelpers.lightDark(v, UIHelpers.darkMode);

        });
        binding.switch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedOperation(binding.switch2);
            UserModel.getMatchData().setMoveOutOfZone(isChecked);
        });
        binding.toSecondFragment.setOnClickListener(view12 -> NavHostFragment.findNavController(ThirdFragment.this)
                .navigate(R.id.action_ThirdFragment_to_SecondFragment));
        binding.toHomePage.setOnClickListener(view12 -> NavHostFragment.findNavController(ThirdFragment.this)
                .navigate(R.id.action_ThirdFragment_to_FirstFragment));
        binding.plusSpeaker.setOnClickListener(v -> {
            UserModel.getMatchData().setSpeakerAuto(UserModel.getMatchData().getSpeakerAuto() + 1);
            binding.numNotes.setText(String.valueOf(UserModel.getMatchData().getSpeakerAuto()));
        });
        binding.minusSpeaker.setOnClickListener(v -> {
            UserModel.getMatchData().setSpeakerAuto(UserModel.getMatchData().getSpeakerAuto() - (UserModel.getMatchData().getSpeakerAuto() <= 0 ? 0 : 1));
            binding.numNotes.setText(String.valueOf(UserModel.getMatchData().getSpeakerAuto()));
        });
        binding.plusAmp.setOnClickListener(v -> {
            UserModel.getMatchData().setAmpAuto(UserModel.getMatchData().getAmpAuto() + 1);
            binding.numNotesInAmp.setText(String.valueOf(UserModel.getMatchData().getAmpAuto()));
        });
        binding.minusAmp.setOnClickListener(v -> {
            UserModel.getMatchData().setAmpAuto(UserModel.getMatchData().getAmpAuto() - (UserModel.getMatchData().getAmpAuto() <= 0 ? 0 : 1));
            binding.numNotesInAmp.setText(String.valueOf(UserModel.getMatchData().getAmpAuto()));
        });
        UIHelpers.lightDark(v, UIHelpers.darkMode);
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