package org.jboss.weld.osgi.integration;

import org.osgi.cdi.extension.api.event.InterBundleEvent;
import org.osgi.cdi.integration.api.BundleContainer;
import org.osgi.cdi.integration.api.BundleContainers;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.BeanManager;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
public class WeldEmbedded {

    private final Weld weld;
    private final ExtensionActivator activator;
    private final BundleContext context;
    private Collection<ServiceRegistration> regs = new ArrayList<ServiceRegistration>();

    private WeldEmbedded(Weld weld, ExtensionActivator activator, BundleContext context) {
        this.weld = weld;
        this.activator = activator;
        this.context = context;
    }

    public static WeldEmbedded startFor(BundleContext context) throws Exception {
        boolean set = CDIOSGiExtension.currentBundle.get() != null;
        CDIOSGiExtension.currentBundle.set(context.getBundle().getBundleId());
        WeldEmbedded embedded =
                new WeldEmbedded(new Weld(context.getBundle()),
                new ExtensionActivator(), context);
        try {
            embedded.regs.add(
                    context.registerService(Event.class.getName(),
                    embedded.weld.getEvent(),
                    null));

            embedded.regs.add(
                    context.registerService(BeanManager.class.getName(),
                    embedded.weld.getBeanManager(),
                    null));

            embedded.regs.add(
                    context.registerService(Instance.class.getName(),
                    embedded.weld.getInstance(),
                    null));
        } catch (Throwable t) {
            // Ignore
        }
        embedded.weld.initialize(new BundleContainer() {

                @Override
                public void fire(InterBundleEvent event) {
                    // nothing to do
                }
            }, new BundleContainers() {

            @Override
            public Collection<BundleContainer> getContainers() {
                return Collections.emptyList();
            }
        });
        embedded.activator.start(context);
        if (!set) {
            CDIOSGiExtension.currentBundle.remove();
        }
        return embedded;
    }

    public void shutdown() throws Exception {
        boolean set = CDIOSGiExtension.currentBundle.get() != null;
        CDIOSGiExtension.currentBundle.set(context.getBundle().getBundleId());
        activator.stop(context);
        for (ServiceRegistration reg : regs) {
            try {
                reg.unregister();
            } catch (IllegalStateException e) {
                // Ignore
            }
        }
        weld.shutdown();
        if (!set) {
            CDIOSGiExtension.currentBundle.remove();
        }
    }

    public Event event() {
        return weld.getInstance().select(Event.class).get();
    }

    public BeanManager beanManager() {
        return weld.getBeanManager();
    }

    public Instance<Object> instance() {
        return weld.getInstance();
    }
}
