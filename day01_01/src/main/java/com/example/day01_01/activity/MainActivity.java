package com.example.day01_01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day01_01.R;
import com.example.day01_01.presenter.IPresenter;
import com.example.day01_01.presenter.RegistPresenter;
import com.example.day01_01.view.IView;


/**
 * MVP
 * Model(处理数据) View（视图） Presenter（管理层操作m和v）
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView {

    private EditText et_userName;
    private EditText et_password;
    private Button btn_register;
    private IPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mPresenter = new RegistPresenter(this);
    }

    private void initView() {
        et_userName = (EditText) findViewById(R.id.et_userName);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                String phone = et_userName.getText().toString().trim();
                String pwd = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(this, "参数有误", Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.startRequest(phone,pwd);
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void getResponse(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
