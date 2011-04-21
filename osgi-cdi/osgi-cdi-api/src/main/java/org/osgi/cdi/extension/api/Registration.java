package org.osgi.cdi.extension.api;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
public interface Registration<T> {

    void unregister();

    <T> Service<T> getServiceReference();

}
