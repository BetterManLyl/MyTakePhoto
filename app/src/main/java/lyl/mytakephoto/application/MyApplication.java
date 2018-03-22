package lyl.mytakephoto.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author lyl
 * @date 2018/3/1.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        LeakCanary.install(this);

    }
}
