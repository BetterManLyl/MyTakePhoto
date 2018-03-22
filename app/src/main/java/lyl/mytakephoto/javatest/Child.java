package lyl.mytakephoto.javatest;

/**
 * @author lyl
 * @date 2018/3/16.
 */

public class Child extends Father{

    @Override
    public void initView() {
        //super.initView();
        System.out.print("child");
    }

    @Override
    public void initData() {

        System.out.print("child initData");
    }
}
