package org.osgi.cdi.api.extension.annotation;

import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;

/**
 * Created by IntelliJ IDEA. User: matthieu Date: 22/04/11 Time: 12:07 To change this template use File | Settings |
 * File Templates.
 */
public class FilterAnnotation
        extends AnnotationLiteral<Filter>
        implements Filter {

    private final String value;

    public FilterAnnotation(String value) {
        this.value = value;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Filter.class;
    }

    @Override
    public String value() {
        return value;
    }
}