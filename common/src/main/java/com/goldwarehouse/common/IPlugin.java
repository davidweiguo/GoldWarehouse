package com.goldwarehouse.common;

/**
 * Created by guo_d on 2016/12/22.
 */
public interface IPlugin {
    void init() throws Exception;
    void uninit();
}