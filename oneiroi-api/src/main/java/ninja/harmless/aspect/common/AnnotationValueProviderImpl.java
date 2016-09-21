package ninja.harmless.aspect.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author bnjm@harmless.ninja - 9/19/16.
 */
@Component
public class AnnotationValueProviderImpl<T> implements AnnotationValueProvider<T> {

    @Override
    public <T extends Annotation> T getAnnotation(ProceedingJoinPoint proceedingJoinPoint, Class<T> clazz) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        T annotation = method.getAnnotation(clazz);

        return annotation;
    }
}
