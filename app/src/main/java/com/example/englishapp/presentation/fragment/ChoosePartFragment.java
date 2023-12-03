package com.example.englishapp.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.R;
import com.example.englishapp.databinding.FragmentChoosePartBinding;
import com.example.englishapp.presentation.viewmodel.MainActivityViewModel;

public class ChoosePartFragment extends Fragment {
    private MainActivityViewModel mainActivityViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChoosePartBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose_part, container, false);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        binding.setMainActivityViewModel(mainActivityViewModel);
        return binding.getRoot();
    }
}