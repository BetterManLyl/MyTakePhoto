package lyl.mytakephoto.activity.fresco;

import android.net.Uri;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/29.
 */

public class FrescoEightActivity extends BaseActivity {
    @BindView(R.id.fre_three_image)
    SimpleDraweeView freThreeImage;

    @Override
    protected int getLayoutId() {
        return R.layout.fresco_eight;
    }


    @Override
    public void initView() {
        super.initView();
        freThreeImage.setImageURI("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");
    }

    @OnClick({R.id.btn_load_gif_image, R.id.btn_load_rotate_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_load_gif_image:
                //缩放
                ImageRequest imageRequest = ImageRequestBuilder
                        .newBuilderWithSource(Uri.parse("http://img4.duitang.com/uploads" +
                                "/item/201211/24/20121124175330_ruKEK.jpeg"))
                        .setResizeOptions(new ResizeOptions(50, 50))
                        .build();
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(imageRequest)
                        .setOldController(freThreeImage.getController())
                        .build();
                freThreeImage.setController(draweeController);

                break;
            case R.id.btn_load_rotate_image:
                //旋转
                ImageRequest imageRequest1=ImageRequestBuilder
                        .newBuilderWithSource(Uri.parse("http://img4.duitang.com/uploads" +
                        "/item/201211/24/20121124175330_ruKEK.jpeg"))
                        .setAutoRotateEnabled(true)
                        .build();
                DraweeController draweeController1 = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(imageRequest1)
                        .setOldController(freThreeImage.getController())
                        .build();
                freThreeImage.setController(draweeController1);

                break;
            default:
                break;
        }
    }
}
