package com.example.day_02_mvpdemo.di.module;


import com.example.day_02_mvpdemo.di.model.LoginModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * M层 采用Dagger2 的注解@Module @Provides
 */
@Module
public class LoginModule {
    //作用：提供M层的实例对象
    @Provides
    public LoginModelImpl getLoginModel() {
        return new LoginModelImpl();
    }
}
