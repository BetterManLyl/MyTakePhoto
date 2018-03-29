package lyl.mytakephoto.activity.fresco;

import android.graphics.Color;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/29.
 */

public class FrescoThreeActivity extends BaseActivity {
    @BindView(R.id.fre_three_image)
    SimpleDraweeView freThreeImage;


    @Override
    protected int getLayoutId() {
        return R.layout.fresco_three;
    }

    @Override
    public void initView() {
        super.initView();
        Uri uri = Uri.parse("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");
        freThreeImage.setImageURI(uri);
    }

    @OnClick({R.id.btn_circle_radius_image, R.id.btn_circle_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_circle_radius_image:
                loadImage(0);
                break;
            case R.id.btn_circle_image:
                loadImage(1);
                break;
            default:
                break;
        }
    }

    private void loadImage(int type) {
        GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder =
                new GenericDraweeHierarchyBuilder(this.getResources());
        RoundingParams roundingParams = null;
        if (type == 0) {
            //设置圆角
            roundingParams = RoundingParams.fromCornersRadius(50);
            //设置覆盖背景图
            roundingParams.setOverlayColor(Color.BLUE);
            //设置边框 以及宽度
            roundingParams.setBorder(Color.GREEN, 5);
        } else {
            //设置圆形图片
            roundingParams = RoundingParams.asCircle();
        }
        GenericDraweeHierarchy build = genericDraweeHierarchyBuilder.setRoundingParams(roundingParams).build();
        freThreeImage.setHierarchy(build);
        Uri uri = Uri.parse("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");
        freThreeImage.setImageURI(uri);
    }
}
