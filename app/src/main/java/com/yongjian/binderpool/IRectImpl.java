package com.yongjian.binderpool;

import android.os.RemoteException;

/**
 * Created by YONGJIAN on 2016/3/31 0031.
 */
public class IRectImpl extends IRect.Stub {
    @Override
    public int area(int length, int width) throws RemoteException {
        return length * width;
    }

    @Override
    public int perimeter(int length, int width) throws RemoteException {
        return length*2 + width*2;
    }
}