package com.example.day01_01.presenter;


import com.example.day01_01.model.IModel;
import com.example.day01_01.model.RegisterImpl;
import com.example.day01_01.view.IView;

import java.util.List;

/**
 * p层的实现类
 */
public class RegistPresenter implements IPresenter {
    private IModel mIModel;
    private IView mIView;
    //参考案例
    private List mList;
    //mList = new ArrayList();
    public RegistPresenter(IView iView) {
        //多态  父类引用指向子类实例
        mIModel = new RegisterImpl();
        this.mIView = iView;
    }

    @Override
    public void onAttch(IView iView) {
        this.mIView = iView;
    }

    @Override
    public void startRequest(String userName, String pwd) {
        mIModel.requestData(userName, pwd, new IModel.Callback() {
            @Override
            public void setData(String info) {
                mIView.getResponse(info);
            }
        });
    }

    //防止内存溢出
    @Override
    public void onDeAttch() {
        if (mIModel != null) {
            mIModel = null;
        }
        if (mIView != null) {
            mIView = null;
        }
    }
}
