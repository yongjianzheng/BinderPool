package com.yongjian.binderpool;

import android.os.RemoteException;

/**
 * Created by YONGJIAN on 2016/3/31 0031.
 */
public class ICalculateImpl extends ICalculate.Stub {
    @Override
    public int add(int first, int second) throws RemoteException {
        return first + second;
    }

    @Override
    public int sub(int first, int second) throws RemoteException {
        return first - second;
    }
}