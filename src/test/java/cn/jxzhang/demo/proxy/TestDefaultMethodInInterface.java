package cn.jxzhang.demo.proxy;

import cn.jxzhang.demo.proxy.demo.TwitterMapper;
import org.junit.Assert;
import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * TestDefaultMethodInInterface
 *
 * @author zhangjiaxing
 */
public class TestDefaultMethodInInterface {

    @Test
    public void testJdk8DefaultMethodInInterface() {

        // 这里不能直接使用 TwitterMapper.class.getInterfaces()，因为代理目标是一个接口，接口没有声明接口，所以使用直接创建 Class 数组的方式
        TwitterMapper o = (TwitterMapper) Proxy.newProxyInstance(TwitterMapper.class.getClassLoader(), new Class[] {TwitterMapper.class}, new Java8InvocationHandler());

        String s = o.selectOne(1);

        Assert.assertNull(s);


    }
//
//
//    @Test
//    public void testJdk9DefaultMethodInInterface() {
//        TwitterMapper o = (TwitterMapper) Proxy.newProxyInstance(getClass().getClassLoader(), TwitterMapper.class.getInterfaces(), new Java9InvocationHandler());
//        String s = o.selectOne(1);
//        Assert.assertNull(s);
//    }

    /**
     * JDK 1.8 中 处理接口中的 默认方法：直接执行该方法
     */
    private static final class Java8InvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isDefault()) {
                System.out.println("encounter a default method, execute method body");

                Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                        .getDeclaredConstructor(Class.class, int.class);
                constructor.setAccessible(true);

                Class<?> declaringClass = method.getDeclaringClass();
                int allModes = MethodHandles.Lookup.PUBLIC | MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED | MethodHandles.Lookup.PACKAGE;

                return constructor.newInstance(declaringClass, allModes)
                        .unreflectSpecial(method, declaringClass)
                        .bindTo(proxy)
                        .invokeWithArguments(args);
            }

            System.out.println("not a default method, do nothing");
            return null;

        }
    }
//
//    /**
//     * JDK 1.9 中，接口中的默认方法：直接执行该方法
//     */
//    private static final class Java9InvocationHandler implements InvocationHandler {
//
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            if (method.isDefault()) {
//                Class<?> declaringClass = method.getDeclaringClass();
//                MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(declaringClass, MethodHandles.lookup());
//                MethodType methodType = MethodType.methodType(method.getReturnType(), method.getParameterTypes());
//                MethodHandle aStatic = lookup.findSpecial(declaringClass, method.getName(), methodType, declaringClass);
//                return aStatic.bindTo(proxy).invokeWithArguments(args);
//            }
//
//            return null;
//        }
//    }
}