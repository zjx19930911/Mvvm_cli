package com.iflytek.mvvm_cli.liveData

import androidx.lifecycle.MutableLiveData

/**
 * 封装一层MutableLiveData
 * 简化泛型使用
 * Created by Jianxin on 2021/2/4.
 */

class MyMutableLiveData<T> : MutableLiveData<BaseLiveData<T>>()