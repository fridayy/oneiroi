package ninja.harmless.aspect.common;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Annotation;

/**
 * @author bnjm@harmless.ninja - 9/19/16.
 */
public interface AnnotationValueProvider<T> {
    <T extends Annotation> T getAnnotation(ProceedingJoinPoint proceedingJoinPoint, Class<T> clazz);
}
