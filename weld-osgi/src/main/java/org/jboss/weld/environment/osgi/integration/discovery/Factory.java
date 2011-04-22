package org.jboss.weld.environment.osgi.integration.discovery;

import org.jboss.weld.environment.osgi.integration.Weld;
import org.osgi.cdi.api.integration.BundleContainer;
import org.osgi.cdi.api.integration.BundleContainerFactory;
import org.osgi.framework.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: matthieu Date: 22/04/11 Time: 11:53 To change this template use File | Settings |
 * File Templates.
 */
public class Factory implements BundleContainerFactory {

    Map<Bundle,BundleContainer> containers = new HashMap<Bundle,BundleContainer>();

    @Override public BundleContainer getBundleContainer(Bundle bundle) {
        BundleContainer container = new Weld(bundle);
        containers.put(bundle,container);
        return container;
    }

    @Override public void stopBundleContainer(Bundle bundle) {
        ((Weld)containers.get(bundle)).shutdown();
        containers.remove(bundle);
    }
}
