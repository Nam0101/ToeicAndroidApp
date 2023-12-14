package com.example.englishapp.data.repository.impl;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.data.repository.FunctionRepository;

import java.util.ArrayList;

public class FunctionRepositoryImpl implements FunctionRepository {
    @Override
    public ArrayList<Function> getFunctions() {
        ArrayList<Function> functionsArray = new ArrayList<>();
        functionsArray.add(new Function(1, "Trang chính", "https://i.imgur.com/RjJgVnL.png"));
        functionsArray.add(new Function(2, "Luyện tập", "https://imgur.com/TMoy3rz.png"));
        functionsArray.add(new Function(3, "Thi thử", "https://i.imgur.com/d6QdEMd.png"));
        functionsArray.add(new Function(4, "Khóa học online", "https://i.imgur.com/IVf5cc5.png"));
        functionsArray.add(new Function(5, "Từ mới", "https://i.imgur.com/h7F71cK.png"));
        functionsArray.add(new Function(6, "Tài khoản", "https://i.imgur.com/14PTDQ0.png"));
        functionsArray.add(new Function(7, "Đăng xuất", "https://i.imgur.com/62Bzmyp.png"));
        return functionsArray;
    }
}
