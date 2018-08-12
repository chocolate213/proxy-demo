package cn.jxzhang.demo.proxy.jdk.target;

/**
 * ManWaiter
 *
 * @author zhangjiaxing
 * @version 1.0
 */
public class ManWaiter implements Waiter {
    @Override
    public void serve() {
        System.out.println("man waiter");
    }

    @Override
    public void sayHello() {
        System.out.println("say hello");
    }
}
