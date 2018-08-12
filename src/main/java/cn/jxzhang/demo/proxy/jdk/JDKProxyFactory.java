package cn.jxzhang.demo.proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * JDKProxyFactory
 *
 * @author zhangjiaxing
 * @version 1.0
 */
public class JDKProxyFactory {

    @SuppressWarnings("unchecked")
    public static  <T> T createProxy(T target) {
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();

        InvocationHandler handler = (proxy, method, args) -> {
            Object invoke = null;

            System.out.println("JDKProxyFactory: before invoke. ");

            try {
                invoke = method.invoke(target, args);
            } catch (Exception e) {
                System.out.println("JDKProxyFactory: on exception. ");
            }

            System.out.println("JDKProxyFactory: after invoke. ");

            return invoke;
        };

        Object o = Proxy.newProxyInstance(classLoader, interfaces, handler);
        return (T) o;
    }
}
