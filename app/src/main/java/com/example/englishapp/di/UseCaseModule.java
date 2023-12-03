package com.example.englishapp.di;

import com.example.englishapp.data.repository.FunctionRepository;
import com.example.englishapp.data.repository.Part5QuestionRepository;
import com.example.englishapp.data.repository.UserRepository;
import com.example.englishapp.domain.ForgotPasswordUseCase;
import com.example.englishapp.domain.GetFunctionsUseCase;
import com.example.englishapp.domain.GetPart5QuestionUseCase;
import com.example.englishapp.domain.LoginUseCase;
import com.example.englishapp.domain.RegisterUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    @Provides
    @Singleton
    public static LoginUseCase provideLoginUseCase(UserRepository userRepository) {
        return new LoginUseCase(userRepository);
    }

    @Provides
    @Singleton
    public static RegisterUseCase provideRegisterUseCase(UserRepository userRepository) {
        return new RegisterUseCase(userRepository);
    }

    @Provides
    @Singleton
    public static ForgotPasswordUseCase provideForgotPasswordUseCase(UserRepository userRepository) {
        return new ForgotPasswordUseCase(userRepository);
    }

    @Provides
    @Singleton
    public static GetFunctionsUseCase provideGetFunctionsUseCase(FunctionRepository functionRepository){
        return new GetFunctionsUseCase(functionRepository);
    }

    @Provides
    @Singleton
    public static GetPart5QuestionUseCase provideGetPart5QuestionUseCase(Part5QuestionRepository part5QuestionRepository){
        return new GetPart5QuestionUseCase(part5QuestionRepository);
    }
}