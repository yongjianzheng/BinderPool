package com.yongjian.binderpool;

import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Handler;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addbtu;
    private Button subbtu;
    private Button areabtu;
    private Button perbtu;
    private final String TAG = "BinderPoolActivity";

    private BinderPool mBinderPool;
    private ICalculate mCalculate;
    private IRect mRect;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            addbtu.setOnClickListener(MainActivity.this);
            subbtu.setOnClickListener(MainActivity.this);
            areabtu.setOnClickListener(MainActivity.this);
            perbtu.setOnClickListener(MainActivity.this);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binderi);
        addbtu = (Button) findViewById(R.id.add_btn);
        subbtu = (Button) findViewById(R.id.sub_btn);
        areabtu = (Button) findViewById(R.id.area_btn);
        perbtu = (Button) findViewById(R.id.per_btn);
        getBinderPool();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void getBinderPool(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mBinderPool = BinderPool.getInstance(MainActivity.this);
                mHandler.obtainMessage().sendToTarget();
            }
        }).start();
    }

    @Override
    public void onClick(final View v) {
        try {
            switch (v.getId()){
                case R.id.add_btn:
                    mCalculate = ICalculateImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_CALCULATE));
                    Log.e(TAG,String.valueOf(mCalculate.add(3,2)));
                    Toast.makeText(MainActivity.this,String.valueOf(mCalculate.add(3,2)),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sub_btn:
                    mCalculate = ICalculateImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_CALCULATE));
                    Log.e(TAG,String.valueOf(mCalculate.sub(3,2)));
                    Toast.makeText(MainActivity.this,String.valueOf(mCalculate.sub(3,2)),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.area_btn:
                    mRect = IRectImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_RECT));
                    Log.e(TAG,String.valueOf(mRect.area(3,2)));
                    Toast.makeText(MainActivity.this,String.valueOf(mRect.area(3,2)),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.per_btn:
                    mRect = IRectImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_RECT));
                    Log.e(TAG,String.valueOf(mRect.perimeter(3,2)));
                    Toast.makeText(MainActivity.this,String.valueOf(mRect.perimeter(3,2)),Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
