package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapplication.databinding.PitScoutingBinding;
import java.util.Objects;

public class PitScouting extends Fragment {

    private PitScoutingBinding binding;
    public PitScouting() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = PitScoutingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.title.setText("Pit Scouting Team " + UserModel.getPitData().getTeamNumber());

        ArrayAdapter<String> driveTrain = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"West Coast", "Mechanum", "Swerve", "Other"});

        binding.driveTrain.setAdapter(driveTrain);

        binding.driveTrain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);//Only known method to set text color to white
                UserModel.getPitData().setDriveTrain(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        binding.driveTrain.setSelection(UserModel.getPitData().getDriveTrain());//Triggers setting color to white

        ArrayAdapter<String> intake = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Intake", "Outpost (Source)", "Ground", "Outpost and Ground"});

        binding.intake.setAdapter(intake);

        binding.intake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);//Only known method to set text color to white
                UserModel.getPitData().setIntake(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        binding.intake.setSelection(UserModel.getPitData().getIntake());//Triggers setting color to white

        ArrayAdapter<String> terrain = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"No Traversal", "Bump", "Trench", "Bump and Trench"});

        binding.terrain.setAdapter(terrain);

        binding.terrain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);//Only known method to set text color to white
                UserModel.getPitData().setTerrain(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        binding.terrain.setSelection(UserModel.getPitData().getTerrain());//Triggers setting color to white

        binding.cont.setOnClickListener(v ->
            UIHelpers.makeConfirmationAlert("Transfer Pit Data", "Do you want to transfer your pit data?", () -> {
                try {
                    UserModel.getPitData().toJson();
                } catch (Exception e) {
                    UIHelpers.makeHelpAlert("Unknown Data Transfer Error!", e.getMessage(), getContext());
                }
                NavHostFragment.findNavController(PitScouting.this).navigate(R.id.action_HomePage_to_FirstFragment);
                }, () -> {}, getContext()));

        binding.back.setOnClickListener(v -> UIHelpers.makeConfirmationAlert("Cancel Pit Data", "Do you want to cancel your pit data?", () -> NavHostFragment.findNavController(PitScouting.this)
                .navigate(R.id.action_HomePage_to_FirstFragment), () -> {}, getContext()));

        binding.reset.setOnClickListener(v -> {
            binding.driveTrain.setSelection(0);
            binding.intake.setSelection(0);
            binding.terrain.setSelection(0);
            binding.input.setText("");
        });

        binding.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                binding.characterLimit.setText("CHARACTER LIMIT: " + Objects.requireNonNull(binding.input.getText()).length() + "/150");
                UserModel.getPitData().setNotes(binding.input.getText().toString());
            }
        });



    }

}
