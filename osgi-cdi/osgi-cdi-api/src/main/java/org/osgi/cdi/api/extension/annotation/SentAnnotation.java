package org.osgi.cdi.api.extension.annotation;

import javax.enterprise.util.AnnotationLiteral;
import java.lang.annotation.Annotation;

/**
 * Created by IntelliJ IDEA. User: matthieu Date: 22/04/11 Time: 12:06 To change this template use File | Settings |
 * File Templates.
 */
public class SentAnnotation
        extends AnnotationLiteral<Sent>
        implements Sent {

    @Override
    public Class<? extends Annotation> annotationType() {
        return Sent.class;
    }
}
