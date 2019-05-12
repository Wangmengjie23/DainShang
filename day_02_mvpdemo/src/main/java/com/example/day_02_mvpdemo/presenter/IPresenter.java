package com.example.day_02_mvpdemo.presenter;

import com.example.day_02_mvpdemo.view.IView;

/**
 * P层的 父接口
 */
public interface IPresenter {
    //传入V层对象
    void onAttch(IView iView);
    void startRequest(String userName,String pwd);
    void onDeAttch();
}
