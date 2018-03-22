package lyl.mytakephoto.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.ToastUtils;

/**
 * @author lyl
 * @date 2018/3/5.
 * 自定义广播接收者BroadcastReceiver
 *
 * 默认情况下，广播接收器运行在 UI 线程，因此，onReceive()方法不能执行耗时操作，否则将导致ANR
 *
 */
//继承BroadcastReceiver基类
public class MyReceiver extends BroadcastReceiver {

    // 复写onReceive()方法
    // 接收到广播后，则自动调用该方法
    @Override
    public void onReceive(Context context, Intent intent) {
        //写入接收广播后的操作
       String action= intent.getAction();
        if (action.equals("aaa")){
            ToastUtils.showShort("接收到了广播");
        }
    }


}
