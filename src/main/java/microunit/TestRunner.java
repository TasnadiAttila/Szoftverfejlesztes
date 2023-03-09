package microunit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class TestRunner {
    protected  Class<?> testClass;

    public TestRunner(Class<?> testClass){
        this.testClass = testClass;
    }

    protected List<Method> getAnnotatedMethods(Class<? extends Annotation> annotationClass){
        return Arrays.stream(testClass.getDeclaredMethods()).filter(method -> method.isAnnotationPresent(annotationClass)).toList();
    }

    protected abstract void invokeTestMethod(Method method, Object instance, )
        throws IllegalAccessException;
}
