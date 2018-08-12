package cn.jxzhang.demo.proxy.target;

/**
 * TargetObjectWithoutInterface
 *
 * @author zhangjiaxing
 * @version 1.0
 */
public class TargetObjectWithoutInterface {

    public int invoke() {
        System.out.println("invoke");
        return 100;
    }
}
