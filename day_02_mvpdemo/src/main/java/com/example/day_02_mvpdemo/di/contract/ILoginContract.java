package com.example.day_02_mvpdemo.di.contract;


/**
 * 契约类接口
 */
public interface ILoginContract {
    //V层接口
     interface ILoginView {
        //数据显示
         void showData(String responseData);
    }

    //P层接口
     interface ILoginPresenter<ILoginView> {
        //绑定
         void attachView(ILoginView loginView);

        //解绑
         void detachView(ILoginView loginView);

        //数据请求，请求M层数据，做登录处理
         void requstLoginData(String userName, String password);

    }

    //M层接口
     interface ILoginModel {
        //做登录的接口的请求
         void containLoginResponseData(String userName, String password, CallBack callback);

        //接口回调
         interface CallBack {
            //回显数据方法
            void responseData(String responseData);
        }
    }

}
