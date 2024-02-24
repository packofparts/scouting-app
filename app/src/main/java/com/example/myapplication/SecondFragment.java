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
    MatchData.NoteAcquisition intakeMethod = MatchData.NoteAcquisition.NONE;
    public static int intakeMethodIndex = 0;
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
        binding.speakerUnampCounter.setText(String.valueOf(UserModel.getMatchData().getSpeakerTeleop()));
        binding.speakerAmpCounter.setText(String.valueOf(UserModel.getMatchData().getAmplifiedSpeaker()));
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
        binding.next.setTranslationX((width - 300)/ 1.1f);
        binding.next.setTranslationY(height * 0.863f);
        binding.prev.setTranslationX((width - 300)/ 8.0f);
        binding.prev.setTranslationY(height * 0.863f);
        binding.title.setTranslationX((width - 136)/ 2.0f);
        binding.team.setTranslationY(height * 0.127f);
        binding.team.setTranslationX(binding.title.getX());
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation", 0f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f, 90f, 180f, 270f, 360f);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view13 -> {
            animation.start();
            UIHelpers.darkMode = !UIHelpers.darkMode;
            UIHelpers.lightDark(v, UIHelpers.darkMode);
        });
        binding.broke.setTranslationX(width * 0.073f);
        binding.broke.setTranslationY(height * 0.216f);
        binding.broke.setOnCheckedChangeListener((buttonView, isChecked) -> {

            checkedOperation(binding.broke);
            UserModel.getMatchData().setBroke(binding.broke.isChecked());
        });
        binding.defense.setTranslationX(width * 0.073f);
        binding.defense.setTranslationY(height * 0.288f);
        binding.defense.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkedOperation(binding.defense);
            UserModel.getMatchData().setDefense(binding.defense.isChecked());
        });
        binding.intake.setTranslationX(width * 0.073f);
        binding.intake.setTranslationY(height * 0.360f);
        binding.intakeBackground.setTranslationX(width * 0.366f);
        binding.intakeBackground.setTranslationY(height * 0.321f);
        ArrayAdapter<String> chainAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Intake", "Ground Intake", "Source Intake", "Both"});
        binding.intakeMethod.setAdapter(chainAdapter);
        binding.intakeMethod.setSelection(SecondFragment2.chainAttemptIndex);
        binding.intakeMethod.setTranslationX(width * 0.366f);
        binding.intakeMethod.setTranslationY(height * 0.367f);
        binding.intakeMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SecondFragment.intakeMethodIndex = position;
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
                    case 3:
                        intakeMethod = MatchData.NoteAcquisition.BOTH;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.ampNotes.setTranslationX(width * 0.073f);
        binding.ampNotes.setTranslationY(height * 0.504f);
        binding.minusAmp.setTranslationX(width * 0.366f);
        binding.minusAmp.setTranslationY(height * 0.453f);
        binding.minusAmp.setOnClickListener(view14 -> {
            if (UserModel.getMatchData().getAmpTeleop() > 0){
                UserModel.getMatchData().setAmpTeleop(UserModel.getMatchData().getAmpTeleop() - 1);
                binding.ampCounter.setText(String.valueOf(UserModel.getMatchData().getAmpTeleop()));
            }
        });
        binding.plusAmp.setTranslationX(width * 0.732f);
        binding.plusAmp.setTranslationY(height * 0.453f);
        binding.plusAmp.setOnClickListener(view15 -> {
            UserModel.getMatchData().setAmpTeleop(UserModel.getMatchData().getAmpTeleop() + 1);
            binding.ampCounter.setText(String.valueOf(UserModel.getMatchData().getAmpTeleop()));
        });
        binding.ampCounter.setTranslationX(width * 0.598f);
        binding.ampCounter.setTranslationY(height * 0.511f);

        binding.speakerNotesUnamp.setTranslationX(width * 0.073f);
        binding.speakerNotesUnamp.setTranslationY(height * 0.619f);
        binding.minusSpeakerUnamp.setTranslationX(width * 0.366f);
        binding.minusSpeakerUnamp.setTranslationY(height * 0.576f);
        binding.minusSpeakerUnamp.setOnClickListener(view16 -> {
            if (UserModel.getMatchData().getSpeakerTeleop() > 0){
                UserModel.getMatchData().setSpeakerTeleop(UserModel.getMatchData().getSpeakerTeleop() - 1);
                binding.speakerUnampCounter.setText("" + UserModel.getMatchData().getSpeakerTeleop());
            }
        });
        binding.plusSpeakerUnamp.setTranslationX(width * 0.732f);
        binding.plusSpeakerUnamp.setTranslationY(height * 0.576f);
        binding.plusSpeakerUnamp.setOnClickListener(view17 -> {
            UserModel.getMatchData().setSpeakerTeleop(UserModel.getMatchData().getSpeakerTeleop() + 1);
            binding.speakerUnampCounter.setText(String.valueOf(UserModel.getMatchData().getSpeakerTeleop()));
        });
        binding.speakerUnampCounter.setTranslationX(width * 0.598f);
        binding.speakerUnampCounter.setTranslationY(height * 0.633f);

        binding.speakerNotesAmp.setTranslationX(width * 0.073f);
        binding.speakerNotesAmp.setTranslationY(height * 0.770f);
        binding.minusSpeakerAmp.setTranslationX(width * 0.366f);
        binding.minusSpeakerAmp.setTranslationY(height * 0.727f);

        binding.minusSpeakerAmp.setOnClickListener(view18 -> {
            if (UserModel.getMatchData().getAmplifiedSpeaker() > 0){
                UserModel.getMatchData().setAmplifiedSpeaker(UserModel.getMatchData().getAmplifiedSpeaker() - 1);
                binding.speakerAmpCounter.setText(String.valueOf(UserModel.getMatchData().getAmplifiedSpeaker()));
            }
        });
        binding.plusSpeakerAmp.setTranslationX(width * 0.732f);
        binding.plusSpeakerAmp.setTranslationY(height * 0.727f);

        binding.plusSpeakerAmp.setOnClickListener(view19 -> {
            UserModel.getMatchData().setAmplifiedSpeaker(UserModel.getMatchData().getAmplifiedSpeaker() + 1);
            binding.speakerAmpCounter.setText(String.valueOf(UserModel.getMatchData().getAmplifiedSpeaker()));
        });
        binding.speakerAmpCounter.setTranslationX(width * 0.598f);
        binding.speakerAmpCounter.setTranslationY(height * 0.784f);
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
}