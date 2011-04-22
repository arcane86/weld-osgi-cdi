package org.jboss.weld.environment.osgi;

import org.jboss.weld.environment.osgi.integration.discovery.Factory;
import org.osgi.cdi.api.integration.BundleContainerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Created by IntelliJ IDEA. User: matthieu Date: 22/04/11 Time: 12:07 To change this template use File | Settings |
 * File Templates.
 */
public class Activator implements BundleActivator {
    @Override public void start(BundleContext context) throws Exception {
        context.registerService(BundleContainerFactory.class.getName(),new Factory(),null);
    }

    @Override public void stop(BundleContext context) throws Exception {
    }
}
