package lyl.mytakephoto.util;

import android.app.Activity;
import android.content.Intent;

/**
 * @author lyl
 * @date 2018/3/15.
 */

@SuppressWarnings("ConstantConditions")
public class MyUtils {

    /**
     * 静态的activity造成的内存泄漏例子
     * 因为静态的变量是一直贮存在JVM方法区
     * 如果当前的Activty不再使用且Util中的sActivity对象没有更改，会导致当前Activty一直驻留在内存中。
     */
    private static Activity mActivity;

    public static void setActivity(Activity activity) {
        mActivity = activity;
    }


    public static void startActivity(Class activity) {
        Intent intent = new Intent(mActivity, activity);
        mActivity.startActivity(intent);
    }
}
