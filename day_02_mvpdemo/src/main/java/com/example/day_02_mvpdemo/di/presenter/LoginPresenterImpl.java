package com.example.day_02_mvpdemo.di.presenter;


import com.example.day_02_mvpdemo.di.component.DaggerLoginComponent;
import com.example.day_02_mvpdemo.di.contract.ILoginContract;
import com.example.day_02_mvpdemo.di.model.LoginModelImpl;
import com.example.day_02_mvpdemo.di.module.LoginModule;

import java.lang.ref.SoftReference;

import javax.inject.Inject;

/**
 */
public class LoginPresenterImpl implements ILoginContract.ILoginPresenter<ILoginContract.ILoginView> {
    ILoginContract.ILoginView loginView;
    private SoftReference<ILoginContract.ILoginView> reference;
    //private ILoginContract.ILoginModel model;
    @Inject
    LoginModelImpl model;

    @Override
    public void attachView(ILoginContract.ILoginView loginView) {
        this.loginView = loginView;
        //软引用包裹
        reference = new SoftReference<>(loginView);
        //耦合性处理
        DaggerLoginComponent.builder().loginModule(new LoginModule()).build().inject(this);
        //M层
        //model = new LoginModelImpl();
        //model = new QueryMoneyModelImpl();
    }

    @Override
    public void detachView(ILoginContract.ILoginView loginView) {
        reference.clear();
    }

    @Override
    public void requstLoginData(String userName, String password) {
        model.containLoginResponseData(userName,password,new ILoginContract.ILoginModel.CallBack() {
            @Override
            public void responseData(String responseData) {
                loginView.showData(responseData);
            }
        });
    }
}
