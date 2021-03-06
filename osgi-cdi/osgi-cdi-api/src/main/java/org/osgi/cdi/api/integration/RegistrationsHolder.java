package org.osgi.cdi.api.integration;

import org.osgi.framework.ServiceRegistration;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mathieu ANCELIN - SERLI (mathieu.ancelin@serli.com)
 */
@ApplicationScoped
public class RegistrationsHolder {

    private List<ServiceRegistration> registrations = new ArrayList<ServiceRegistration>();

    public List<ServiceRegistration> getRegistrations() {
        return registrations;
    }

    public void addRegistration(ServiceRegistration reg) {
        registrations.add(reg);
    }

    public void removeRegistration(ServiceRegistration reg) {
        registrations.remove(reg);
    }

    public void clear() {
        registrations.clear();
    }

}
