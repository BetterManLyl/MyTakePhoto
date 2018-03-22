package lyl.mytakephoto;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lyl.mytakephoto.activity.SecondActivity;
import lyl.mytakephoto.base.BaseActivity;
import lyl.mytakephoto.huidiao.CallBack;
import lyl.mytakephoto.util.MyUtils;

/**
 * @author lyl 2018.03.01
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
public class MainActivity extends BaseActivity {

    @BindView(R.id.photo_image)
    ImageView photoImage;
    private String phoneNum = "18326897225";
    private final int TAKE_PHOTO = 0x1;
    @BindView(R.id.btn_call_phone)
    Button btnCallPhone;


    private File fileUri;

    @Override
    protected int getLayoutId() {
        initData();
        return R.layout.activity_main;
    }

    public void initData() {

        Intent intent = this.getIntent();
        Uri uri = intent.getData();
    }


    @OnClick(R.id.take_photo)
    public void onClickTakePhoto() {
        takePhoto();
    }

    @OnClick(R.id.btn_viewstub)
    public void btnViewStub() {
        Bundle bundle = new Bundle();
        bundle.putString("key", "value");
        goActivity(SecondActivity.class, bundle);
        // MyUtils.startActivity(this, SecondActivity.class);
        // startActivity(new Intent(this, SecondActivity.class));
        //findViewById(R.id.viewstub).setVisibility(View.VISIBLE);
    }

    /**
     * 拍照
     * 如果还需要读取拍照后的照片，还需要添加读取的权限READ_EXTERNAL_STORAGE
     */
    private void takePhoto() {
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {
                if (value) {
                    takePicture();
                } else {
                    LogUtils.eTag(TAG, "没有权限");
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void takePicture() {
        fileUri = createFile(fileUri, "IMG_", ".jpg");
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //指定拍照后的存储路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileUri));
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //为什么data有时候会返回null？
        //因为在调用拍照意图的时候 指定了拍照存储的路径，这时data就是null。
        //如果不指定存储路径，data可能不为null，因为机型的不同，即使没有指定照片存储路径，data也有可能为null。
        //所以为了安全起见，还是指定保存路径
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                showPhoto(data);
            }
        }
    }

    /**
     * 根据系统时间、前缀、后缀产生一个文件
     */
    public static File createFile(File folder, String prefix, String suffix) {
        folder = new File(Environment.getExternalStorageDirectory(), "/DCIM/camera/");
        if (!folder.exists() || !folder.isDirectory()) folder.mkdirs();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        String filename = prefix + dateFormat.format(new Date(System.currentTimeMillis())) + suffix;
        return new File(folder, filename);
    }

    /**
     * 拍完就显示照片
     * 这时是没有给拍照指定路径的，拍完后直接显示
     */
    private void showPhoto(Intent data) {
        if (data != null) {
            if (data.hasExtra("data")) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                photoImage.setImageBitmap(bitmap);
                LogUtils.eTag(TAG, "拍照返回");
            }
        } else {


            /**
             * 提示相册刷新
             */
            Uri uri = Uri.parse(fileUri.getPath());
            Bitmap bitmap1 = BitmapFactory.decodeFile(uri.getPath());
            photoImage.setImageBitmap(bitmap1);

            /**
             * 注意这几步
             * 是通知系统相册刷新，将拍下来的照片保存到系统相册中
             */
            Intent refreshIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            refreshIntent.setData(uri);
            this.sendBroadcast(refreshIntent);
        }
    }


    @OnClick(R.id.btn_call_phone)
    public void onViewClicked() {
        /**
         * 判断当前手机版本是否大于M  M=23  6.0
         */
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
//                    != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
//                    LogUtils.eTag(TAG, "已经拒绝过一次了");
//                }
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
//            } else {
//
//            }
//        } else {
//            takeCall();
//        }
        //使用rxPermissions进行权限申请
        rxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean value) {

                if (value) {
                    takeCall();
                    LogUtils.eTag(TAG, "同意授权");
                } else {
                    LogUtils.eTag(TAG, "不同意授权");
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 100:
                LogUtils.eTag(TAG, "拨打电话请求返回");
                break;
            default:
                break;
        }
    }


    private void takeCall() {
        /**
         * 拨打电话
         * 1、如果action是ACTION_DIAL 是跳转到拨打电话的界面，不会直接拨打电话，
         * 此时也不需要"android.permission.CALL_PHONE"的权限
         * 2、如果action是ACTION_CALL 直接拨打电话，并且需要在清单文件中申请权限，
         * 如果手机是6.0以上的系统，还需要动态申请拨打电话的权限，否则会崩溃
         */
        //  Intent intent=new Intent(Intent.ACTION_DIAL);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + phoneNum);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
