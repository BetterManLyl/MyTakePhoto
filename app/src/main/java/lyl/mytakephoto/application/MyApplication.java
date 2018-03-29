package lyl.mytakephoto.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author lyl
 * @date 2018/3/1.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);
        //LeakCanary.install(this);

        //初始化fresco  你只需要调用Fresco.initialize一次即可完成初始化
        Fresco.initialize(this);
    }
}
