package org.osgi.cdi.api.extension.annotation;

import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;

/**
 * Created by IntelliJ IDEA. User: matthieu Date: 22/04/11 Time: 12:05 To change this template use File | Settings |
 * File Templates.
 */
public class SpecificationAnnotation
        extends AnnotationLiteral<Specification>
        implements Specification {

    private final Class value;

    public SpecificationAnnotation(Class value) {
        this.value = value;
    }

    @Override
    public Class value() {
        return value;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Specification.class;
    }
}