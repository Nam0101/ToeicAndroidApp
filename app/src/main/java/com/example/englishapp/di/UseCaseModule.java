package com.example.englishapp.di;

import com.example.englishapp.data.repository.FunctionRepository;
import com.example.englishapp.data.repository.QuestionRepository;
import com.example.englishapp.data.repository.UserRepository;
import com.example.englishapp.domain.ForgotPasswordUseCase;
import com.example.englishapp.domain.GetFunctionsUseCase;
import com.example.englishapp.domain.GetPart1QuestionUseCase;
import com.example.englishapp.domain.GetPart5QuestionUseCase;
import com.example.englishapp.domain.GetPart6QuestionUseCase;
import com.example.englishapp.domain.GetPart7QuestionUseCase;
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
    public static GetPart1QuestionUseCase provideGetPart1QuestionUseCase(QuestionRepository part1QuestionRepository) {
        return new GetPart1QuestionUseCase(part1QuestionRepository);
    }

    @Provides
    @Singleton
    public static GetPart5QuestionUseCase provideGetPart5QuestionUseCase(QuestionRepository part5QuestionRepository) {
        return new GetPart5QuestionUseCase(part5QuestionRepository);
    }

    @Provides
    @Singleton
    public static GetPart6QuestionUseCase provideGetPart6QuestionUseCase(QuestionRepository part6QuestionRepository) {
        return new GetPart6QuestionUseCase(part6QuestionRepository);
    }

    @Provides
    @Singleton
    public static GetPart7QuestionUseCase provideGetPart7QuestionUseCase(QuestionRepository part7QuestionRepository) {
        return new GetPart7QuestionUseCase(part7QuestionRepository);
    }

}