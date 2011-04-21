package org.osgi.cdi.extension.api;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
public interface Services<T> extends Iterable<T> {

    int size();
}
