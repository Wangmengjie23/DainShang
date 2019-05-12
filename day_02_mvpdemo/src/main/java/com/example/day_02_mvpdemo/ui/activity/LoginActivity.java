package com.example.day_02_mvpdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day_02_mvpdemo.R;
import com.example.day_02_mvpdemo.di.contract.ILoginContract;
import com.example.day_02_mvpdemo.di.presenter.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginContract.ILoginView {

    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private ILoginContract.ILoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //创建P层
        presenter = new LoginPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    public void showData(final String responseData) {
        //线程通信方式 Acaptivity提供一个在主线程运行子线程操作
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //子线程切换到UI线程中
                Toast.makeText(LoginActivity.this, responseData, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        //获取用户名、密码
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        //获取P层,触发请求动作
        presenter.requstLoginData(userName, password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        presenter.detachView(this);
    }
}
