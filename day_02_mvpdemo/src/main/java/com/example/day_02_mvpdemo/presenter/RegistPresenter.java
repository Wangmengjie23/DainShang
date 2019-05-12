package com.example.day_02_mvpdemo.presenter;

import com.example.day_02_mvpdemo.model.IModel;
import com.example.day_02_mvpdemo.model.RegisterImpl;
import com.example.day_02_mvpdemo.view.IView;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * p层的实现类
 */
public class RegistPresenter implements IPresenter {
    private IModel mIModel;
    private IView mIView;
    //参考案例
    private List mList;//mList = new ArrayList();
    private SoftReference<IView> mSoft;
    public RegistPresenter(IView iView) {
        //多态  父类引用指向子类实例
        mIModel = new RegisterImpl();
        this.mIView = iView;
        mSoft = new SoftReference<>(mIView);
    }

      @Override
    public void onAttch(IView iView) {
        this.mIView = iView;
    }

    @Override
    public void startRequest(String userName, String pwd) {
        //new接口 用的匿名内部类
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
        mSoft.clear();
    }
}
