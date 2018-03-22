package lyl.mytakephoto.javatest;

/**
 * @author lyl
 * @date 2018/3/16.
 */

public class Father  {
    public void initView() {
        System.out.println("father--->initView");
    }


    public  void initData() {
        System.out.println("father--->initData");
    }

    public static void main(String[] args) {
        Father father = new Father();
        father.initView();
        father.initData();
    }
}
