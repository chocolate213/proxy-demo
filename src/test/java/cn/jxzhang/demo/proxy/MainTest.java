package cn.jxzhang.demo.proxy;

import cn.jxzhang.demo.proxy.cglib.CglibProxyFactory;
import cn.jxzhang.demo.proxy.demo.TwitterMapper;
import cn.jxzhang.demo.proxy.demo.TwitterMapperImpl;
import cn.jxzhang.demo.proxy.jdk.JDKProxyFactory;
import cn.jxzhang.demo.proxy.target.TargetObject;
import cn.jxzhang.demo.proxy.target.TargetObjectImpl;
import cn.jxzhang.demo.proxy.target.TargetObjectWithoutInterface;
import org.junit.Assert;
import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void testDefaultMethod() {
        TwitterMapper mapper = new TwitterMapperImpl();
        List<String> strings = mapper.selectList();
        String str = mapper.selectOne(1);
        System.out.println(strings);
        System.out.println(str);
    }

    @Test
    public void testStaticMethodWithMH() throws Throwable {
        MethodHandle emptyList = MethodHandles.lookup().findStatic(Collections.class, "emptyList", MethodType.methodType(List.class));
        Object o = emptyList.invokeWithArguments();
        System.out.println(o);

        MethodHandle selectOne = MethodHandles.lookup().findSpecial(TwitterMapper.class, "selectList", MethodType.methodType(List.class), TwitterMapper.class);

        Object invoke = selectOne.invoke(new TwitterMapperImpl());
    }
}