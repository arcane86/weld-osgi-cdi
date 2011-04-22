package org.osgi.cdi.api.integration;

import org.osgi.framework.Bundle;

/**
 *
 * @author Matthieu Clochard
 */
public interface BundleContainerFactory {

    BundleContainer getBundleContainer(Bundle bundle);
    void stopBundleContainer(Bundle bundle);
}
