package com.yongjian.binderpool;

import android.content.Intent;
import android.app.Service;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by YONGJIAN on 2016/3/31 0031.
 */
public class BinderPoolService extends Service {
    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    public BinderPoolService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }
}