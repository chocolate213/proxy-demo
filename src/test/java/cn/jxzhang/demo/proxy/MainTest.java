package cn.jxzhang.demo.proxy;

import cn.jxzhang.demo.proxy.cglib.CglibProxyFactory;
import cn.jxzhang.demo.proxy.jdk.JDKProxyFactory;
import cn.jxzhang.demo.proxy.target.TargetObject;
import cn.jxzhang.demo.proxy.target.TargetObjectImpl;
import cn.jxzhang.demo.proxy.target.TargetObjectWithoutInterface;
import org.junit.Assert;
import org.junit.Test;

/**
 * MainTest
 *
 * @author zhangjiaxing
 * @version 1.0
 */
public class MainTest {

    @Test
    public void testJdkProxyTargetObjectWithInterface() {
        TargetObject proxy = JDKProxyFactory.createProxy(new TargetObjectImpl());
        int invoke = proxy.invoke();
        Assert.assertEquals(invoke, 100);
    }

    @Test
    public void testCglibProxyTargetObjectWithInterface() {
        TargetObject proxy = CglibProxyFactory.createProxy(TargetObjectImpl.class);
        int invoke = proxy.invoke();
        Assert.assertEquals(invoke, 100);
    }

    @Test(expected = ClassCastException.class)
    public void testJdkProxyTargetObjectWithoutInterface() {
        TargetObjectWithoutInterface proxy = JDKProxyFactory.createProxy(new TargetObjectWithoutInterface());
        proxy.invoke();
    }

    @Test
    public void testCglibProxyTargetObjectWithoutInterface() {
        TargetObjectWithoutInterface proxy = CglibProxyFactory.createProxy(TargetObjectWithoutInterface.class);
        int invoke = proxy.invoke();
        Assert.assertEquals(invoke, 100);
    }
}