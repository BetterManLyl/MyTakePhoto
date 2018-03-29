package lyl.mytakephoto.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * @author lyl
 * @date 2018/3/5.
 */

public class MyService extends Service {

    MyBinder myBinder = new MyBinder();
    /**
     *  从上面的代码我们可以看出MyService继承了Service类，并重写了onBind方法，
     * 该方法是必须重写的，但是由于此时是启动状态的服务，则该方法无须实现，返回null即可，
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.eTag("lyl", "Service----->onCreate()");
    }



    /**
     * 每次通过startService()方法启动Service时都会被回调。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.eTag("lyl", "Service----->onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 服务销毁时的回调
     */
    @Override
    public void onDestroy() {
        LogUtils.eTag("lyl", "Service----->onDestroy()");
        super.onDestroy();
    }

    public void hhsay() {
        ToastUtils.showShort("调用bindservice方法");
    }


    public class MyBinder extends Binder {

        public MyBinder() {

        }

        public MyService getMyService() {
            return MyService.this;
        }

        public void say() {
            hhsay();
        }
    }

    /**
     * 已经弃用
     *
     * @param intent
     * @param startId
     */
    @Override
    public void onStart(Intent intent, int startId) {
        LogUtils.eTag("lyl", "Service----->onStart()");
        super.onStart(intent, startId);
    }
}
