package com.example.myapplication;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.animation.ObjectAnimator;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SpinnerAdapter;

import com.example.myapplication.databinding.FragmentHomepageBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to * create an instance of this fragment.
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomepageBinding.inflate(inflater, container, false);
        v = container;
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.team.setText("Team " + UserModel.getPitData().getTeamNumber());
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.pop, "rotation",UIHelpers.wolfFrames);
        animation.setDuration(1000);
        binding.pop.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, animation, this.getContext()));

        ArrayAdapter<String> drive = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"West Coast", "Mecanum", "Swerve", "Other"});

        binding.driveTrain.setAdapter(drive);

        binding.driveTrain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserModel.getPitData().setDriveTrain(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.mass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    UserModel.getPitData().setMass(Double.parseDouble(Objects.requireNonNull(binding.mass.getText()).toString()));
                } catch (NumberFormatException e){
                    //The user has entered an empty string.
                    Snackbar.make(view, "Please enter a number.", 600).show();
                    UserModel.getPitData().setMass(0.0);
                }
            }
        });

        ArrayAdapter<String> climb = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"Cannot Climb", "Shallow Climb", "Deep Climb", "Shallow and Deep Climb"});

        binding.climb.setAdapter(climb);

        binding.climb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserModel.getPitData().setClimb(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.L1.setOnCheckedChangeListener((v, b) -> UserModel.getPitData().setL1(b));
        binding.L2.setOnCheckedChangeListener((v, b) -> UserModel.getPitData().setL2(b));
        binding.L3.setOnCheckedChangeListener((v, b) -> UserModel.getPitData().setL3(b));
        binding.L4.setOnCheckedChangeListener((v, b) -> UserModel.getPitData().setL4(b));

        ArrayAdapter<String> coralIntake = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Intake", "Source Intake", "Ground Intake", "Source and Ground Intake"});

        binding.coralIntake.setAdapter(coralIntake);

        binding.coralIntake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserModel.getPitData().setCoralIntake(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.net.setOnCheckedChangeListener((v, b) -> UserModel.getPitData().setNet(b));
        binding.processor.setOnCheckedChangeListener((v, b) -> UserModel.getPitData().setProcessor(b));

        ArrayAdapter<String> algaeIntake = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Intake", "Reef Intake", "Ground Intake", "Reef and Ground Intake"});

        binding.algaeIntake.setAdapter(algaeIntake);

        binding.algaeIntake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserModel.getPitData().setAlgaeIntake(position);
                binding.dislodge.setChecked(position % 2 != 0 || binding.dislodge.isChecked());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.dislodge.setOnCheckedChangeListener((v, b) -> UserModel.getPitData().setDislodge(b));

        binding.cont.setOnClickListener(view1 ->

                UIHelpers.makeConfirmationAlert("Transfer Pit Data", "Do you want to transfer your pit data?", () -> {
            try {
                UserModel.getPitData().toJson();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            NavHostFragment.findNavController(HomePage.this).navigate(R.id.action_HomePage_to_FirstFragment);
        }, () -> {}, getContext()));


        binding.back.setOnClickListener(view1 -> UIHelpers.makeConfirmationAlert("Cancel Pit Data", "Do you want to cancel your pit data?", () -> NavHostFragment.findNavController(HomePage.this)
                .navigate(R.id.action_HomePage_to_FirstFragment), () -> {}, getContext()));

        binding.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                binding.characterLimit.setText("Character Limit: " + Objects.requireNonNull(binding.input.getText()).length() + "/150");
                Analyzer2.populate(getResources().openRawResource(R.raw.cleansentiment));
                double score = Analyzer2.analyze(binding.input.getText().toString());
                binding.analyzerScore.setText("Analyzer Score: " + score);
                UserModel.getPitData().setNotes(binding.input.getText().toString());
                UserModel.getPitData().setAnalyzerScore(score);
            }
        });

        binding.driveTrainHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Drive Train", "This is the drive train of the robot!", getContext()));
        binding.massHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Mass", "How massive (heavy) is the robot in pounds?", getContext()));
        binding.climbHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Climb", "What cages can the robot climb? Tell us here!", getContext()));
        binding.coralScoringHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Coral Scoring", "These are the branch levels of which the robot can score on!", getContext()));
        binding.coralIntakeHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Coral Intake", "Can the robot grab coral from the ground, source, or both?", getContext()));
        binding.algaeScoringHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Algae Scoring", "How does the robot score algae? Can the robot shoot, place in processor, or both?", getContext()));
        binding.algaeIntakeHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Algae Intake", "These are the places where the robot can obtain algae from!", getContext()));
        binding.dislodgeHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Algae Dislodging", "Can the robot simply remove algae from the reef? The robot doesn't have to grab it directly!", getContext()));
        binding.notesHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Notes", "Here, you can jot down anything extra that you've observed in the pits!", getContext()));
        binding.limitHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Character Limit", "You have a 150-character limit for your notes.", getContext()));
        binding.analyzerHelp.setOnClickListener(v -> UIHelpers.makeHelpAlert("Sentiment Analyzer", "This is the overall sentiment (positivity/negativity) of your notes!", getContext()));

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        UIHelpers.relate(v, width, height, getResources().getDisplayMetrics().density);
        UIHelpers.lightDark(v, UIHelpers.darkMode);
        binding.bottomTag.setText(MainActivity.getLocationText());

    }

}
