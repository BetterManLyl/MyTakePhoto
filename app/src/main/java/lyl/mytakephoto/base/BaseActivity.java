package lyl.mytakephoto.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lyl.mytakephoto.netwotk.NetworkRegister;
import lyl.mytakephoto.util.ActivitiManager;
import lyl.mytakephoto.util.MyUtils;

/**
 * @author lyl
 * @date 2018/3/1.
 */

public abstract class BaseActivity extends AppCompatActivity implements NetworkRegister.NetworkCallback {
    public RxPermissions rxPermissions;
    private Unbinder unbinder = null;
    public static final String TAG = "lyl";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivitiManager.addActivity(this);
        //MyUtils.setActivity(this);
        initView();

        unbinder = ButterKnife.bind(this);
        rxPermissions = new RxPermissions(this);

    }

    @Override
    public void netDisable() {
        initNet(0);
        Toast.makeText(this, "不可用", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void netEnable() {
        initNet(1);
        Toast.makeText(this, "可用", Toast.LENGTH_SHORT).show();
    }

    public void initNet(int type) {
        LogUtils.eTag("lyl","initNet");
    }

    public void goActivity(Class c, Bundle bundle) {
        Intent intent = new Intent(this, c);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    private NetworkRegister networkRegister;

    public void initView() {
        LogUtils.eTag("lyl","baseactivity----initView");
        registerNetwok();

        test();


    }

    public void test() {
        LogUtils.eTag("lyl","baseactivity----test");
    }

    /**
     * 注册监听
     */
    public void registerNetwok() {
        if (networkRegister==null){
            networkRegister = new NetworkRegister();
            networkRegister.setNetworkCallback(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            registerReceiver(networkRegister, intentFilter);
        }
    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiManager.finishActivity(this);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
