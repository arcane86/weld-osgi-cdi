package org.osgi.cdi.integration.api;

import org.osgi.cdi.extension.api.event.InterBundleEvent;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
public interface BundleContainer {

    void fire(InterBundleEvent event);
}
