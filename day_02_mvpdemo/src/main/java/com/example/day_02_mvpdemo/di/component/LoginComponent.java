package com.example.day_02_mvpdemo.di.component;


import com.example.day_02_mvpdemo.di.module.LoginModule;
import com.example.day_02_mvpdemo.di.presenter.LoginPresenterImpl;

import dagger.Component;

/**
 * 采用Dagger2 让M层和P层进行关联
 * Dagger2提供一个Component的注解 让M层和P层就关联上了
 */
@Component(modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginPresenterImpl presenter);
}
