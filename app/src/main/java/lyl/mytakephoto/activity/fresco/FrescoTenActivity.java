package lyl.mytakephoto.activity.fresco;

import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/29.
 */

public class FrescoTenActivity extends BaseActivity {
    @BindView(R.id.btn_load_gif_image)
    Button btnLoadGifImage;
    @BindView(R.id.ll_simple)
    LinearLayout linearLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fresco_ten;
    }


    @OnClick(R.id.btn_load_gif_image)
    public void onViewClicked() {
        linearLayout.removeAllViews();
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
        //设置宽高比

        simpleDraweeView.setAspectRatio(2.0f);
        simpleDraweeView.setImageURI("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");
        linearLayout.addView(simpleDraweeView);
    }
}
