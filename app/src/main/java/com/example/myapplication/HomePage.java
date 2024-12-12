package com.example.myapplication;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.animation.ObjectAnimator;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentHomepageBinding;

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
        ObjectAnimator animation = ObjectAnimator.ofFloat(binding.WolfLogoInfo, "rotation",UIHelpers.wolfFrames);
        animation.setDuration(1000);
        binding.WolfLogoInfo.setOnClickListener(view1 -> UIHelpers.darkModeToggle(v, animation, this.getContext(), "fragment_homepage"));
        binding.prevInfoBack.setOnClickListener(view12 -> NavHostFragment.findNavController(HomePage.this)
                .navigate(R.id.action_HomePage_to_FirstFragment));
        UIHelpers.lightDark(v, UIHelpers.darkMode, "fragment_homepage");
    }
}