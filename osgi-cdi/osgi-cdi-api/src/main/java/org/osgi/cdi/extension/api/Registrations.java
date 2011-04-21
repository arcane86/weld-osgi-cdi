package org.osgi.cdi.extension.api;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
public interface Registrations<T> extends Iterable<Registration<T>> {

    int size();
}
