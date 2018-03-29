package lyl.mytakephoto.activity.fresco;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/29.
 */

public class FrescoTwoActivity extends BaseActivity {
    @BindView(R.id.fre_two_image)
    SimpleDraweeView freOneImage;

    @BindView(R.id.tv_type_content)
    TextView tv_type_content;

    private int image_type = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fresco_two;
    }


    @Override
    public void initView() {
        super.initView();
        Uri uri = Uri.parse("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");
        GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(this.getResources());
        GenericDraweeHierarchy build = null;
        switch (image_type) {
            case 0:
                build = genericDraweeHierarchyBuilder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER).build();
                tv_type_content.setText("居中，无缩放");
                break;
            case 1:
                build = genericDraweeHierarchyBuilder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP).build();
                tv_type_content.setText("保持宽高比缩小或放大，使得两边都大于或等于显示边界。居中显示");
                break;
            case 2:
                build = genericDraweeHierarchyBuilder.setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP).build();
                tv_type_content.setText("同centerCrop, 但居中点不是中点，而是指定的某个点,这里我设置为图片的左上角那点");
                break;
            case 3:
                build = genericDraweeHierarchyBuilder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build();
                tv_type_content.setText("使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片");
                break;
            case 4:
                build = genericDraweeHierarchyBuilder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build();
                tv_type_content.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内。居中显示");
                break;
            case 5:
                build = genericDraweeHierarchyBuilder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build();
                tv_type_content.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界左上对齐");
                break;
            case 6:
                build = genericDraweeHierarchyBuilder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_END).build();
                tv_type_content.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界右下对齐");
                break;
            case 7:
                build = genericDraweeHierarchyBuilder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
                tv_type_content.setText("不保持宽高比，填充满显示边界");
                break;
            default:
                break;
        }
        if (build!=null){
            freOneImage.setHierarchy(build);
        }
        freOneImage.setImageURI(uri);
    }

    @OnClick({R.id.btn_center, R.id.btn_center_crop, R.id.btn_focus_crop,
            R.id.btn_center_inside, R.id.btn_center_fit, R.id.btn_fit_start, R.id.btn_fit_end,R.id.btn_fit_xy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_center://居中 无缩放
                image_type = 0;
                break;
            case R.id.btn_center_crop:
                image_type = 1;
                break;
            case R.id.btn_focus_crop:
                image_type = 2;
                break;
            case R.id.btn_center_inside:
                image_type = 3;
                break;
            case R.id.btn_center_fit:
                image_type = 4;
                break;
            case R.id.btn_fit_start:
                image_type = 5;
                break;
            case R.id.btn_fit_end:
                image_type = 6;
                break;
            case R.id.btn_fit_xy:
                image_type = 7;
                break;
            default:
                break;
        }
        initView();
    }

}
