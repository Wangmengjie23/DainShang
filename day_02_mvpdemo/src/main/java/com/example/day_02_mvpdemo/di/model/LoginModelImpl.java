package com.example.day_02_mvpdemo.di.model;

import android.os.AsyncTask;

import com.example.day_02_mvpdemo.data.Constant;
import com.example.day_02_mvpdemo.di.contract.ILoginContract;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */
public class LoginModelImpl implements ILoginContract.ILoginModel {

    @Override
    public void containLoginResponseData(final String userName, final String password, ILoginContract.ILoginModel.CallBack callback) {
        new MyTask(userName,password,callback).execute(Constant.LOGIN_URL);
    }
    class MyTask extends AsyncTask<String,Void,String> {
        private String mUserName;
        private String mPwd;
        private ILoginContract.ILoginModel.CallBack callback;
        public MyTask(String userName,String pwd,ILoginContract.ILoginModel.CallBack callback){
            this.mUserName = userName;
            this.mPwd = pwd;
            this.callback = callback;
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                String params = "phone="+mUserName+"&pwd="+mPwd;
                outputStream.write(params.getBytes());
                outputStream.flush();
                outputStream.close();
                if (connection.getResponseCode()==200){
                    InputStream inputStream = connection.getInputStream();
                    String str = getStr(inputStream);
                    return str;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        //成功的回掉
        @Override
        protected void onPostExecute(String s) {
            callback.responseData(s);
        }
    }
    //把输入流里面数据读出来
    public String getStr(InputStream inputStream) throws IOException {
        StringBuffer sb = new StringBuffer();
        byte[] bytes = new byte[1024];
        int leng = 0;
        while ((leng = inputStream.read(bytes))!=-1){
            String info = new String(bytes,0,leng);
            sb.append(info);
        }
        return sb.toString();
    }

}
