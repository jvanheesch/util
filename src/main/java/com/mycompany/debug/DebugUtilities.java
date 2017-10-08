package com.mycompany.debug;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

public final class DebugUtilities {
    private DebugUtilities() {
    }

    @SuppressWarnings("unchecked")
    @SafeVarargs
    public static <I> I wrap(I object, Class<I> yinterface, BiPredicate<Method, Object[]>... predicates) {
        Objects.requireNonNull(object);
        if (!yinterface.isInterface()) {
            throw new IllegalArgumentException("The parameter yinterface must represent an Interface (this is how dynamic proxies work).");
        }
        return (I) Proxy.newProxyInstance(
                yinterface.getClassLoader(),
                new Class[]{yinterface},
                new WrappingInvocationHandler(object, Arrays.stream(predicates).reduce(BiPredicate::or).orElse((method, args) -> true))
        );
    }
}
