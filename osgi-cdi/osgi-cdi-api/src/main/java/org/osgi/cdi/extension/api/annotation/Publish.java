package org.osgi.cdi.extension.api.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
@Target({ TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Publish {
    public Class[] contracts() default {};
    public String[] properties() default {};
    public boolean useQualifiersAsProperties() default false;
}
