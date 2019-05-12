package com.example.day01_01.model;

/**
 * model的父接口
 */
public interface IModel {
    //请求数据接口，并返回数据,接口回调方法
    void requestData(String userName, String pwd, Callback callback);


    //这个接口是用来保存数据一个链接的桥梁
    interface Callback{
     void setData(String info);
    }
}
