package lyl.mytakephoto.netwotk;

import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/16.
 */

public class NetworkActivity extends BaseActivity {
    @BindView(R.id.btn_network)
    Button btnNetwork;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_network;
    }

    @Override
    public void initView() {
        super.initView();
    }


    @OnClick(R.id.btn_network)
    public void onViewClicked() {


    }
}
