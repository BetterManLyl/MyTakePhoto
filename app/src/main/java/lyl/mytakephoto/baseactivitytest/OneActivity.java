package lyl.mytakephoto.baseactivitytest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;

import lyl.mytakephoto.R;
import lyl.mytakephoto.netwotk.NetworkRegister;
import lyl.mytakephoto.view.Myview;

/**
 * @author lyl
 * @date 2018/3/16.
 */

public class OneActivity extends BaseActivityOne implements NetworkRegister.NetworkCallback {

    private Handler handler;
    private Myview myview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MyThread().start();

    }

    @Override
    public int getLayoutId() {
        LogUtils.eTag("lyl", "OneActivity-->getLayoutId");
        return R.layout.activity_one;
    }


    @Override
    public void initView() {
        initData();
        myview= (Myview) findViewById(R.id.my_view);
        myview.setXBottom("和哈呵呵呵");

        LogUtils.eTag("lyl", "OneActivity-->initView");
        findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.obtainMessage(0, "这是主线程handler发送给子线程的").sendToTarget();
               // startActivity(new Intent(OneActivity.this, TwoActivity.class));
            }
        });
    }

    @Override
    public void netDisable() {
        super.netDisable();
    }

    @Override
    public void netEnable() {
        super.netEnable();
    }


    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            handler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 0:
                                LogUtils.eTag("lyl", msg.obj.toString());
                                break;
                            default:
                                break;
                        }
                    }
            };
            Looper.loop();
        }
    }
}
