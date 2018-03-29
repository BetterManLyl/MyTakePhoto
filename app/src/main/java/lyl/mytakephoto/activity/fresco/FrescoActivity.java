package lyl.mytakephoto.activity.fresco;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lyl.mytakephoto.R;
import lyl.mytakephoto.base.BaseActivity;

/**
 * @author lyl
 * @date 2018/3/28.
 * <p>
 * Fresco的使用步骤
 * 1、依赖包  根据不同的需求依赖不同的包   清单文件设置网络的权限
 * 2、在application进行初始化
 * 3、需要在布局中替换掉ImageView 使用 Fresco自带的SimpleDraweeView
 */

public class FrescoActivity extends BaseActivity {

    private ArrayList<String> imageUrlList = new ArrayList<>();

    private SimpleDraweeView simpleDraweeView;
    private RecyclerView recycler;

    private Context context;
    private MyAdapter myAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fresco;
    }

    @Override
    public void initView() {
        context = this;
        super.initView();
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.simpleDraweeView);
        initRecyclser();
        loadImage();
        initData();
    }

    private void initRecyclser() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager line = new LinearLayoutManager(context);
        recycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recycler.setLayoutManager(line);

    }

    private void initData() {
        for (int i = 0; i < 1000; i++) {
            imageUrlList.add("http://avatar.csdn.net/4/E/8/1_y1scp.jpg");
        }
        myAdapter = new MyAdapter();
        recycler.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void loadImage() {

        //设置样式
        GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder =
                new GenericDraweeHierarchyBuilder(this.getResources());
        GenericDraweeHierarchy hierarchy = genericDraweeHierarchyBuilder
                .setProgressBarImage(new ProgressBarDrawable()).build();
//        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
//                .setUri("")
//                .setTapToRetryEnabled(false)
//                .setOldController(simpleDraweeView.getController())
//                .build();
//        simpleDraweeView.setController(draweeController);
        simpleDraweeView.setHierarchy(hierarchy);

        simpleDraweeView.setImageURI(Uri.parse("http://avatar.csdn.net/4/E/8/1_y1scp.jpg"));

    }


    @OnClick({R.id.btn_progress_fresco, R.id.btn_fresco_01, R.id.btn_fresco_02,
            R.id.btn_fresco_03, R.id.btn_fresco_04,R.id.btn_fresco_05,R.id.btn_fresco_06,
            R.id.btn_fresco_07,R.id.btn_fresco_08,R.id.btn_fresco_09})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_progress_fresco:
                startActivity(new Intent(this, FrescoOneActivity.class));//progress
                break;
            case R.id.btn_fresco_01:
                startActivity(new Intent(this, FrescoTwoActivity.class));//不同的裁剪方式
                break;
            case R.id.btn_fresco_02:
                startActivity(new Intent(this, FrescoThreeActivity.class));//圆形和圆角图片
                break;
            case R.id.btn_fresco_03:
                startActivity(new Intent(this, FrescoFourActivity.class));//渐进式加载图片
                break;
            case R.id.btn_fresco_04:
                startActivity(new Intent(this, FrescoFiveActivity.class));//加载gif图片
                break;
            case R.id.btn_fresco_05:
                startActivity(new Intent(this, FrescoSixActivity.class));//多图请求及图片复用
                break;
            case R.id.btn_fresco_06:
                startActivity(new Intent(this, FrescoServenActivity.class));//图片加载的监听
                break;
            case R.id.btn_fresco_07:
                startActivity(new Intent(this, FrescoEightActivity.class));//图片缩放和旋转
                break;
            case R.id.btn_fresco_08:
                startActivity(new Intent(this, FrescoNineActivity.class));//修改图片
                break;
            case R.id.btn_fresco_09:
                startActivity(new Intent(this, FrescoTenActivity.class));//修改图片
                break;
            default:
                break;
        }
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_view, parent, false);

            MyViewHolder myview = new MyViewHolder(view);

            return myview;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
//            GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder =
//                    new GenericDraweeHierarchyBuilder(context.getResources());
//            GenericDraweeHierarchy hierarchy = genericDraweeHierarchyBuilder.setFadeDuration(1000).build();
//            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
//                    .setUri(imageUrlList.get(position))
//                    .setTapToRetryEnabled(true)
//                    .setOldController(holder.sim.getController())
//                    .build();
//            holder.sim.setController(draweeController);
//            holder.sim.setHierarchy(hierarchy);


            Uri uri = Uri.parse(imageUrlList.get(position));
            holder.sim.setImageURI(uri);
            Glide.with(context).load(imageUrlList.get(position)).into(holder.sim);
        }

        @Override
        public int getItemCount() {
            return imageUrlList.size();
        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView sim;

        public MyViewHolder(View itemView) {
            super(itemView);
            sim = itemView.findViewById(R.id.item_image);
        }
    }
}
