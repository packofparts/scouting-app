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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;



public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    ViewGroup v = null;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        v = container;
        binding.team.setText("Team " + UserModel.getMatchData().getTeamNumber());
        binding.ampCounter.setText(String.valueOf(UserModel.getMatchData().getAmpTeleop()));
        binding.speakerNotesCounter.setText(String.valueOf(UserModel.getMatchData().getSpeakerNotes()));
        binding.missedNotesCounter.setText(String.valueOf(UserModel.getMatchData().getMissedNotes()));
        binding.broke.setChecked(UserModel.getMatchData().isBroke());
        checkedOperation(binding.broke);
        binding.defense.setChecked(UserModel.getMatchData().isDefense());
        checkedOperation(binding.defense);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.next.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_SecondFragment2));
        binding.prev.setOnClickListener(view12 -> NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_ThirdFragment));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;
        ViewGroup.LayoutParams layoutParams = binding.relativeLayoutFirst.getLayoutParams();
        layoutParams.width = (int) width;
        layoutParams.height = (int) height;
        binding.relativeLayoutFirst.setLayoutParams(layoutParams);
        binding.relativeLayoutFirst.setTranslationY(50);
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", UIHelpers.wolfFrames);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, animation, this.getContext()));
        binding.broke.setOnCheckedChangeListener((buttonView, isChecked) -> {

            checkedOperation(binding.broke);
            UserModel.getMatchData().setBroke(binding.broke.isChecked());
        });
        binding.defense.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedOperation(binding.defense);
            UserModel.getMatchData().setDefense(binding.defense.isChecked());
        });
        ArrayAdapter<String> chainAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Intake", "Ground Intake", "Source Intake", "Both"});
        binding.intakeMethod.setAdapter(chainAdapter);
        binding.intakeMethod.setSelection(getPickUpIndex());
        binding.intakeMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MatchData.NoteAcquisition intakeMethod;
                switch (position){
                    case 0:
                        intakeMethod = MatchData.NoteAcquisition.NONE;
                        break;
                    case 1:
                        intakeMethod = MatchData.NoteAcquisition.FLOOR;
                        break;
                    case 2:
                        intakeMethod = MatchData.NoteAcquisition.SOURCE;
                        break;
                    default:
                        intakeMethod = MatchData.NoteAcquisition.BOTH;
                        break;
                }
                UserModel.getMatchData().setNoteAcquired(intakeMethod);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.minusAmp.setOnClickListener(view14 -> {
            if (UserModel.getMatchData().getAmpTeleop() > 0){
                UserModel.getMatchData().setAmpTeleop(UserModel.getMatchData().getAmpTeleop() - 1);
                binding.ampCounter.setText(String.valueOf(UserModel.getMatchData().getAmpTeleop()));
            }
        });
        binding.plusAmp.setOnClickListener(view15 -> {
            UserModel.getMatchData().setAmpTeleop(UserModel.getMatchData().getAmpTeleop() + 1);
            binding.ampCounter.setText(String.valueOf(UserModel.getMatchData().getAmpTeleop()));
        });
        binding.minusSpeaker.setOnClickListener(view16 -> {
            if (UserModel.getMatchData().getSpeakerNotes() > 0){
                UserModel.getMatchData().setSpeakerNotes(UserModel.getMatchData().getSpeakerNotes() - 1);
                binding.speakerNotesCounter.setText("" + UserModel.getMatchData().getSpeakerNotes());
            }
        });
        binding.plusSpeaker.setOnClickListener(view17 -> {
            UserModel.getMatchData().setSpeakerNotes(UserModel.getMatchData().getSpeakerNotes() + 1);
            binding.speakerNotesCounter.setText(String.valueOf(UserModel.getMatchData().getSpeakerNotes()));
        });
        binding.minusMissedNotes.setOnClickListener(view18 -> {
            if (UserModel.getMatchData().getMissedNotes() > 0){
                UserModel.getMatchData().setMissedNotes(UserModel.getMatchData().getMissedNotes() - 1);
                binding.missedNotesCounter.setText(String.valueOf(UserModel.getMatchData().getMissedNotes()));
            }
        });
        binding.plusMissedNotes.setOnClickListener(view19 -> {
            UserModel.getMatchData().setMissedNotes(UserModel.getMatchData().getMissedNotes() + 1);
            binding.missedNotesCounter.setText(String.valueOf(UserModel.getMatchData().getMissedNotes()));
        });
        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
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
    }

    private static int getPickUpIndex(){
        MatchData.NoteAcquisition noteAcquired = UserModel.getMatchData().getNoteAcquired();
        if(noteAcquired == MatchData.NoteAcquisition.NONE) {
            return 0;
        } else if (noteAcquired == MatchData.NoteAcquisition.FLOOR) {
            return 1;
        } else if (noteAcquired == MatchData.NoteAcquisition.SOURCE) {
            return 2;
        } else {
            return 3;
        }
    }
}