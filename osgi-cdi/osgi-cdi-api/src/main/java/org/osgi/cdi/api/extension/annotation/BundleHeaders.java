package org.osgi.cdi.api.extension.annotation;


import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface BundleHeaders {
}
