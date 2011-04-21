package org.osgi.cdi.integration.api;

import java.util.Collection;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
public interface BundleContainers {

    Collection<BundleContainer> getContainers();
}
