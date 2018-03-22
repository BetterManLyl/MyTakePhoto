package lyl.mytakephoto.baseactivitytest;

import com.blankj.utilcode.util.LogUtils;

import lyl.mytakephoto.R;
import lyl.mytakephoto.netwotk.NetworkRegister;

/**
 * @author lyl
 * @date 2018/3/16.
 */

public class TwoActivity extends BaseActivityOne implements NetworkRegister.NetworkCallback {
    @Override
    public int getLayoutId() {
        return R.layout.activity_two;
    }


    /**
     * 如果对父类的方法进行了重写，则不会再调用父类的方法，只会执行子类的方法
     */
    @Override
    public void initUser() {
    }

    @Override
    public void initView() {
        // super.initView();
        LogUtils.eTag("lyl", "TwoActivity-->initView");
    }

    @Override
    public void netDisable() {
        super.netDisable();
    }

    @Override
    public void netEnable() {
        super.netEnable();
    }

}
