package cn.jxzhang.demo.proxy.jdk.factory;


import cn.jxzhang.demo.proxy.jdk.advice.After;
import cn.jxzhang.demo.proxy.jdk.advice.Before;
import cn.jxzhang.demo.proxy.jdk.advice.OnException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ProxyFactory
 *
 * @author zhangjiaxing
 * @version 1.0
 */
public class ProxyFactory {

    private Before before;

    private After after;

    private OnException onException;

    public ProxyFactory(Before before, After after, OnException onException) {
        this.before = before;
        this.after = after;
        this.onException = onException;
    }

    public static ProxyFactory getProxyFactory(Before before, After after, OnException onException) {
        return new ProxyFactory(before, after, onException);
    }

    @SuppressWarnings("unchecked")
    public <T> T createProxy(T target) {

        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();

        InvocationHandler handler = (proxy, method, args) -> {
            if (before != null) {
                before.execute();
            }

            Object invoke = null;

            try {
                invoke = method.invoke(target, args);
            } catch (Exception e) {
                if (onException != null) {
                    onException.execute();
                }
            }

            if (after != null) {
                after.execute();
            }

            return invoke;
        };

        Object o = Proxy.newProxyInstance(classLoader, interfaces, handler);

        return (T) o;
    }


    public Before getBefore() {
        return before;
    }

    public void setBefore(Before before) {
        this.before = before;
    }

    public After getAfter() {
        return after;
    }

    public void setAfter(After after) {
        this.after = after;
    }

    public OnException getOnException() {
        return onException;
    }

    public void setOnException(OnException onException) {
        this.onException = onException;
    }
}
