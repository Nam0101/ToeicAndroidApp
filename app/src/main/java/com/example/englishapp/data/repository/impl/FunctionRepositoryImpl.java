package com.example.englishapp.data.repository.impl;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.data.repository.FunctionRepository;

import java.util.ArrayList;

public class FunctionRepositoryImpl implements FunctionRepository {
    @Override
    public ArrayList<Function> getFunctions() {
        ArrayList<Function> functionsArray = new ArrayList<>();
        functionsArray.add(new Function(1, "Trang chính", "https://i.imgur.com/RjJgVnL.png"));
        functionsArray.add(new Function(2, "Luyện nghe", "https://i.imgur.com/C1e4RYp.png"));
        functionsArray.add(new Function(3, "Luyện đọc", "https://i.imgur.com/2HF92Uv.png"));
        functionsArray.add(new Function(4, "Thi thử", "https://i.imgur.com/d6QdEMd.png"));
        functionsArray.add(new Function(5, "Khóa học online", "https://i.imgur.com/IVf5cc5.png"));
        functionsArray.add(new Function(6, "Từ mới", "https://i.imgur.com/h7F71cK.png"));
        functionsArray.add(new Function(7, "Tài khoản", "https://i.imgur.com/14PTDQ0.png"));
        functionsArray.add(new Function(8, "Đăng xuất", "https://i.imgur.com/62Bzmyp.png"));
        return functionsArray;
    }
}
