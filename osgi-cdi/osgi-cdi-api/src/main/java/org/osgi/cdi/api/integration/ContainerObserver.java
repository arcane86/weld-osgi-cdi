package org.osgi.cdi.api.integration;

import org.osgi.cdi.api.extension.events.InterBundleEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
@ApplicationScoped
public class ContainerObserver {

    private BundleContainer currentContainer;
    private BundleContainers containers;

    public void setCurrentContainer(BundleContainer currentContainer) {
        this.currentContainer = currentContainer;
    }

    public void setContainers(BundleContainers containers) {
        this.containers = containers;
    }

    public void listenInterBundleEvents(@Observes InterBundleEvent event) {
        if (!event.isSent()) {
            for (BundleContainer container : containers.getContainers()) {
                if (!container.equals(currentContainer)) {
                    event.sent();
                    container.fire(event);
                }
            }
        }
    }
}
