package lyl.mytakephoto.activity.fresco;

import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/29.
 */

public class FrescoSixActivity extends BaseActivity {
    @BindView(R.id.fre_three_image)
    SimpleDraweeView freThreeImage;

    @Override
    protected int getLayoutId() {
        return R.layout.fresco_six;
    }


    @Override
    public void initView() {
        super.initView();
        Uri uri = Uri.parse("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");

        GenericDraweeHierarchyBuilder gen = new GenericDraweeHierarchyBuilder(this.getResources()).setProgressBarImage(new ProgressBarDrawable());
        GenericDraweeHierarchy genhier = gen.build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setLowResImageRequest(ImageRequest.fromUri(uri))
                .setImageRequest(ImageRequest.fromUri(uri))
                .setOldController(freThreeImage.getController())
                .build();

        freThreeImage.setHierarchy(genhier);
        freThreeImage.setController(controller);
    }

    @OnClick({R.id.btn_load_gif_image, R.id.btn_load_gif_image_start, R.id.btn_load_gif_image_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_load_gif_image:
                initView();
                break;
            case R.id.btn_load_gif_image_start://加载缩略图 本功能仅支持本地URI，并且是JPEG图片格式
                //图片地址
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/1.jpg"));
                //加载图片的请求
                ImageRequest image = ImageRequestBuilder
                        .newBuilderWithSource(uri)
                        .setLocalThumbnailPreviewsEnabled(true)
                        .build();
                //控制图片的加载
                DraweeController control = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(image)
                        .setOldController(freThreeImage.getController())
                        .build();
                //加载图片
                freThreeImage.setController(control);
                break;
            case R.id.btn_load_gif_image_stop://本地图片复用,加载最先可用的图片

                //首先尝试获取本地压缩后的图片 URI，如果失败的话，尝试获取本地原始图片 URI，
                //如果还是失败的话，尝试获取上传到网络的图片 URI
                Uri uri1, uri2;
                uri1 = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/1.jpg"));
                uri2 = Uri.parse("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");
                ImageRequest request1 = ImageRequest.fromUri(uri1);
                ImageRequest request2 = ImageRequest.fromUri(uri2);
                ImageRequest[] requests = {request1, request2};
                DraweeController control2 = Fresco.newDraweeControllerBuilder()
                        .setFirstAvailableImageRequests(requests)
                        .setOldController(freThreeImage.getController())
                        .build();
//加载失败时，显示该图片
//GenericDraweeHierarchy hire=new GenericDraweeHierarchyBuilder(this.getResources()).build();
//hire.setFailureImage(R.mipmap.ic_launcher);

                //freThreeImage.setHierarchy(hire);
                freThreeImage.setController(control2);

                break;
            default:
                break;
        }
    }
}
