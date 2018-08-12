package cn.jxzhang.demo.proxy.target;

/**
 * TargetObjectImpl
 *
 * @author zhangjiaxing
 * @version 1.0
 */
public class TargetObjectImpl implements TargetObject {

    @Override
    public int invoke() {
        System.out.println("invoke");
        return 100;
    }
}
