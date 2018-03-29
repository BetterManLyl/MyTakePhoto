package lyl.mytakephoto.activity.fresco;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/29.
 */

public class FrescoNineActivity extends BaseActivity {
    @BindView(R.id.fre_three_image)
    SimpleDraweeView freThreeImage;

    @Override
    protected int getLayoutId() {
        return R.layout.fresco_nine;
    }

    Postprocessor post=new BasePostprocessor() {
        @Override
        public String getName() {
            return "redMeshPostprocessor";
        }
        @Override
        public void process(Bitmap bitmap) {
            for (int x = 0; x < bitmap.getWidth(); x+=2) {
                for (int y = 0; y < bitmap.getHeight(); y+=2) {
                    bitmap.setPixel(x, y, Color.RED);
                }
            }
        }
    };

    @Override
    public void initView() {
        super.initView();
        freThreeImage.setImageURI("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");

    }

    @OnClick(R.id.btn_load_gif_image)
    public void onViewClicked() {

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse("http://img4.duitang.com/upload" +
                "s/item/201211/24/20121124175330_ruKEK.jpeg"))
                .setPostprocessor(post)
                .build();
        DraweeController control = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(freThreeImage.getController())
                .build();
        freThreeImage.setController(control);
    }
}
