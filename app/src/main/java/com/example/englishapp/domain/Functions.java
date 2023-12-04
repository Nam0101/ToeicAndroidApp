package com.example.englishapp.domain;

import com.example.englishapp.data.model.Function;

import java.util.ArrayList;

public class Functions {
    private static Functions instance = null;
    private ArrayList<Function> functions;

    private Functions() {
        functions = new ArrayList<>();
    }

    public static Functions getInstance() {
        if (instance == null) {
            instance = new Functions();
        }
        return instance;
    }

    public ArrayList<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(ArrayList<Function> functions) {
        this.functions = functions;
    }

    public void addFunction(Function function) {
        this.functions.add(function);
    }

    public void removeFunction(Function function) {
        this.functions.remove(function);
    }
}