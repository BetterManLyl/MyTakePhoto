package lyl.mytakephoto.activity.fresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/29.
 */

public class FrescoServenActivity extends BaseActivity {

    @BindView(R.id.btn_load_gif_image)
    Button btnLoadGifImage;
    @BindView(R.id.fre_three_image)
    SimpleDraweeView freThreeImage;

    @Override
    protected int getLayoutId() {
        return R.layout.fresco_serven;
    }

    //图片下载的监听
    ControllerListener con = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onRelease(String id) {
            super.onRelease(id);
        }

        //加载图片完毕
        @Override
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            super.onFinalImageSet(id, imageInfo, animatable);
            imageInfo.getHeight();
        }

        @Override
        public void onSubmit(String id, Object callerContext) {
            super.onSubmit(id, callerContext);
        }

        //加载图片失败
        @Override
        public void onFailure(String id, Throwable throwable) {
            super.onFailure(id, throwable);
        }
    };

    @Override
    public void initView() {
        super.initView();
        //设置占位图
        freThreeImage.getHierarchy().setPlaceholderImage(R.mipmap.ic_launcher);
    }

    @OnClick(R.id.btn_load_gif_image)
    public void onViewClicked() {
        ImageRequest image = ImageRequest.fromUri("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");


        //设置渐进式加载图片
        ImageRequest image1 = ImageRequestBuilder
                .fromRequest(image)
                .setProgressiveRenderingEnabled(true)
                .build();

        DraweeController contor = Fresco.newDraweeControllerBuilder()
                //设置图片加载监听
                .setControllerListener(con)
                .setImageRequest(image1)
                .setOldController(freThreeImage.getController())
                .build();
        freThreeImage.setController(contor);
    }


}
