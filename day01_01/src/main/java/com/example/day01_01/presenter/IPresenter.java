package com.example.day01_01.presenter;

import com.example.day01_01.view.IView;

/**
 * P层的 父接口
 */
public interface IPresenter {
    //传入V层对象
    void onAttch(IView iView);
    void startRequest(String userName, String pwd);
    void onDeAttch();
}
