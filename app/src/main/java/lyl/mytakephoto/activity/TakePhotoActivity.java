package lyl.mytakephoto.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HandshakeCompletedListener;

import butterknife.BindView;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;
import lyl.mytakephoto.broadcast.MyReceiver;
import lyl.mytakephoto.huidiao.CallBack;
import lyl.mytakephoto.netwotk.NetworkActivity;
import lyl.mytakephoto.netwotk.NetworkRegister;
import lyl.mytakephoto.service.MyService;

/**
 * @author lyl
 * @date 2018/3/1.
 */

public class TakePhotoActivity extends BaseActivity implements CallBack {
    private static final int TAKE_PHOTO = 0X11;
    private static final int CROP_PHOTO = 0X12;
    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_network)
    TextView tv_network;

    private File file;
    private Intent intent;

    public int age;

    private MyService.MyBinder mybinder;
    private NetworkRegister networkRegister = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LogUtils.eTag("lyl", "handleMessage");
        }
    };

    @OnClick(R.id.btn_remove_handler)
    public void removeHandler() {
        if (handler != null) handler.removeCallbacks(run);
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            LogUtils.eTag("lyl", Thread.currentThread().getName());
            if (handler != null) handler.postDelayed(this, 1000);
        }
    };

    @OnClick(R.id.btn_send_handler)
    public void handlerOnclick() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.eTag("lyl", Thread.currentThread().getName());
                Toast.makeText(TakePhotoActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });

        handler.postDelayed(run, 1000);

        Message me = new Message();
        handler.sendMessage(me);

    }


    @Override
    public void initView() {
        //super.initView();
        LogUtils.eTag("lyl","TakePhotoActivity----initView");
       // startActivity(new Intent(this, NetworkActivity.class));
    }

    @Override
    public void initNet(int type) {
        super.initNet(type);
        switch (type) {
            case 0:
                tv_network.setText("不可用");
                break;
            case 1:
                tv_network.setText("可用");
                break;
            default:
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_take_photo;
    }

    public void initNet() {

        String url = "";
        try {
            URL getUrl = new URL(url);
            URLConnection httpURLConnection = getUrl.openConnection();
            httpURLConnection.connect();
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @OnClick(R.id.btn_take_photo)
    public void onViewClicked() {
        photo();
    }


    @OnClick(R.id.btn_bind_service_method)
    public void toast() {
        mybinder.say();
    }

    /**
     * 动态注册广播
     */
    @OnClick(R.id.btn_register_receiver)
    public void onRegisterReceiver() {
        MyReceiver broad = new MyReceiver();
        IntentFilter interf = new IntentFilter();
        interf.addAction("aaa");
        registerReceiver(broad, interf);
    }

    /**
     * 发送广播
     */
    @OnClick(R.id.btn_send_receiver)
    public void onsendReceiver() {
        Intent intent = new Intent();
        intent.setAction("aaa");
        sendBroadcast(intent);
    }

    /**
     * 绑定service
     */
    @OnClick(R.id.btn_bind_service)
    public void onBindService() {
        bindService(new Intent(this, MyService.class), serviceConn, Service.BIND_AUTO_CREATE);
    }

    /**
     * 启动service
     * 一旦以这种方式启动service，则服务会在后台无限期运行，即使启动服务的组件已经被销毁也不受影响
     * 除非手动调用才会停止
     */
    @OnClick(R.id.btn_start_service)
    public void onStartService() {
        intent = new Intent(this, MyService.class);
        startService(intent);
    }


    @OnClick(R.id.btn_logout_service)
    public void onLogoutService() {
        if (intent != null) {
            stopService(intent);
        }

    }

    MyService myService = null;

    private ServiceConnection serviceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mybinder = (MyService.MyBinder) service;
            myService = mybinder.getMyService();
        }

        //意外中断的时候调用该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 测试隐式的跳转
     * 携带data数据跳转
     */
    @OnClick(R.id.btn_test)
    public void test1() {
        Intent intent = new Intent();
        intent.setData(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath())));
        intent.setClassName("lyl.mytakephoto", "lyl.mytakephoto.MainActivity");
//        intent.setAction(Intent.ACTION_CALL);
//        intent.setData(Uri.parse("tel:" + "18326897225"));
//        intent.setDataAndType(Uri.parse("tel:" + "18326897225"), "");
        startActivity(intent);
    }

    /**
     * 拍照
     */
    private void photo() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(createFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    public File createFile() {
        file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        return file;
    }

    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;

    public void gotoCilp() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(file), "image/*");
        //发送裁剪信号
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", OUTPUT_X);
        intent.putExtra("outputY", OUTPUT_Y);
        intent.putExtra("scale", true);
        //将剪切的图片保存到目标Uri中
        //1-false用uri返回图片
        //2-true直接用bitmap返回图片（此种只适用于小图片，返回图片过大会报错）
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, CROP_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            if (requestCode == TAKE_PHOTO) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                image.setImageBitmap(bitmap);
                gotoCilp();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void excute() {

    }
}
