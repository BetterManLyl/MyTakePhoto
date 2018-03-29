package lyl.mytakephoto.activity.fresco;

import android.net.Uri;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/29.
 */

public class FrescoOneActivity extends BaseActivity {
    private SimpleDraweeView simpleDraweeView;

    @Override
    protected int getLayoutId() {
        return R.layout.fresco_one;
    }

    @Override
    public void initView() {
        super.initView();
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.fre_one_image);
        initImage();
    }

    private void initImage() {
        GenericDraweeHierarchyBuilder ge=new GenericDraweeHierarchyBuilder(this.getResources());
        GenericDraweeHierarchy build = ge.setProgressBarImage(new ProgressBarDrawable()).build();
        Uri uri=Uri.parse("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");
        simpleDraweeView.setHierarchy(build);
        simpleDraweeView.setImageURI(uri);
    }
}
