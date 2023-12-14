package com.example.englishapp.domain;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.data.repository.FunctionRepository;

import java.util.ArrayList;

public class GetFunctionsUseCase {

    public final FunctionRepository functionRepository;

    public GetFunctionsUseCase(FunctionRepository functionRepository) {
        this.functionRepository = functionRepository;
    }
    public ArrayList<Function> execute() {
        return functionRepository.getFunctions();
    }
}
