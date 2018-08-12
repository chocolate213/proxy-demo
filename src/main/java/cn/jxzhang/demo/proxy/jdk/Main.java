package cn.jxzhang.demo.proxy.jdk;


import cn.jxzhang.demo.proxy.jdk.advice.After;
import cn.jxzhang.demo.proxy.jdk.advice.Before;
import cn.jxzhang.demo.proxy.jdk.advice.OnException;
import cn.jxzhang.demo.proxy.jdk.factory.ProxyFactory;
import cn.jxzhang.demo.proxy.jdk.target.ManWaiter;
import cn.jxzhang.demo.proxy.jdk.target.Waiter;

/**
 * Main
 *
 * @author zhangjiaxing
 * @version 1.0
 */
public class Main {


    public static void main(String[] args) {
        Before before = () -> System.out.println("before");
        After after = () -> System.out.println("after");
        OnException onException = () -> System.out.println("on exception");

        ProxyFactory proxyFactory = ProxyFactory.getProxyFactory(before, after, onException);

        Waiter proxy = proxyFactory.createProxy(new ManWaiter());
        proxy.sayHello();
        proxy.serve();
    }


}
