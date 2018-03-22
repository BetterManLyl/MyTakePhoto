package lyl.mytakephoto.handler;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import lyl.mytakephoto.activity.TakePhotoActivity;

/**
 * @author lyl
 * @date 2018/3/13.
 */

public class MyHandler extends Handler {

    private WeakReference<TakePhotoActivity> mActivity;

    public MyHandler(TakePhotoActivity takePhotoActivity) {
        //将activity设置为弱引用
        mActivity = new WeakReference<TakePhotoActivity>(takePhotoActivity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        TakePhotoActivity takePhotoActivity = mActivity.get();

        switch (msg.what) {
            case 0:
                break;
            default:
                break;
        }
    }
}
