package com.example.day01_01.model;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 用来做注册的model类
 */
public class RegisterImpl implements IModel {
    public static final String REQUEST_URL = "http://172.17.8.100/small/user/v1/register";
    @Override
    public void requestData(String userName, String pwd,Callback callback) {
        //在这里吗做耗时操作
        new MyTask(userName,pwd,callback).execute(REQUEST_URL);
    }

    class MyTask extends AsyncTask<String,Void,String>{
        private String mUserName;
        private String mPwd;
        private Callback callback;
        public MyTask(String userName,String pwd,Callback callback){
            this.mUserName = userName;
            this.mPwd = pwd;
            this.callback = callback;
        }
       //异常的分类  Exception Error
//        throw 和 throws
        //做耗时操作
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
          callback.setData(s);
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
