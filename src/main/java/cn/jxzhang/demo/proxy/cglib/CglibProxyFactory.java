package cn.jxzhang.demo.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * CglibProxyFactory
 *
 * @author zhangjiaxing
 * @version 1.0
 */
public class CglibProxyFactory {
    private static final Enhancer enhancer = new Enhancer();

    public static <T> T createProxy(Class<T> tClass) {
        MethodInterceptor methodInterceptor = (o, method, objects, methodProxy) -> {

            Object result = null;

            System.out.println("CglibProxyFactory: before invoke. ");

            try {
                result = methodProxy.invokeSuper(o, objects);
            } catch (Throwable throwable) {
                System.out.println("CglibProxyFactory: on exception. " + throwable);
            }

            System.out.println("CglibProxyFactory: after invoke. ");

            return result;
        };

        // 设置生成的代理对象需要继承的父类（被增强对象）
        enhancer.setSuperclass(tClass);

        // 设置所有方法回调
        enhancer.setCallback(methodInterceptor);
        return tClass.cast(enhancer.create());
    }
}
