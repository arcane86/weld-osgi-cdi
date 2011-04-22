package org.osgi.cdi.api.extension.annotation;


import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface OSGiBundle {
    /**
     * @return the symbolic name of the bundle
     */
    @Nonbinding
    String value();

    @Nonbinding
    String version() default "";
}
