package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    public int amp = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;
        ViewGroup.LayoutParams layoutParams = binding.relativeLayoutFirst.getLayoutParams();
        layoutParams.width = (int) width;
        layoutParams.height = (int) height + 380;
        binding.relativeLayoutFirst.setLayoutParams(layoutParams);

        binding.next.setTranslationX((width - 300)/ 2.0f);
        binding.next.setTranslationY(height + 230);
        binding.title.setTranslationX((width - 136)/ 2.0f);
        binding.broke.setTranslationX(width * 0.073f);
        binding.broke.setTranslationY(height * 0.216f);
        binding.defense.setTranslationX(width * 0.073f);
        binding.defense.setTranslationY(height * 0.288f);
        binding.ground.setTranslationX(width * 0.073f);
        binding.ground.setTranslationY(height * 0.360f);
        binding.source.setTranslationX(width * 0.073f);
        binding.source.setTranslationY(height * 0.432f);
        binding.ampNotes.setTranslationX(width * 0.073f);
        binding.ampNotes.setTranslationY(height * 0.504f);
        binding.minusAmp.setTranslationX(width * 0.366f);
        binding.minusAmp.setTranslationY(height * 0.453f);
        binding.minusAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amp > 0){
                    amp --;
                    binding.ampCounter.setText("" + amp);
                }
            }
        });
        binding.plusAmp.setTranslationX(width * 0.732f);
        binding.plusAmp.setTranslationY(height * 0.453f);
        binding.plusAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amp ++;
                binding.ampCounter.setText("" + amp);
            }
        });
        binding.ampCounter.setTranslationX(width * 0.598f);
        binding.ampCounter.setTranslationY(height * 0.525f);
        binding.speakerNotesUnamp.setTranslationX(width * 0.073f);
        binding.speakerNotesUnamp.setTranslationY(height * 0.619f);

        binding.speakerNotesAmp.setTranslationX(width * 0.073f);
        binding.speakerNotesAmp.setTranslationY(height * 0.835f);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}