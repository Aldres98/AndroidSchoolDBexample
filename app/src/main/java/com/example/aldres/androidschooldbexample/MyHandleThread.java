package com.example.aldres.androidschooldbexample;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * Created by Aldres on 29.06.2018.
 */

public class MyHandleThread extends HandlerThread {

    Handler mHandler;

    public MyHandleThread(String name) {
        super(name);
    }

    public MyHandleThread(String name, int priority) {
        super(name, priority);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
    }

}
