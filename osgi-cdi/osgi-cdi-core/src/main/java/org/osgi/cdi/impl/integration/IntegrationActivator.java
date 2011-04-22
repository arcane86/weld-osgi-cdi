package org.osgi.cdi.impl.integration;

import org.osgi.cdi.api.integration.BundleContainer;
import org.osgi.cdi.api.integration.BundleContainerFactory;
import org.osgi.cdi.api.integration.BundleContainers;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: guillaume
 * Date: 27/01/11
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class IntegrationActivator implements BundleActivator, BundleListener, ServiceListener, BundleContainers {

    private Map<Long, Holder> managed;

    private ServiceReference containerFactory;

    private BundleContext context;

    @Override
    public void start(BundleContext context) throws Exception {
        managed = new HashMap<Long, Holder>();
        containerFactory = null;
        this.context = context;

        ServiceReference reference = null;
        reference = context.getServiceReference(BundleContainerFactory.class.getName());

        if(reference != null) {
            containerFactory = reference;
            initialize();
        }

        context.addServiceListener(this);
        
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        for (Bundle bundle : context.getBundles()) {
            Holder holder = (Holder) managed.get(bundle.getBundleId());
            if (holder != null) {
                stopManagement(holder.bundle);
            }
        }
    }

    private void initialize() {
        for (Bundle bundle : context.getBundles()) {
            if (Bundle.ACTIVE == bundle.getState()) {
                startManagement(bundle);
            }
        }
        context.addBundleListener(this);
    }

    @Override
    public void bundleChanged(BundleEvent event) {
        switch (event.getType()) {
            case BundleEvent.STARTED:
                startManagement(event.getBundle());
                break;
            case BundleEvent.STOPPED:
                stopManagement(event.getBundle());
                break;
        }
    }

    @Override public void serviceChanged(ServiceEvent event) {
        ServiceReference reference = event.getServiceReference();
        if(containerFactory == null) {
            containerFactory = context.getServiceReference(BundleContainerFactory.class.getName());
            if(containerFactory != null) {
                initialize();
            }
        } else if(reference.compareTo(containerFactory) == 0) {
            switch (event.getType()) {
                case ServiceEvent.MODIFIED:
                    containerFactory = reference;
                    break;
                case ServiceEvent.UNREGISTERING:
                    containerFactory = null;
                    try {
                        stop(context);
                    } catch (Exception e) {
                        //ignore
                    }
                    break;
            }
        }
    }

    private void stopManagement(Bundle bundle) {
        Holder holder = (Holder) managed.get(bundle.getBundleId());
        if (holder != null) {
            BundleContainerFactory factory = (BundleContainerFactory) context.getService(containerFactory);
            factory.stopBundleContainer(bundle);
            managed.remove(bundle.getBundleId());
//            Collection<ServiceRegistration> regs = holder.registrations;
//            for (ServiceRegistration reg : regs) {
//                try {
//                    reg.unregister();
//                } catch (IllegalStateException e) {
//                    // Ignore
//                }
//            }
//            holder.container.shutdown();
//            managed.remove(bundle.getBundleId());
        }
    }

    private void startManagement(Bundle bundle) {

        Holder holder = new Holder();
        BundleContainerFactory factory = (BundleContainerFactory) context.getService(containerFactory);
        holder.bundle = bundle;
        holder.container = factory.getBundleContainer(bundle);
        managed.put(bundle.getBundleId(), holder);
        context.ungetService(containerFactory);

//        Holder holder = new Holder();
//        Weld weld = new Weld(bundle);
//        weld.initialize(holder, this);
//
//        if (weld.isStarted()) {
//
//            Collection<ServiceRegistration> regs = new ArrayList<ServiceRegistration>();
//
//            BundleContext bundleContext = bundle.getBundleContext();
//            try {
//                regs.add(
//                        bundleContext.registerService(Event.class.getName(),
//                                              weld.getEvent(),
//                                              null));
//
//                regs.add(
//                        bundleContext.registerService(BeanManager.class.getName(),
//                                weld.getBeanManager(),
//                                null));
//
//                regs.add(
//                        bundleContext.registerService(Instance.class.getName(),
//                                weld.getInstance(),
//                                null));
//            } catch (Throwable t) {
//                // Ignore
//            }
//            holder.container = weld;
//            holder.registrations = regs;
//            holder.bundle = bundle;
//            managed.put(bundle.getBundleId(), holder);
//        }
    }

    @Override
    public Collection<BundleContainer> getContainers() {
        Set<BundleContainer> result = new HashSet<BundleContainer>();
        for(Holder holder : managed.values()) {
            result.add(holder.container);
        }
        return result;
    }

    private static class Holder {
        Bundle bundle;
        BundleContainer container;
    }
}
