package com.mycompany.debug;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.BiPredicate;

public class WrappingInvocationHandler<I> implements InvocationHandler {
    private final I wrappedObject;
    private final BiPredicate<Method, Object[]> predicateUsedForBreakPoint;

    WrappingInvocationHandler(I wrappedObject, BiPredicate<Method, Object[]> predicateUsedForBreakPoint) {
        this.wrappedObject = wrappedObject;
        this.predicateUsedForBreakPoint = predicateUsedForBreakPoint;
    }

    @SuppressWarnings("IfStatementWithIdenticalBranches")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (this.predicateUsedForBreakPoint.test(method, args)) {
            // put breakpoint on the line below
            return method.invoke(this.wrappedObject, args);
        }
        return method.invoke(this.wrappedObject, args);
    }

    @SuppressWarnings("EqualsReplaceableByObjectsCall")
    public static BiPredicate<Method, Object[]> methodName(String... methodNames) {
        return (method, unusedArgs) -> Arrays.stream(methodNames).anyMatch(name -> name.equals(method.getName()));
    }
}
