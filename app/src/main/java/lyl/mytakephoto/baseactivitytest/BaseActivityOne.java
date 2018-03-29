package lyl.mytakephoto.baseactivitytest;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import lyl.mytakephoto.bean.User;
import lyl.mytakephoto.broadcast.MyReceiver;
import lyl.mytakephoto.netwotk.NetworkRegister;

/**
 * @author lyl
 * @date 2018/3/16.
 */

public abstract class BaseActivityOne extends AppCompatActivity implements NetworkRegister.NetworkCallback {

    private User user;

    private MyReceiver myReceiver = null;
    private NetworkRegister networkRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.eTag("lyl", "BaseActivityOne-->onCreate1");
        setContentView(getLayoutId());
        LogUtils.eTag("lyl", "BaseActivityOne-->onCreate2");
        initUser();
        initView();
        initBroadcast();
    }

    public void initBroadcast() {
        networkRegister = new NetworkRegister();
        networkRegister.setNetworkCallback(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(networkRegister, intentFilter);
    }

    public void initUser() {
        if (user == null) {
            LogUtils.eTag("lyl", this.getClass().getName() + ":user");
            user = new User();
        }
    }

    public int getLayoutId() {
        LogUtils.eTag("lyl", "BaseActivityOne-->getLayoutId");
        return 0;
    }

    /**
     * 使用final修饰的方法，不能被子类重写
     */
    final public void get() {
    }

    public void initData() {
    }

    public void initView() {
        LogUtils.eTag("lyl", "BaseActivityOne-->initView");
    }

    @Override
    public void netDisable() {
    }

    @Override
    public void netEnable() {
    }

    private long currentSeconds=0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (currentSeconds>2000){
                finish();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);

    }
}
