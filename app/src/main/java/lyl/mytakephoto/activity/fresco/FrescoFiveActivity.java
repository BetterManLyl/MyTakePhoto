package lyl.mytakephoto.activity.fresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/29.
 */

public class FrescoFiveActivity extends BaseActivity {
    @BindView(R.id.fre_three_image)
    SimpleDraweeView freThreeImage;

    @Override
    protected int getLayoutId() {
        return R.layout.fresco_five;
    }


    @OnClick({R.id.btn_load_gif_image, R.id.btn_load_gif_image_start, R.id.btn_load_gif_image_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_load_gif_image:
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/test.gif"));
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(false)
                        .setOldController(freThreeImage.getController())
                        .build();
                freThreeImage.setController(controller);

                break;
            case R.id.btn_load_gif_image_start:
                if (freThreeImage.getController()!=null){
                    Animatable animatable = freThreeImage.getController().getAnimatable();
                    if (!animatable.isRunning()) {
                        animatable.start();
                    }
                }


                break;
            case R.id.btn_load_gif_image_stop:
                if (freThreeImage.getController()!=null){
                    Animatable animatable = freThreeImage.getController().getAnimatable();
                    if (animatable.isRunning()) {
                        animatable.stop();
                    }
                }

                break;
            default:
                break;
        }
    }
}
