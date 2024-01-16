package com.example.myapplication;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import androidx.navigation.fragment.NavHostFragment;


import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;

import com.example.myapplication.databinding.FragmentThirdBinding;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ThirdFragment extends Fragment {


    private FragmentThirdBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ){
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    // DOES THE ROBOT HAVE WORKING AUTO
    private boolean workingAuto;
    public void setWorkingAuto(boolean wantedValue){
        wantedValue = true;
        workingAuto = wantedValue;

    }

    private boolean notWorkingAuto;
    public void setNotWorkingAuto(boolean notWantedValue){
        notWantedValue = false;
        notWorkingAuto = notWantedValue;
    }

    //DOES THE ROBOT MOVE OUT OF THEIR ZONE DURING AUTO PHASE

    private boolean outOfZone;
    public void setoutOfZone(boolean wantedValue){
        wantedValue = true;
        outOfZone = wantedValue;
    }
    private boolean notOutOfZone;
    public void setnotOutOfZone(boolean notWantedValue){
        notWantedValue = false;
        notOutOfZone = notWantedValue;
        }
    //DOES ROBOT SCORE DURING AUTO PHASE

    private boolean scoreAuto;
       public void setScoreAuto(boolean wantedValue){
        wantedValue = true;
        scoreAuto = wantedValue;
    }
    private boolean noScoreAuto;
       public void setNoScoreAuto(boolean notWantedValue){
           notWantedValue = false;
           noScoreAuto = notWantedValue;
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
        binding.Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = NavHostFragment.findNavController(ThirdFragment.this);
                setWorkingAuto(workingAuto);
                System.out.println(workingAuto);
            }
        });
        binding.No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = NavHostFragment.findNavController(ThirdFragment.this);
                setNotWorkingAuto(notWorkingAuto);
                System.out.println(notWorkingAuto);
            }
        });
        binding.moveYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = NavHostFragment.findNavController(ThirdFragment.this);
                setoutOfZone(outOfZone);
                System.out.println(outOfZone);
            }
        });
        binding.moveNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = NavHostFragment.findNavController(ThirdFragment.this);
                setnotOutOfZone(notOutOfZone);
                System.out.println(notOutOfZone);
            }
        });
        binding.scoreAutoYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = NavHostFragment.findNavController(ThirdFragment.this);
                setScoreAuto(scoreAuto);
                System.out.println(scoreAuto);
            }
        });
        binding.scoreAutoNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = NavHostFragment.findNavController(ThirdFragment.this);
                setnotOutOfZone(noScoreAuto);
                System.out.println(noScoreAuto);
            }
        });
        Editable numNotes = binding.numNotes.getText();


        }


    /*public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController = NavHostFragment.findNavController(ThirdFragment.this);
                if (.isChecked()) {
                    checkBox.setChecked(false);
                }
            }
        });
    }*/
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