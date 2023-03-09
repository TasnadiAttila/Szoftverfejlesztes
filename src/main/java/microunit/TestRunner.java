package microunit;

import java.io.ObjectStreamException;
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

    public void runTestMethods() {
        try {
            TestResultAccumulator accumulator = new CountingTestResultAccumulator();
            for (Method method : getAnnotatedMethods(Test.class)) {
                System.out.println(method);
                Object instance = testClass.getConstructor().newInstance();
            }
        }catch (ReflectiveOperationException | IllegalArgumentException e){
            throw new InvalidTestClassException(e);
        }
    }
    protected abstract void invokeTestMethod(Method method, Object instance, TestResultAccumulator accumulator)
        throws IllegalAccessException;
}
