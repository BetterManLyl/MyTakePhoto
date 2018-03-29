package lyl.mytakephoto.activity.fresco;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
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

public class FrescoFourActivity extends BaseActivity {
    @BindView(R.id.fre_three_image)
    SimpleDraweeView freThreeImage;


    @Override
    protected int getLayoutId() {
        return R.layout.fresco_four;
    }



    @OnClick(R.id.btn_load_image)
    public void onViewClicked() {
        Uri uri=Uri.parse("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(freThreeImage.getController())
                .build();
        freThreeImage.getHierarchy().setRoundingParams(RoundingParams.asCircle());
        freThreeImage.setController(controller);
    }
}
