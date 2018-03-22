package lyl.mytakephoto.activity;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import lyl.mytakephoto.MainActivity;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;
import lyl.mytakephoto.util.MyUtils;

/**
 * @author lyl
 * @date 2018/3/14.
 */

public class SecondActivity extends BaseActivity {

    /**
     * 在Java中内部类的定义与使用一般为成员内部类与匿名内部类，
     * 他们的对象都会隐式持有外部类对象的引用，影响外部类对象的回收。
     */
    static TestClass testClass;

    public int age;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void initView() {
        super.initView();
        exampleOne();
       // MyUtils.startActivity(MainActivity.class);
        // handler.postDelayed(new MyRun(), 2000);
        //   finish();
//        if (testClass == null) {
//            testClass = new TestClass();
//        }

    }

    class MyRun implements Runnable {

        @Override
        public void run() {
            LogUtils.eTag("lyl", "test");
        }
    }

    private void exampleOne() {
        new MyThread().start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true) {
//                    SystemClock.sleep(1000);
//                }
//            }
//        }).start();
    }

    class TestClass {
        private String name;

        public TestClass() {


        }

        public void process() {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.eTag("lyl", "onDestroy");
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                SystemClock.sleep(1000);
            }
        }
    }
}
